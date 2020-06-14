package com.davidche.appfabric.uaa.model.payload;

import com.davidche.appfabric.uaa.model.DeviceType;
import com.davidche.appfabric.uaa.validation.annotation.NullOrNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeviceInfo {

    @NotBlank(message = "Device id cannot be blank")
    @Schema(name = "Device Id", required = true, type = "string", allowableValues = "Non empty string")
    private String deviceId;

    @NotNull(message = "Device type cannot be null")
    @Schema(name = "Device type Android/iOS", required = true, type = "string", allowableValues =
            "DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS")
    private DeviceType deviceType;

    @NullOrNotBlank(message = "Device notification token can be null but not blank")
    @Schema(name = "Device notification id", type = "string", allowableValues = "Non empty string")
    private String notificationToken;

    public DeviceInfo() {
    }

    public DeviceInfo(String deviceId, DeviceType deviceTypem, String notificationToken) {
        this.deviceId = deviceId;
        deviceType = deviceType;
        notificationToken = notificationToken;
    }

}
