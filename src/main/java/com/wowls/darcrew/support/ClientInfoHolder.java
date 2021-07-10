package com.wowls.darcrew.support;

import com.google.common.base.Preconditions;
import com.wowls.darcrew.configuration.clientinfo.ClientInfo;
import org.springframework.stereotype.Component;

@Component
public class ClientInfoHolder {

    private static final ThreadLocal<ClientInfo> CONTEXT = new ThreadLocal<>();

    public static void set(ClientInfo clientInfo) {
        CONTEXT.set(clientInfo);
    }
    public static ClientInfo get() {
        ClientInfo clientInfo = CONTEXT.get();
        Preconditions.checkState(clientInfo != null, "client info is not initialized");
        return clientInfo;
    }

    public static void reset() {
        CONTEXT.remove();
        CONTEXT.set(null);
    }
}