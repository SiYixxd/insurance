package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class InsurancePlan implements Serializable {
    private String pkSid;
    private Date createTime;
    private int deleteStatus;
    private String insuranceId;
    private String planName;
    private String planId;
    private double planPrice;
}
