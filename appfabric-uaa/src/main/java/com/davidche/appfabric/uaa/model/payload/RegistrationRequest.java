package com.davidche.appfabric.uaa.model.payload;

import com.davidche.appfabric.uaa.validation.annotation.NullOrNotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(name = "Registration Request", description = "The registration request payload")
public class RegistrationRequest {

    @NullOrNotBlank(message = "Registration username can be null but not blank")
    @Schema(name ="A valid username", allowableValues = "NonEmpty String")
    private String username;

    @NullOrNotBlank(message = "Registration email can be null but not blank")
    @Schema(name ="A valid email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NullOrNotBlank(message = "Registration phone can be null but not blank")
    @Schema(name ="A valid phone", required = true, allowableValues = "NonEmpty String")
    private String phone;

    @NotNull(message = "Registration password cannot be null")
    @Schema(name ="A valid password string", required = true, allowableValues = "NonEmpty String")
    private String password;

    @NotNull(message = "Specify whether the user has to be registered as an admin or not")
    @Schema(name ="Flag denoting whether the user is an admin or not", required = true,
            type = "boolean", allowableValues = "true, false")
    private Boolean registerAsAdmin;

    public RegistrationRequest(String username, String email,
                               String password, Boolean registerAsAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registerAsAdmin = registerAsAdmin;
    }

    public RegistrationRequest() {
    }

}
