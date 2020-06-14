package com.davidche.appfabric.uaa.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Indicates that Logger support should be enabled.
 *
 * This should be applied to a Spring java config and should have an accompanying '@Configuration'
 * annotation.
 *
 * @author Idan Rozenfeld
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(LoggerConfiguration.class)
public @interface EnableMyLogger {
}
