package com.ddmcc.websocket.demo.handle;


import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiangrz
 */
@Slf4j
public class StorageHandler extends AbstractSessionHandler {

    private final SessionHandler sessionHandler;

    public StorageHandler(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    @Override
    public void handle(SessionWrapper session) {
        super.handle(session);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean handleMessage(ReceiveMessage message, Long userId) {
        // 发送mq
        if (sessionHandler.handleMessage(message, userId)) {
           // store
        }
        return Boolean.FALSE;
    }
}
