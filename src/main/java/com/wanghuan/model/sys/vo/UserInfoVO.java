package com.wanghuan.model.sys.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserInfoVO implements Serializable {
    private String userName;
    private String userMobile;
}
