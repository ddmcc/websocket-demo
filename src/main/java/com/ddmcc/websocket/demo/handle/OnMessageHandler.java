package com.ddmcc.websocket.demo.handle;

import com.alibaba.fastjson.JSONObject;
import com.ddmcc.websocket.demo.constant.Const;
import com.ddmcc.websocket.demo.model.dto.ReceiveMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * @author Administrator
 */
@Slf4j
public class OnMessageHandler extends AbstractSessionHandler {

    private final RocketMQTemplate template;
    private final StringRedisTemplate stringRedisTemplate;

    public OnMessageHandler(RocketMQTemplate template, StringRedisTemplate stringRedisTemplate) {
        this.template = template;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void handle(SessionWrapper session) {

    }

    @Override
    public boolean handleMessage(ReceiveMessage message, Long userId) {
        try {
            message.setTime(new Date());
            message.setFrom(userId);
            switch (message.getMsgType()) {
                case 10:
                    // 获取用户所在节点
                    String node = stringRedisTemplate.opsForValue().get(super.buildUserNodeKey(message.getTo()));
                    return template.syncSend("SINGLE:" + node, JSONObject.toJSONString(message)).getSendStatus() == SendStatus.SEND_OK;
                case 20:
                    return template.syncSend("GROUP:MESSAGE", JSONObject.toJSONString(message)).getSendStatus() == SendStatus.SEND_OK;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return Boolean.FALSE;
    }
}
