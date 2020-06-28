package com.davidche.workflow.common.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<String> violations;

    public AccessDeniedException(String message) {
        super(message);
        this.violations = new ArrayList<String>();
        violations.add(message);
    }

}