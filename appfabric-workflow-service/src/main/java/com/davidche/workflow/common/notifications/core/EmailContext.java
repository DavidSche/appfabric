package com.davidche.workflow.common.notifications.core;

import com.davidche.workflow.common.notifications.dto.Attachment;
import com.davidche.workflow.common.notifications.dto.EmailBody;
import com.davidche.workflow.common.notifications.dto.EmailRecipient;
import com.davidche.workflow.common.notifications.dto.EmailSubject;

import java.util.Set;

public interface EmailContext {

    Set<EmailRecipient> getEmailRecipients();

    Set<Attachment> getAttachments();

    String getFrom();

    EmailBody getEmailBody();

    EmailSubject getSubject();


}
