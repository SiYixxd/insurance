package com.wanghuan.controller;

import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.service.sys.UserInfoService;
import com.wanghuan.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
public class PasswordController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    public BaseResponse updatePassword(String password, String newPassword, String mobile) throws NoSuchAlgorithmException {
        BaseResponse response = new BaseResponse();
        //通过用户手机号来获取用户的oldPassword
        UserInfo userInfo = userInfoService.getUserInfoByUserMobile(mobile);
        String dbPassword = userInfo.getPassword();
         /* 判断用户登录问题
         用户密码为空，提醒用户输入密码
           数据库中密码与用户输入密码不一致，提醒用户密码错误；
           else登录成功，开始修改密码
           新密码和旧密码一致，提醒用户不能一致
           重新输入密码，对比两次输入的新密码一致。
           不一致，提醒
           一致 修改成功
        * */
        if (dbPassword == null || dbPassword.equals("")) {
            response.setCode(10001);
            response.setMessage("您的密码为空，请输入密码！");
            return response;
        } else if (dbPassword != password) {
            response.setCode(10001);
            response.setMessage("您的密码不正确，请重新输入");
            return response;
        }

        // 更新密码了
        //登陆以后开始修改密码
        if (dbPassword == newPassword){
            response.setCode(10001);
            response.setMessage("不能使用旧密码");
            return response;
        }
        //判断全部通过了将密码保存
        String md5Password=  MD5Util.encrypt(newPassword);
        userInfo.setPassword(md5Password);
        userInfoService.updateUserPassword(md5Password,mobile);
        response.setCode(1000);
        response.setMessage("成功");
        return response;
    }
}
