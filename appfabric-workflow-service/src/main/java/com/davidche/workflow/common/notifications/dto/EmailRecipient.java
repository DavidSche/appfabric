package com.davidche.workflow.common.notifications.dto;

import lombok.Data;

@Data
public final class EmailRecipient {

    private RecipientType type;

    private String emailAddress;

}
