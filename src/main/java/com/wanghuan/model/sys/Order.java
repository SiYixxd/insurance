package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
public class Order implements Serializable {
    private String pksId;               //数据库主键，自增长
    private String userId;                  //该订单所属的用户，也就是默认的投保人。
    private String insurancePolicyId;                  //请求第三方保险公司的接口，支付成功之后产生的保单Id
    private Date createTime;            //订单的创建时间
    private Date updateTime;        //订单的更新时间
    private String orderId;         //该订单的id，随机生成。
    private String insuranceId;         //订单内对应的保险Id
    private String planId;      //订单内对应的保险计划Id
    private String consumerIdNum;      //被保人的Id，
    private String consumerName;
    private Date startTime;         //保险生效时间
    private Date endTime;       //保险过期时间
    private Double price;    //保单对应的价格。

}
