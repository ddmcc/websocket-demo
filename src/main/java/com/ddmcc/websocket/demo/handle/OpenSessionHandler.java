package com.ddmcc.websocket.demo.handle;

import com.ddmcc.websocket.demo.endpoint.SessionCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * @author jiangrz
 */
@Slf4j
public class OpenSessionHandler extends AbstractSessionHandler {

    public final StringRedisTemplate stringRedisTemplate;

    private final String discoveryIp;

    public OpenSessionHandler(StringRedisTemplate stringRedisTemplate, String discoveryIp) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.discoveryIp = discoveryIp;
    }

    @Override
    public void handle(SessionWrapper session) {
        Long userId = session.getUserId();
        log.info("新用户连接 userId：{}， 当前节点在线：{}", userId, SessionCache.USER_SESSION_MAP.keySet().size());
        // 用户与服务器ip关系存入redis
        stringRedisTemplate.opsForValue().set(super.buildUserNodeKey(userId), discoveryIp);
        // 用户session存入map
        SessionWrapper sessionWrapper = SessionCache.USER_SESSION_MAP.computeIfPresent(userId, (k, v) -> {
            log.info("用户userId：{} 在其它地方上线了，旧sessionId关闭：{}", k, v.getId());
            v.close("在别处上线了，请在新的设备上查看消息！");
            return session;
        });
        SessionCache.USER_SESSION_MAP.put(userId, sessionWrapper == null ? session : sessionWrapper);
    }
}
