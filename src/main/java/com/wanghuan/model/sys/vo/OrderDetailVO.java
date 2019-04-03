package com.wanghuan.model.sys.vo;

import com.wanghuan.model.sys.Consumer;
import com.wanghuan.model.sys.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
public class OrderDetailVO implements Serializable {
    private String insurancePolicyId;
    private Date createTime;            //订单的创建时间
    private String orderId;         //该订单的id，随机生成。
    private String consumerName;
    private Date startTime;         //保险生效时间
    private Date endTime;       //保险过期时间
    private Double price;
    private String userId;
    private String insuranceId;
    private List<UserInfoVO> userInfoList;
    private List<Consumer> consumerList;
   private List<PlanVO> planList;

}
