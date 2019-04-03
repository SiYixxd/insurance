package com.wanghuan.model.sys.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@ToString
public class OrderVO implements Serializable {
    private String insurancePolicyId;
    private Date createTime;            //订单的创建时间
    private String orderId;         //该订单的id，随机生成。
    private String consumerName;
    private Date startTime;         //保险生效时间
    private Date endTime;       //保险过期时间
    private Double price;
}
