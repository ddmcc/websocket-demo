package com.ddmcc.websocket.demo.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author jiangrz
 */
@Data
public class SendMessage {

    private Long fromUserId;

    private Long toUserId;

    private String content;

    private Date time;

    private Integer msgType;
}
