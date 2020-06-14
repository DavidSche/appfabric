package com.davidche.appfabric.uaa.log;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Helper class for log message format.
 *
 * @author Idan
 */
@NoArgsConstructor
final class LoggerMsgArgsGenerator {

    public Object[] enter(ProceedingJoinPoint joinPoint, MyLoggable myLoggable) {
        return new Object[]{methodName(joinPoint), methodArgs(joinPoint, myLoggable)};
    }

    public Object[] warnBefore(ProceedingJoinPoint joinPoint, MyLoggable myLoggable, long nano) {
        return new Object[]{methodName(joinPoint), methodArgs(joinPoint, myLoggable),
                durationString(nano), warnDuration(myLoggable)};
    }

    public Object[] warnAfter(ProceedingJoinPoint joinPoint, MyLoggable myLoggable, Object result, long nano) {
        return new Object[]{methodName(joinPoint), methodArgs(joinPoint, myLoggable),
                methodResults(result, myLoggable), durationString(nano), warnDuration(myLoggable)};
    }

    public Object[] after(ProceedingJoinPoint joinPoint, MyLoggable myLoggable, Object result, long nano) {
        return new Object[]{methodName(joinPoint), methodArgs(joinPoint, myLoggable),
                methodResults(result, myLoggable), durationString(nano)};
    }

    public Object[] error(ProceedingJoinPoint joinPoint, MyLoggable myLoggable, long nano, Throwable err) {
        return new Object[]{methodName(joinPoint), methodArgs(joinPoint, myLoggable),
                errClass(err), errMsg(err), errSourceClass(err), errLine(err), durationString(nano)};
    }

    public Object[] errorWithException(ProceedingJoinPoint joinPoint, MyLoggable myLoggable, long nano, Throwable err) {
        return new Object[]{methodName(joinPoint), methodArgs(joinPoint, myLoggable),
                errClass(err), errMsg(err), errSourceClass(err), errLine(err), durationString(nano), err};
    }

    private String warnDuration(MyLoggable myLoggable) {
        return Duration.ofMillis(myLoggable.warnUnit().toMillis(myLoggable.warnOver())).toString();
    }

    private String methodName(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
    }

    private String methodArgs(JoinPoint joinPoint, MyLoggable myLoggable) {
        return myLoggable.skipArgs() ? ".." : argsToString(joinPoint.getArgs());
    }

    private String methodResults(Object result, MyLoggable myLoggable) {
        return myLoggable.skipResult() ? ".." : argsToString(result);
    }

    private String errClass(Throwable err) {
        return err.getClass().getName();
    }

    private String errMsg(Throwable err) {
        return err.getMessage();
    }

    private int errLine(Throwable err) {
        if (err.getStackTrace().length > 0) {
            return err.getStackTrace()[0].getLineNumber();
        }
        return -1;
    }

    private String errSourceClass(Throwable err) {
        if (err.getStackTrace().length > 0) {
            return err.getStackTrace()[0].getClassName();
        }
        return "somewhere";
    }

    private String durationString(long nano) {
        return Duration.ofMillis(TimeUnit.NANOSECONDS.toMillis(nano)).toString();
    }

    private String argsToString(Object arg) {
        String text;
        if (arg == null) {
            return "NULL";
        } else if (arg.getClass().isArray()) {
            if (arg instanceof Object[]) {
                text = objectArraysToString((Object[]) arg);
            } else {
                text = primitiveArrayToString(arg);
            }
        } else {
            String origin = arg.toString();
            if (arg instanceof String || origin.isEmpty()) {
                text = String.format("'%s'", origin);
            } else {
                text = origin;
            }
        }
        return text;
    }

    private String objectArraysToString(Object... arg) {
        StringBuilder bldr = new StringBuilder();
        bldr.append('[');
        for (Object item : arg) {
            if (bldr.length() > 1) {
                bldr.append(",").append(" ");
            }
            bldr.append(argsToString(item));
        }
        return bldr.append(']').toString();
    }

    private String primitiveArrayToString(Object arg) {
        String text;
        if (arg instanceof char[]) {
            text = Arrays.toString((char[]) arg);
        } else if (arg instanceof byte[]) {
            text = Arrays.toString((byte[]) arg);
        } else if (arg instanceof short[]) {
            text = Arrays.toString((short[]) arg);
        } else if (arg instanceof int[]) {
            text = Arrays.toString((int[]) arg);
        } else if (arg instanceof long[]) {
            text = Arrays.toString((long[]) arg);
        } else if (arg instanceof float[]) {
            text = Arrays.toString((float[]) arg);
        } else if (arg instanceof double[]) {
            text = Arrays.toString((double[]) arg);
        } else if (arg instanceof boolean[]) {
            text = Arrays.toString((boolean[]) arg);
        } else {
            text = "[unknown]";
        }
        return text;
    }
}