package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class PlanItem implements Serializable {
    private String pkSid;
    private Date createTime;
    private int deleteStatus;
    private String planName;
    private String planInsurPrice;
    private String planInfo;
    private String planId;


}
