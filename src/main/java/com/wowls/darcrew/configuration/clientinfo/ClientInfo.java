package com.wowls.darcrew.configuration.clientinfo;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
public class ClientInfo {
    private String key;
    private String osType;
    private String osVersion;
    private String appVersion;
    private String accessToken;
    private String updateToken;
    private String sessionKey;
    private String userId;
    private String deviceId;
    private String deviceModel;
    private String resolutionType;
    private String networkType;
    private String clientIp;

    public String serialize() {
        return Stream.of(key, userId, osType, osVersion, appVersion,
                deviceId, deviceModel, resolutionType,
                accessToken, updateToken, sessionKey,
                clientIp, networkType)
                .map(StringUtils::defaultString)
                .collect(Collectors.joining("|"));
    }
}
