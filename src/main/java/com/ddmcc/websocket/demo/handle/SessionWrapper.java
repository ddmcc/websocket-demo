package com.ddmcc.websocket.demo.handle;

import com.ddmcc.websocket.demo.constant.Const;
import io.netty.channel.ChannelId;
import lombok.Data;
import org.yeauty.pojo.Session;

/**
 * @author jiangrz
 */
@Data
public class SessionWrapper {

    private final Session session;

    public SessionWrapper(Session session, Long userId) {
        session.setAttribute(Const.USER_ID, userId);
        this.session = session;
    }


    public void close(String message) {
        session.sendText(message);
        this.close();
    }

    public void close() {
        session.close();
    }

    public Long getUserId() {
        return session.getAttribute(Const.USER_ID);
    }

    public <T> T getAttr(String name) {
        return session.getAttribute(name);
    }

    public ChannelId getId() {
        return session.id();
    }

    public void sendMessage(String message) {
        session.sendText(message);
    }

    public boolean isClose() {
        return !session.isActive() || !session.isOpen();
    }
}
