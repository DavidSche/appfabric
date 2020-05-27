package com.davidche.workflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("appfabric-workflow-server")
public class CamundaWorkflowApplication {
    public static void main(String... args) {
        SpringApplication.run(CamundaWorkflowApplication.class, args);
    }
}
