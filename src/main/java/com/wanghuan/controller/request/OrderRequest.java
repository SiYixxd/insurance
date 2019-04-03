package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.lang.model.element.NestingKind;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class OrderRequest implements Serializable {
    private Date startTime;
    private Date endTime;
    private String consumerIdNum;
    private String userId;
    private String insuranceId;
    private String planId;
    private boolean saveAct;
    private String consumerName;
    private Double price;
    private String orderId;
}
