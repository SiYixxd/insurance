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
    private String pkSid;//数据库自增长
    private String userId;//uuid生成
    private String userName;
    private String userMobile;
    private String imageUrl;
    private Date createTime;//new date
    private String password;//md5
}
