package com.davidche.appfabric.uaa.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.logging.LogLevel;

/**
 * Log deceleration for methods or classes, whose execution should be logged.
 *
 * @author Idan Rozenfeld
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface MyLoggable {

    /**
     * The log level {@link LogLevel}. (default: INFO)
     */
    LogLevel value() default LogLevel.INFO;

    /**
     * The logger name. if not set, class name will be given.
     */
    String name() default "";

    /**
     * Log method before its execution? (default: False)
     */
    boolean entered() default false;

    /**
     * Skip log method with its results? (default: False)
     */
    boolean skipResult() default false;

    /**
     * Skip log method with its arguments? (default: False)
     */
    boolean skipArgs() default false;

    /**
     * List of exceptions that this logger should not log its stack trace. (default: None)
     */
    Class<? extends Throwable>[] ignore() default {};

    /**
     * Should logger warn whenever method execution takes longer? (default: Forever)
     */
    long warnOver() default -1;

    /**
     * Time unit for the warnOver. (default: MINUTES)
     * (warnOver = 2, warnUnit = TimeUnit.SECONDS)
     */
    TimeUnit warnUnit() default TimeUnit.MINUTES;
}