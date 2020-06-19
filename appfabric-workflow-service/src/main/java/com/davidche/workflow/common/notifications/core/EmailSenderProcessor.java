package com.davidche.workflow.common.notifications.core;

@FunctionalInterface
public interface EmailSenderProcessor {

    void process(EmailContext emailContext);

}
