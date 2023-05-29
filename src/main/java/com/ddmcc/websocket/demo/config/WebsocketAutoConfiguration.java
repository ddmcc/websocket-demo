package com.ddmcc.websocket.demo.config;


import com.ddmcc.websocket.demo.handle.CloseSessionHandler;
import com.ddmcc.websocket.demo.handle.OnMessageHandler;
import com.ddmcc.websocket.demo.handle.OpenSessionHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jiangrz
 */
@Configuration
public class WebsocketAutoConfiguration {


    @Value("${node_name}")
    private String nodeName;

    @Bean
    public OpenSessionHandler openSessionHandler(StringRedisTemplate template) {
        return new OpenSessionHandler(template, nodeName);
    }


    @Bean
    public CloseSessionHandler closeSessionHandler(StringRedisTemplate template) {
        return new CloseSessionHandler(template);
    }

    @Bean
    public OnMessageHandler onMessageHandler(RocketMQTemplate rocketMQTemplate, StringRedisTemplate template) {
        return new OnMessageHandler(rocketMQTemplate, template);
    }
}
