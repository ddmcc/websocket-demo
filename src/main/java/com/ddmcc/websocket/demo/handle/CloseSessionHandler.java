package com.ddmcc.websocket.demo.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jiangrz
 */
@Slf4j
public class CloseSessionHandler extends AbstractSessionHandler {

    private final StringRedisTemplate stringRedisTemplate;

    public CloseSessionHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void handle(SessionWrapper session) {
        Long userId = session.getUserId();
        log.info("用户userId：{} 下线，sessionId关闭：{}", userId, session.getId());
        stringRedisTemplate.delete(super.buildUserNodeKey(userId));
        session.close();
    }
}
