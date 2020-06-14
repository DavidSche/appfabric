package com.davidche.appfabric.uaa.model.payload;

import com.davidche.appfabric.uaa.validation.annotation.NullOrNotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "Login Request", description = "The login request payload")
public class LoginRequest {

    @NullOrNotBlank(message = "Login Username can be null but not blank")
    @Schema(name = "Registered username", allowableValues = "NonEmpty String", nullable = false)
    private String username;

    @NullOrNotBlank(message = "Login Email can be null but not blank")
    @Schema(name = "User registered email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NotNull(message = "Login password cannot be blank")
    @Schema(name = "Valid user password", required = true, allowableValues = "NonEmpty String")
    private String password;

    @Valid
    @NotNull(message = "Device info cannot be null")
    @Schema(name = "Device info", required = true, type = "object", allowableValues = "A valid " +
            "deviceInfo object")
    private DeviceInfo deviceInfo;

    public LoginRequest(String username, String email, String password, DeviceInfo deviceInfo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.deviceInfo = deviceInfo;
    }

    public LoginRequest() {
    }

}
