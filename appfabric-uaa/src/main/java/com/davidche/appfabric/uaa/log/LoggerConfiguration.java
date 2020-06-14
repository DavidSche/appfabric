package com.davidche.appfabric.uaa.log;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the logger.
 *
 * @author Idan Rozenfeld
 * @see EnableMyLogger
 */
@Configuration
public class LoggerConfiguration {

    @Bean
    public MyLogger logger() {
        return new MyLogger();
    }

    @Bean
    public MyLoggerInterceptor loggerInterceptor(MyLogger myLogger) {
        return new MyLoggerInterceptor(myLogger);
    }
}