package com.ddmcc.websocket.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangrz
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties("ws")
public class WebsocketProperties {

    private String name;

    private String path;

    private int port;

}
