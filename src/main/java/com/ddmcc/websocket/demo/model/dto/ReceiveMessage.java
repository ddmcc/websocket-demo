package com.ddmcc.websocket.demo.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author jiangrz
 */
@Data
public class ReceiveMessage {

    private String content;

    private Integer msgType;

    public Long to;

    public Long from;

    public Date time;
}
