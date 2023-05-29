package com.ddmcc.websocket.demo.endpoint;


import com.ddmcc.websocket.demo.handle.SessionWrapper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangrz
 */
public class SessionCache {

    /**
     * 用户ID：session
     */
    public static ConcurrentHashMap<Long, SessionWrapper> USER_SESSION_MAP = new ConcurrentHashMap<>();


}
