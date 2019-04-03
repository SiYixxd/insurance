package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Consumer implements Serializable {
    private String pkSid;               //数据库主键，自增长。
    private String orderId;             //该被保人所对应的保单Id（暂先不考虑被保人有多条订单与一条订单有多个被保人）
    private String consumerName;        //被保人姓名
    private String consumerIdNum;                 //被保人身份证号
    private String relation;                 //被保人与投保人关系
}
