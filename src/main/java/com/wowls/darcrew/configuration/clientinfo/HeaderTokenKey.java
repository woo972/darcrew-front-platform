package com.wowls.darcrew.configuration.clientinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HeaderTokenKey {
    KEY(0),
    USER_ID(1),
    OS_TYPE(2),
    OS_VERSION(3),
    APP_VERSION(4),
    DEVICE_ID(5),
    DEVICE_MODEL(6),
    RESOLUTION_TYPE(7),
    ACCESS_TOKEN(8),
    UPDATE_TOKEN(9),
    SESSION_KEY(10),
    CLIENT_IP(11),
    NETWORK_TYPE(12);

    private int offset;
}
