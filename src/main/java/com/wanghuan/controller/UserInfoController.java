package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.common.SmsCode;
import com.wanghuan.controller.request.PasswordRequest;
import com.wanghuan.controller.request.SetRequest;
import com.wanghuan.controller.request.RegisterRequest;
import com.wanghuan.controller.request.SignRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.MessageInfo;
import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.service.sys.RegistService;
import com.wanghuan.service.sys.UserInfoService;
import com.wanghuan.service.sys.UserSignService;
import com.wanghuan.utils.MD5Util;
import com.wanghuan.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    //  @Resource(name = "messageInfoServiceImpl")
    // private MessageInfoService messageInfoService;

    @Resource(name = "userSignServiceImpl")
    private UserSignService userSignService;


    @Autowired
    private RegistService registService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取验证码
     *
     * @param userMobile 用户手机号
     * @return
     */
    @GetMapping("/getCheckcode/{userMobile}")
    public String getCheckCode(@PathVariable String userMobile) {
        //1 随机一个验证码
        String code = SmsCode.getCode();//获取随机验证码
        try {
            //2 请求第三方短信接口发送短信
            //调用接口方法，发送短信到手机 userMobile接收短信的手机号码
            SmsCode.sendCode(userMobile, code);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【发送验证码异常】", "手机号是" + userMobile, e);
        }
        // insert into t_msm ()value() ;把code保存到数据库 获取String类型的code对象
        // 3 封装数据， 保存数据
        MessageInfo info = new MessageInfo();
        info.setCreateTime(new Date());
        info.setMobile(userMobile);
        info.setCode(code);
        //  messageInfoService.saveMessageInfo(info);
        //4 发送成功则给用户返回数据
        return code;
    }

    /**
     * 短信验证码注册和登陆接口
     *
     * @param request request请求中包含了用户的手机号
     * @return
     */
   /* @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody RegisterRequest request) {
        BaseResponse response = new BaseResponse();
        //1 验证验证码是不是正确
        //1.1 根据手机号从t_msm中查到最近一条【order by createTime DESC limit 1】
        String tempMobile = request.getMobile();
     //   MessageInfo messageInfo = messageInfoService.getMsgByMobile(tempMobile);
        //1.2 判断手机号的验证码记录是不是有记录 如果没有返回一个报错信息提示验证码不存在
     //   if (messageInfo.getCode() == null || messageInfo.getCode().equals("")) {
            //验证码不存在
            response.setCode(10001);
            response.setMessage("您的验证不存在");
     //   } else {
            //1.3 如果有 判断截止时间  并且判断用户的验证吗和数据库是不是一致  不一致则直接提示异常
     //       long timeout = messageInfo.getTimeout();
            long now = new Date().getTime(); //记录当前时间
     //       long sendMessage = messageInfo.getCreateTime().getTime();//发送短信的时间毫秒值
            if ((sendMessage + timeout) < now) {    //发送短信的时间+5min要大于当前时间，如果小于当前时间说明短信已经过期
                response.setCode(10001);
                response.setMessage("您的验证码已经过期了");
            } else if (!messageInfo.getCode().equals(request.getCode())) {
                //验证码不正确
                response.setCode(10001);
                response.setMessage("您的验证码错误");
                return response;
            }
        }

        //2 验证码通过  进行注册或者登陆
        try {
            UserInfo userInfo = registService.signUp(request.getMobile(), "随机生成一个userName", request.getCode(), "-");
           *//* UserSign userSign = new UserSign();
            userSign.setUserId(userInfo.getUserId());
            userSignService.insertSign(userSign)*//*
            ;
            response.setInfo(userInfo);
            response.setCode(10000);
            response.setMessage("成功");
            return response;
        } catch (Exception e) {
            //在服务器中logstash 打印出异常日志
            log.error("[用户注册] 用户注册未知异常", "参数信息:" + request.toString(), e);
            response.setMessage("抱歉，错误了！");
            response.setCode(10001);
            return response;
        }
    }
*/

    /**
     * 短信验证码注册和登陆接口
     *
     * @param request request请求中包含了用户的手机号
     * @return
     */
    @PostMapping(value = "/register2", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    public BaseResponse register2(@RequestParam Map<String, String> request) {
        //这个map的名字叫做request，所以直接从request中过去对象就行了
        request.get("mobile");
        log.info("");
        return null;
    }


    @GetMapping("/getUser/{userMobile}")
    public UserInfo userGet(@PathVariable String userMobile) {
        UserInfo userInfo = userInfoService.getUserInfoByUserMobile(userMobile);
        return userInfo;
    }

    //用户通过用户id 更新自己的昵称和头像   头像传固定url地址 通过手机号get到userInfo
    @PostMapping(value = "/setUserInfo")
    public BaseResponse setUserInfo(@RequestBody SetRequest request) {
        BaseResponse response =  new BaseResponse();
        String mobile = request.getMobile();
        String imageUrl = request.getImageUrl();
        String userName = request.getUserName();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserMobile(mobile);
        //获取到userInfo后发现昵称与头像是默认，开始set
        userInfo.setUserName(userName);
        userInfo.setImageUrl(imageUrl);
        //设置完成后更新userInfo
        userInfoService.updateUser(userInfo);
        //需要把设置完成的信息返回给用户
        response.setData(userInfo);

        return response;
    }
  /* @PostMapping(value = "/setUserInfo2", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    public BaseResponse setUserInfo2(@RequestParam Map<String,String> request) {
        String mobile =  request.get("mobile");
        String imageUrl = request.get("imageUrl");
        String userName = request.get("userName");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserMobile(mobile);
        //获取到userInfo后发现昵称与头像是默认，开始set
        userInfo.setUserName(userName);
        userInfo.setImageUrl(imageUrl);
        //设置完成后更新userInfo
        userInfoService.updateUser(userInfo);
        //需要把设置完成的信息返回给用户
        return new BaseResponse(10000, "成功", userInfo);
    }*/

    @PostMapping(value = "/setUserPassword")
    // 用户设置自己的密码
    public BaseResponse setUserPassword(@RequestBody PasswordRequest passwordRequest) {
        //获取用户的输入的密码
        try {
            String password = passwordRequest.getPassword();
            String mobile = passwordRequest.getMobile();
            String md5Password = MD5Util.encrypt(mobile + password);
            userInfoService.updateUserPassword(md5Password, mobile);
            UserInfo userInfo = userInfoService.getUserInfoByUserMobile(mobile);
            userInfo.setPassword(md5Password);
            return new BaseResponse(10000, "密码设置成功");
        } catch (NoSuchAlgorithmException e) {
            log.error("未知异常", e);
            return new BaseResponse(10001, "设置异常");
        }
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


    /*    *
     * APP 通过redis签到的接口
     * @param request
     * @return
     */
    @PostMapping(value = "/userSign")
    public BaseResponse userSign(@RequestBody SignRequest request) throws Exception {
        BaseResponse response = new BaseResponse();
        try {
            userSignService.saveSignByRedis(request.getUserId());
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

}
