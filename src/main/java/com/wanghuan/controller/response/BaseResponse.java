package com.wanghuan.controller.response;

import com.wanghuan.common.InsuranceException;
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
    private Boolean endFlag;


    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setByExpcetion(InsuranceException expcetion) {
        this.setCode(expcetion.getCode());
        this.setMessage(expcetion.getMessage());
    }
}
