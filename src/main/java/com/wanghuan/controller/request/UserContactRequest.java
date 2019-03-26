package com.wanghuan.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserContactRequest implements Serializable {
    private String contactMobile;
    private String contactEmail;
    private String contactName;
    private String contactIdNumber;
    private String userId;
}
