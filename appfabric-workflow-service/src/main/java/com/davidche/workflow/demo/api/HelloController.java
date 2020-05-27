package com.davidche.workflow.demo.api;

import com.davidche.workflow.demo.dto.GreetingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

//import org.apache.commons.lang.StringUtils;

/**
 * HelloController:Rest controller for working with test
 *
 * @version 0.1-SNAPSHOT
 * <p>
 * com.david:david-api-demo-rest-server
 * @Author:David.che :
 */
@Slf4j
@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public GreetingDTO greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.trace("This is a trace log example");
        log.info("This is an info log example");
        log.debug("This is a debug log example");
        log.error("This is an error log example");
        log.warn("This is a warn log example");
        // log.info(ResponseStatus.SUCCESSFUL);
        return new GreetingDTO(counter.incrementAndGet(), String.format(template, name));
    }


    @RequestMapping("/hello")
    public GreetingDTO hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        return new GreetingDTO(counter.incrementAndGet(), String.format(template, name));
    }

// /auditevents – lists security audit-related events such as user login/logout. Also, we can filter by principal or type among others fields
// /beans – returns all available beans in our BeanFactory. Unlike /auditevents, it doesn’t support filtering
// /conditions – formerly known as /autoconfig, builds a report of conditions around auto-configuration
// /configprops – allows us to fetch all @ConfigurationProperties beans
// /env – returns the current environment properties. Additionally, we can retrieve single properties
// /flyway – provides details about our Flyway database migrations
// /health – summarises the health status of our application
// /heapdump – builds and returns a heap dump from the JVM used by our application
// /info – returns general information. It might be custom data, build information or details about the latest commit
// /liquibase – behaves like /flyway but for Liquibase
// /logfile – returns ordinary application logs
// /loggers – enables us to query and modify the logging level of our application
// /metrics – details metrics of our application. This might include generic metrics as well as custom ones
// /prometheus – returns metrics like the previous one, but formatted to work with a Prometheus server
// /scheduledtasks – provides details about every scheduled task within our application
// /sessions – lists HTTP sessions given we are using Spring Session
// /shutdown – performs a graceful shutdown of the application
// /threaddump – dumps the thread information of the underlying JVM
}
