package com.wanghuan.controller;


import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.UserContactRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.UserContact;
import com.wanghuan.service.sys.UserContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@Transactional
public class UserContactController {
    @Resource(name = "userContactServiceImpl")
    private UserContactService userContactService;


    /**
     * 增加一个常用联系人
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/insertContact")
    public BaseResponse insertContact(@RequestBody UserContactRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            UserContact contact = new UserContact();
            BeanUtils.copyProperties(request, contact);
            contact.setCreateTime(new Date());
            contact.setDeleteStatus(0);
            userContactService.insertContact(contact);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            log.error("新建失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 移除一个常用联系人
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/removeContact")
    public BaseResponse removeContact(@RequestBody UserContactRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            userContactService.removeContact(request.getContactIdNumber());
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            log.error("移除失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 更新一个常用联系人
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateContact")
    public BaseResponse updateContact(@RequestBody UserContactRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            UserContact contact = new UserContact();
            BeanUtils.copyProperties(request, contact);
            userContactService.updateContact(contact);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            log.error("更新失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 查找常用联系人
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/findContactByUserId")
    public BaseResponse findContactByUserId(@RequestBody UserContactRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            List<UserContact> contact = userContactService.findContactByUserId(request.getUserId());
            response.setData(contact);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            log.error("查找失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


}
