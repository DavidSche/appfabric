package com.davidche.workflow.demo.dto;

/**
 * test model :Greeting
 * 
 * @Author:David.che version: 0.1-SNAPSHOT Spring Boot APP
 *                   com.david:david-api-demo-rest-server
 *
 */

public class GreetingDTO {

    private final long id;
    private final String content;

    public GreetingDTO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}