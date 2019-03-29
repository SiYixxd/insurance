package com.wanghuan.model.sys;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserSign implements Serializable {
    private String userId;
    /**
     * 状态 0 未签到 1 已签到
     */
    private int signStatus;
    /**
     * 总共签到天数
     */
    private int totalDay;
    /**
     * 当前应该签第几天 / 理论应该签的
     */
    private int currentDay;

}
