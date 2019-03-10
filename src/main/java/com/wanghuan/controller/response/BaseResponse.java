package com.wanghuan.controller.response;

import com.wanghuan.model.sys.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class BaseResponse implements Serializable {

    private int code;
    private String message;

    private UserInfo info;
}
