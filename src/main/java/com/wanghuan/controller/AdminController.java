package com.wanghuan.controller;

import com.wanghuan.controller.request.AdminPageRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.Admin;
import com.wanghuan.model.sys.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class AdminController {


    @CrossOrigin
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody AdminPageRequest adminPageRequest) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("成功");
        Admin user = new Admin();
        user.setPassword("123");
        user.setUsername("xiaoming");
        response.setUser(user);
        return response;
    }
}