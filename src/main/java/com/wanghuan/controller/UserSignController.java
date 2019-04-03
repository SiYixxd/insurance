package com.wanghuan.controller;

import com.insurance.model.sys.MessageInfo;
import com.wanghuan.common.Constants;
import com.wanghuan.common.InsuranceException;
import com.wanghuan.controller.request.SignRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.dubboService.DubboConsumerService;
import com.wanghuan.service.sys.InsuranceItemService;
import com.wanghuan.service.sys.UserSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@Slf4j
@Transactional
public class UserSignController {
    @Resource(name = "userSignServiceImpl")
    private UserSignService userSignService;


    @Resource
    private InsuranceItemService itemService;
    @Resource
    private DubboConsumerService consumerService;

    @GetMapping(value = "/testDubbo")
    public String updateSign() {
        return itemService.findInsuranceAndUserInfoById();
    }

    @GetMapping(value = "/testInsertMobile/{mobile}")
    public MessageInfo testInsertMobile(@PathVariable String mobile) {
        return consumerService.getMsgByMobile(mobile);
    }

    /**
     * 用户签到接口
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateSign")
    public BaseResponse updateSign(@RequestBody SignRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            userSignService.saveSign(request.getUserId());
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            if (e instanceof InsuranceException) {
                //判断自定义异常
                InsuranceException exception = (InsuranceException) e;
                response.setByExpcetion(exception);
                return response;
            } else {
                //其他运行时异常
                log.error("。。。。", e);
                response.setCode(Constants.ERROR_CODE);
                response.setMessage(Constants.ERROR_MESSAGE);
                return response;
            }
        }
    }


}
