package com.ddmcc.websocket.demo.handle;


import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;

/**
 * @author jiangrz
 */
public interface SessionHandler {


    /**
     * handler
     *
     * @param session session
     */
    void handle(SessionWrapper session);

    /**
     * handle message
     *
     * @param message message
     * @param userId  from userId
     * @return boolean
     */
    boolean handleMessage(ReceiveMessage message, Long userId);
}
