package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class MessageInfo implements Serializable {
    private String pkSid;
    private String code;
    private String mobile;
    private Date createTime;
    private int timeout;
}
