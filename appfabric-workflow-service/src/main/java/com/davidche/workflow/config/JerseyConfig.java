package com.davidche.workflow.config;

import org.camunda.bpm.spring.boot.starter.rest.CamundaJerseyResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Camunda Engine REST API URL
 * @See https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/rest-api/
 *
 */
@Component
@ApplicationPath("/rest")
public class JerseyConfig extends CamundaJerseyResourceConfig {

}
