package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RegisterRequest implements Serializable {
    private String code;
    private String mobile;

}
