package com.davidche.appfabric.uaa.log;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

/**
 * AspectJ to intercept {@link MyLoggable} methods or classes.
 *
 * @author Idan Rozenfeld
 */
@Aspect
@Component
public class MyLoggerInterceptor {

    private MyLogger myLogger;

    private LoggerMsgArgsGenerator lmag;

    private Set<WarnPoint> warnPoints;
    private ScheduledExecutorService warnService;

    @Autowired
    public MyLoggerInterceptor(MyLogger myLogger) {
        this.lmag = new LoggerMsgArgsGenerator();
        this.myLogger = myLogger;
    }

    @PostConstruct
    protected void construct() {
        warnPoints = new ConcurrentSkipListSet<>();
        warnService = Executors.newSingleThreadScheduledExecutor();
        warnService.scheduleAtFixedRate(() -> {
            for (WarnPoint wp : warnPoints) {
                long duration = System.nanoTime() - wp.getStart();
                if (isOver(duration, wp.getMyLoggable())) {
                    log(LogLevel.WARN, "#{}({}): in {} and still running (max {})",
                            wp.getPoint(), wp.getMyLoggable(),
                            lmag.warnBefore(wp.getPoint(), wp.getMyLoggable(), duration));
                    warnPoints.remove(wp);
                }
            }
        }, 1L, 1L, TimeUnit.SECONDS);
    }

    @Pointcut("execution(public * *(..))"
            + " && !execution(String *.toString())"
            + " && !execution(int *.hashCode())"
            + " && !execution(boolean *.canEqual(Object))"
            + " && !execution(boolean *.equals(Object))")
    protected void publicMethod() {
    }

    @Pointcut("@annotation(myLoggable)")
    protected void loggableMethod(MyLoggable myLoggable) {
    }

    @Pointcut("@within(myLoggable)")
    protected void loggableClass(MyLoggable myLoggable) {
    }

    @Around(value = "publicMethod() && loggableMethod(myLoggable)", argNames = "joinPoint,myLoggable")
    public Object logExecutionMethod(ProceedingJoinPoint joinPoint, MyLoggable myLoggable) throws Throwable {
        return logMethod(joinPoint, myLoggable);
    }

    @Around(value = "publicMethod() && loggableClass(myLoggable) && !loggableMethod(com.davidche.appfabric.uaa.log.MyLoggable)", argNames = "joinPoint,myLoggable")
    public Object logExecutionClass(ProceedingJoinPoint joinPoint, MyLoggable myLoggable) throws Throwable {
        return logMethod(joinPoint, myLoggable);
    }

    public Object logMethod(ProceedingJoinPoint joinPoint, MyLoggable myLoggable) throws Throwable {
        long start = System.nanoTime();
        WarnPoint warnPoint = null;
        Object returnVal;

        if (isLevelEnabled(joinPoint, myLoggable) && myLoggable.warnOver() >= 0) {
            warnPoint = new WarnPoint(joinPoint, myLoggable, start);
            warnPoints.add(warnPoint);
        }

        if (myLoggable.entered()) {
            log(myLoggable.value(), "#{}({}): entered", joinPoint,
                    myLoggable, lmag.enter(joinPoint, myLoggable));
        }

        try {
            returnVal = joinPoint.proceed();

            long nano = System.nanoTime() - start;
            if (isOver(nano, myLoggable)) {
                log(LogLevel.WARN, "#{}({}): {} in {} (max {})",
                        joinPoint, myLoggable, lmag.warnAfter(joinPoint, myLoggable, returnVal, nano));
            } else {
                log(myLoggable.value(), "#{}({}): {} in {}", joinPoint, myLoggable,
                        lmag.after(joinPoint, myLoggable, returnVal, nano));
            }
            return returnVal;
        } catch (Throwable ex) {
            if (contains(myLoggable.ignore(), ex)) {
                log(LogLevel.ERROR, "#{}({}): thrown {}({}) from {}[{}] in {}",
                        joinPoint, myLoggable, lmag.error(joinPoint, myLoggable, System.nanoTime() - start, ex));
            } else {
                log(LogLevel.ERROR, "#{}({}): thrown {}({}) from {}[{}] in {}",
                        joinPoint, myLoggable, lmag.errorWithException(joinPoint, myLoggable, System.nanoTime() - start, ex));
            }
            throw ex;
        } finally {
            if (warnPoint != null) {
                warnPoints.remove(warnPoint);
            }
        }
    }

    private void log(LogLevel level, String message, ProceedingJoinPoint joinPoint, MyLoggable myLoggable, Object... args) {
        if (myLoggable.name().isEmpty()) {
            myLogger.log(level, ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass(), message, args);
        } else {
            myLogger.log(level, myLoggable.name(), message, args);
        }
    }

    private boolean isLevelEnabled(ProceedingJoinPoint joinPoint, MyLoggable myLoggable) {
        return myLoggable.name().isEmpty()
                ? myLogger.isEnabled(LogLevel.WARN,
                ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass())
                : myLogger.isEnabled(LogLevel.WARN, myLoggable.name());
    }

    private boolean isOver(long nano, MyLoggable myLoggable) {
        return myLoggable.warnOver() >= 0
                && TimeUnit.NANOSECONDS.toMillis(nano) > myLoggable.warnUnit().toMillis(myLoggable.warnOver());
    }

    private boolean contains(Class<? extends Throwable>[] array, Throwable exp) {
        boolean contains = false;
        for (final Class<? extends Throwable> type : array) {
            if (instanceOf(exp.getClass(), type)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private boolean instanceOf(Class<?> child, Class<?> parent) {
        boolean instance = child.equals(parent)
                || child.getSuperclass() != null && instanceOf(child.getSuperclass(), parent);
        if (!instance) {
            for (final Class<?> iface : child.getInterfaces()) {
                instance = instanceOf(iface, parent);
                if (instance) {
                    break;
                }
            }
        }
        return instance;
    }

    @EqualsAndHashCode(of = "point")
    @AllArgsConstructor
    @Getter
    protected static class WarnPoint implements Comparable<WarnPoint> {

        private ProceedingJoinPoint point;
        private MyLoggable myLoggable;
        private long start;

        @Override
        public int compareTo(WarnPoint obj) {
            return Long.compare(obj.getStart(), start);
        }
    }

}