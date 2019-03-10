package com.wanghuan.model.sys;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserInfo implements Serializable {
    private String pkSid;
    private String userId;
    private String userName;
    private String userMobile;
    private String imageUrl;
    private Date createTime;
}
