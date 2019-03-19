package com.wanghuan.controller.response;

import com.wanghuan.model.sys.Admin;
import com.wanghuan.model.sys.UserInfo;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse implements Serializable {
    private int code;
    private String message;
    private UserInfo info;
    private Admin user;
    private Object data;


    public BaseResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
}
