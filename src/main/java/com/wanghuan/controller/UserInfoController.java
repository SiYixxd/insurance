package com.wanghuan.controller;

import com.wanghuan.controller.request.RegisterRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.MessageInfo;
import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.service.sys.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@RestController
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;



    /**
     * 获取验证码
     * @param userMobile 用户手机号
     * @return
     */
    @GetMapping("/getCheckcode/{userMobile}")
    public String getCheckCode(@PathVariable String userMobile) {
        //1 随机一个验证码
        Random random = new Random();
        String checkCode = String.valueOf(random.nextInt(100000));
        //2 请求第三方短信接口发送短信
        //todo 请求第三方短信接口发送短信

        //3 todo 短信数据保存到数据库

        //4 发送成功则给用户返回数据
        return checkCode;
    }

    @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody RegisterRequest request) {
        BaseResponse response = new BaseResponse();
        //1 验证验证码是不是正确
            //1.1 根据手机号从messageinfo中查到最近一挑【order by createTime DESC limit 1】
            //1.2 判断手机号的验证码记录是不是有记录 如果没有返回一个报错信息提示验证码不存在
            //1.3 如果有 判断截止时间  并且判断用户的验证吗和数据库是不是一致  不一致则直接提示异常
        MessageInfo dbMessageInfo = new MessageInfo();
        long timeout = dbMessageInfo.getTimeout() * 1000;// 5分钟的毫秒
        long now = new Date().getTime();
        long sendMessage = dbMessageInfo.getCreateTime().getTime();//发送短信的时间毫秒值
        if((sendMessage + timeout) < now){
            //已经过期
            response.setCode(10001);
            response.setMessage("您的验证码已经过期了");

        }else if(!dbMessageInfo.getCode().equals(request.getCode())){
            //验证码不正确
            response.setCode(10001);
            response.setMessage("您的验证码错误");
            return response;
        }
        //2 验证码通过
            //2.1 通过手机号查询userInfo，
        UserInfo userInfo = userInfoService.getUserInfoByUserMobile(request.getMobile());
            //2.2 如果有记录，说明他是登陆 直接返回信息
            //2.3 如果没有记录，注册，保存用户信息， 返回信息
        //2.4注册 事务操作 一定要写到service
        response.setInfo(userInfo);
        response.setCode(10000);
        response.setMessage("成功");
        return response;
    }


    @GetMapping("/getUser/{userMobile}")
    public UserInfo userGet(@PathVariable String userMobile) {
        UserInfo userInfo = userInfoService.getUserInfoByUserMobile(userMobile);
        return userInfo;
    }
    /**
     * 新建用户信息
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/addUser")
    public UserInfo insertUser(@RequestBody UserInfo userInfo) {
        userInfoService.insertUser(userInfo);
        return userInfo;
    }
    /*更新用户信息
    @param id
    @return
    * */
    @PutMapping("/userInfo/{userId}")
    public UserInfo updateUser(@RequestBody UserInfo userInfo, @PathVariable String id) {
        if (userInfo.getUserId() == id) {
            userInfoService.updateUser(userInfo);
        }
        return userInfo;
    }
}
