package com.wanghuan.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class InsuranceRequest implements Serializable {
    private String categoryId;
    private String insuranceId;
    private String companyId;
    private String insuranceName;
    private String insuranceRange;
    private int insuranceMin;
    private int insuranceMax;
    private String insuranceLimit;
    private String insuranceDetail;
    private String insuranceRules;
    private String insuranceSummary;
    private String insuranceBanner;
}
