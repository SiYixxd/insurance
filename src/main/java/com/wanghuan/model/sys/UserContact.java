package com.wanghuan.model.sys;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserContact implements Serializable {
    private String pkSid;//数据库自增长
    private Date createTime;
    private int deleteStatus;
    private String contactMobile;
    private String contactEmail;
    private String contactName;
    private String contactIdNumber;
    private String userId;

}
