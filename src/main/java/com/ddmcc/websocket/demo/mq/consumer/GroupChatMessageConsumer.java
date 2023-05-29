package com.ddmcc.websocket.demo.mq.consumer;

import com.ddmcc.websocket.demo.endpoint.SessionCache;
import com.ddmcc.websocket.demo.handle.SessionWrapper;
import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author jiangrz
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "group", topic = "GROUP", messageModel = MessageModel.BROADCASTING)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GroupChatMessageConsumer implements RocketMQListener<ReceiveMessage> {


    @Override
    public void onMessage(ReceiveMessage dto) {
        log.info("开始消费群聊消息：{}", dto);
        Long to = dto.getTo();
        // 根据群ID获取群人员或者全部发送
        SessionCache.USER_SESSION_MAP.entrySet().forEach(item -> {
            SessionWrapper sessionWrapper = item.getValue();
            if (sessionWrapper != null) {
                // 发送
            //    sessionWrapper.sendMessage(JSONObject.toJSONString(sendMessage))
            }
        });

    }
}
