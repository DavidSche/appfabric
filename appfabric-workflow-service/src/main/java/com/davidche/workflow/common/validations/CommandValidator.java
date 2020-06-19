package com.davidche.workflow.common.validations;


public interface CommandValidator<T> {

    void validate(T t);

}
