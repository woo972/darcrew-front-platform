package com.wowls.darcrew.configuration.clientinfo;

import com.wowls.darcrew.support.ClientInfoHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class ClientInfoInterceptor implements HandlerInterceptor {

    private static final String DARCREW_APP = "darcrew-app";

    private final ClientInfoHolder clientInfoHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        clientInfoHolder.set(ClientInfoHeaderExtractor.extract(request.getHeader(DARCREW_APP)));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        ClientInfoHolder.reset();
    }
}
