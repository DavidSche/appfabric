package com.davidche.workflow.common.notifications.dto;

import lombok.Data;

@Data
public final class EmailBody {

    /**
     * This is the html representation of the email message body.
     */
    private String message;

}
