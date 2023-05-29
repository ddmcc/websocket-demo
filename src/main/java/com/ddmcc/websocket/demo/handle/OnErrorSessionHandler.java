package com.ddmcc.websocket.demo.handle;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangrz
 */
@Slf4j
public class OnErrorSessionHandler extends AbstractSessionHandler {


    private final SessionHandler sessionHandler;

    public OnErrorSessionHandler(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }


    @Override
    public void handle(SessionWrapper session) {
        log.error("用户链接异常关闭 userId：{}", session.getUserId());
        sessionHandler.handle(session);
    }
}
