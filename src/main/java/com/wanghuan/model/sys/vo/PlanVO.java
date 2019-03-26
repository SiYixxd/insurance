package com.wanghuan.model.sys.vo;

import com.wanghuan.model.sys.PlanItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PlanVO {
    private String pkSid;
    private Date createTime;
    private int deleteStatus;
    private String insuranceId;
    private String planName;
    private String planId;
    private double planInsurancePrice;
    private double planPrice;
    private String planInfo;
    private List<PlanItem> itemList;
}
