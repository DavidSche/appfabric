package com.davidche.appfabric.uaa.model.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(name = "Password reset link request", description = "The password reset link payload")
public class PasswordResetLinkRequest {

    @NotBlank(message = "Email cannot be blank")
    @Schema(name = "User registered email", required = true, allowableValues = "NonEmpty String")
    private String email;

    public PasswordResetLinkRequest(String email) {
        this.email = email;
    }

    public PasswordResetLinkRequest() {
    }

}
