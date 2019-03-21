package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class InsuranceCompany implements Serializable {
    private String pkSid;
    private Date createTime;
    private int deteleStatus;
    private String companyId;
    private String companyName;
    private String companyLogo;
}
