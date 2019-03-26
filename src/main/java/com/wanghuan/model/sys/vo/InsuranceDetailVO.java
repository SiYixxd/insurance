package com.wanghuan.model.sys.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class InsuranceDetailVO {

    private String companyId;
    private String companyName;
    private String insuranceName;
    private String insuranceRange;//保险范围
    private int insuranceMin;//投保最小年龄
    private int insuranceMax;//投保最大年龄
    private String insuranceLimit;//保险期限
    private String insuranceDetail;//保险条款
    private String insuranceRules;//保险须知
    private String insuranceSummary;//保险简介
    private String insuranceBanner;//保险条幅，最上面的那个图片 xxx.jpg

    private List<PlanVO> planList;

}
