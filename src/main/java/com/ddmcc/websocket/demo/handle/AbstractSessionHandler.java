package com.ddmcc.websocket.demo.handle;


import com.ddmcc.websocket.demo.constant.Const;
import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;

/**
 * @author jiangrz
 */
public abstract class AbstractSessionHandler implements SessionHandler {

    public String buildUserNodeKey(Long userId) {
        return Const.REDIS_KEY_PREFIX + Const.USER_NODE_KEY + userId;
    }

    @Override
    public void handle(SessionWrapper session) {

    }


    @Override
    public boolean handleMessage(ReceiveMessage message, Long userId) {
        return false;
    }
}
