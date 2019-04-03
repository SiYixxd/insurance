package com.wanghuan.model.sys.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ConsumerVO implements Serializable {
    private String pkSid;               //数据库主键，自增长。
    private String consumerName;        //被保人姓名
    private String consumerIdNum;                 //被保人身份证号
    private String relation;                 //被保人与投保人关系
}
