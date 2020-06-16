package com.davidche.appfabric.uaa.model.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Setter
@Getter
@Schema(name = "Logout request", description = "The logout request payload")
public class LogOutRequest {

    @Valid
    @NotNull(message = "Device info cannot be null")
    @Schema(name = "Device info", required = true, type = "object", allowableValues = "A valid " +
            "deviceInfo object")
    private DeviceInfo deviceInfo;

    public LogOutRequest() {
    }

    public LogOutRequest(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

}
