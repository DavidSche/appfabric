package com.davidche.appfabric.uaa.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, String> model;

    public Mail() {
        model = new HashMap<>();
    }

    public Mail(String from, String to, String subject, String content, Map<String, String> model) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.model = model;
    }

}
