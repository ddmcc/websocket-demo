package com.ddmcc.websocket.demo.mq.consumer;

import com.ddmcc.websocket.demo.endpoint.SessionCache;
import com.ddmcc.websocket.demo.handle.SessionHandler;
import com.ddmcc.websocket.demo.handle.SessionWrapper;
import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author jiangrz
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "SINGLE", consumerGroup = "simple-${node_name}", selectorType = SelectorType.TAG, selectorExpression = "${node_name}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimpleChatMessageConsumer implements RocketMQListener<ReceiveMessage> {

    private final SessionHandler closeSessionHandler;


    @Override
    public void onMessage(ReceiveMessage dto) {
        log.info("开始消费聊天消息：{}", dto);
        SessionWrapper sessionWrapper = SessionCache.USER_SESSION_MAP.get(dto.getTo());
        if (sessionWrapper == null) {
            log.info("未找到消息接收人：{} ，可能已下线", dto.getTo());
        } else if (sessionWrapper.isClose()) {
            log.info("消息接收人链接已关闭：{}，可能已断开链接", dto.getTo());
            closeSessionHandler.handle(sessionWrapper);
        } else {
            // 发送
            // sessionWrapper.sendMessage(JSONObject.toJSONString(sendMessage));
        }
    }
}
