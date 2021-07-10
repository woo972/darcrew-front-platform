package com.wowls.darcrew.configuration.clientinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientInfoHeaderExtractor {

    private static final String SEPARATOR = "|";
    private static final int DEVICE_MODEL_MAX_LENGTH = 20;

    static ClientInfo extract(String appHeader) {
        String[] appHeaderTokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(appHeader, SEPARATOR);
        return ClientInfo.builder()
                .key(extractValueByOffset(appHeaderTokens, HeaderTokenKey.KEY))
                .userId(extractValueByOffset(appHeaderTokens, HeaderTokenKey.USER_ID))
                .osType(extractValueByOffset(appHeaderTokens, HeaderTokenKey.OS_TYPE))
                .osVersion(extractValueByOffset(appHeaderTokens, HeaderTokenKey.OS_VERSION))
                .appVersion(extractValueByOffset(appHeaderTokens, HeaderTokenKey.APP_VERSION))
                .deviceId(extractValueByOffset(appHeaderTokens, HeaderTokenKey.DEVICE_ID))
                .deviceModel(extractValueByOffset(appHeaderTokens, HeaderTokenKey.DEVICE_MODEL, DEVICE_MODEL_MAX_LENGTH))
                .resolutionType(extractValueByOffset(appHeaderTokens, HeaderTokenKey.RESOLUTION_TYPE))
                .accessToken(extractValueByOffset(appHeaderTokens, HeaderTokenKey.ACCESS_TOKEN))
                .updateToken(extractValueByOffset(appHeaderTokens, HeaderTokenKey.UPDATE_TOKEN))
                .sessionKey(extractValueByOffset(appHeaderTokens, HeaderTokenKey.SESSION_KEY))
                .clientIp(extractValueByOffset(appHeaderTokens, HeaderTokenKey.CLIENT_IP))
                .networkType(extractValueByOffset(appHeaderTokens, HeaderTokenKey.NETWORK_TYPE))
                .build();
    }

    private static String extractValueByOffset(String[] tokens, HeaderTokenKey key) {
        int offset = key.getOffset();
        if (tokens.length > offset) {
            return tokens[offset];
        }
        return null;
    }

    private static String extractValueByOffset(String[] tokens, HeaderTokenKey key, int maxLength) {
        String headerValue = extractValueByOffset(tokens, key);
        if (StringUtils.isNotBlank(headerValue) && headerValue.length() > maxLength) {
            headerValue = headerValue.substring(0, maxLength);
        }
        return headerValue;
    }
}
