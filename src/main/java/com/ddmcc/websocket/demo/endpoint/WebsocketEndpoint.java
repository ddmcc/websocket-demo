package com.ddmcc.websocket.demo.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.ddmcc.websocket.demo.handle.*;
import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;

/**
 * @author jiangrz
 */
@Slf4j
@Component
@ServerEndpoint(path = "${ws.path}/{userId}", port = "${ws.port}")
public class WebsocketEndpoint {


    @Autowired
    private OpenSessionHandler openSessionHandler;
    @Autowired
    private CloseSessionHandler closeSessionHandler;
    @Autowired
    private OnMessageHandler onMessageHandler;

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @PathVariable Long userId) {
        session.setSubprotocols("stomp");
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @PathVariable Long userId) {
        log.info("new connection");
        openSessionHandler.handle(new SessionWrapper(session, userId));
    }

    @OnClose
    public void onClose(Session session, @PathVariable Long userId) throws IOException {
        log.info("one connection closed");
        closeSessionHandler.handle(new SessionWrapper(session, userId));
    }

    @OnError
    public void onError(Session session, Throwable throwable, @PathVariable Long userId) {
        throwable.printStackTrace();
        new OnErrorSessionHandler(closeSessionHandler).handle(new SessionWrapper(session, userId));
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathVariable Long userId) {
        log.info("接收到来自：{} 的消息：{}", userId, message);
        new StorageHandler(onMessageHandler).handleMessage(JSONObject.parseObject(message, ReceiveMessage.class), userId);
    }

}
