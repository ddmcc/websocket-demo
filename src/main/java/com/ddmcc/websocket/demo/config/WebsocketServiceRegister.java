package com.ddmcc.websocket.demo.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.ddmcc.websocket.demo.properties.WebsocketProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 向nacos注册服务
 * @author jiangrz
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebsocketServiceRegister implements CommandLineRunner {


    private final WebsocketProperties websocketProperties;
    private final NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void run(String... args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", nacosDiscoveryProperties.getServerAddr());
        properties.setProperty("namespace", nacosDiscoveryProperties.getNamespace());
        NamingService namingService = NamingFactory.createNamingService(properties);
        // 注册服务节点
        namingService.registerInstance(websocketProperties.getName(), nacosDiscoveryProperties.getIp(), websocketProperties.getPort());
    }
}
