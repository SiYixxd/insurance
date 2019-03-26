package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CompanyRequest implements Serializable {
    private String companyId;
    private String companyName;
    private String companyLogo;
}
