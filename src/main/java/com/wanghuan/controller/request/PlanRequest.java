package com.wanghuan.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PlanRequest implements Serializable {

    private String planId;
    private String planName;
    private double planInsurancePrice;
    private double planPrice;
    private String planInfo;
    private String insuranceId;
}
