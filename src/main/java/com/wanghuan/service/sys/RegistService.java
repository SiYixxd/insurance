package com.wanghuan.service.sys;

import com.wanghuan.controller.request.RegisterRequest;
import com.wanghuan.model.sys.MessageInfo;
import com.wanghuan.model.sys.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegistService {
    //用户通过手机号注册，传入手机号和验证码
    public UserInfo signUp(String mobile,String userName,String code,String imageUrl);

}
