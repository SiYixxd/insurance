package com.wanghuan.service.impl.sys;

import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.service.sys.RegistService;
import com.wanghuan.service.sys.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class RegisetServiceImpl implements RegistService {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

 /*   @Resource(name = "messageInfoServiceImpl")
    private MessageInfoService messageInfoService;*/

    @Override
    @Transactional(readOnly = false)    //事务注解
    public UserInfo signUp(String mobile, String userName, String code, String imageUrl) {
        // 注册的话 用户名给随机值，头像给默认空就行了。 需要一个接口用于用户更新昵称和头像
        UserInfo dbUserInfo = userInfoService.getUserInfoByUserMobile(mobile);
        //2.2 如果有记录，说明他是登陆 直接返回信息
        if (dbUserInfo != null) {
            //登陆 直接返回信息
            return dbUserInfo;
        } else {
            //2.3 如果没有记录，注册，保存用户信息， 返回信息 registerService
            // 2.4注册 事务操作 一定要写到service
            UserInfo userInfo = new UserInfo();
            userInfo.setUserMobile(mobile);
            userInfo.setUserId(String.valueOf(new Random().nextInt(100000)));
            userInfo.setUserName(String.valueOf(new Random().nextInt(100000)));
            userInfo.setImageUrl("-");
            userInfo.setCreateTime(new Date());
            log.info("保存用户注册信息， 用户手机号:" + "" + ", 用户完整信息:" + userInfo.toString());
            userInfoService.insertUser(userInfo);
            return userInfo;
        }

    }

}





