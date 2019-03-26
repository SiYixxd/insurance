package com.wanghuan.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SignRequest implements Serializable {
    private String userId;
    //    1 表示已签到  0表示未签到
    private int act;
}
