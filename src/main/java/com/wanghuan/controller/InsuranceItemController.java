package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.InsuranceRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import com.wanghuan.model.sys.vo.InsuranceItemVO;
import com.wanghuan.service.sys.InsuranceItemService;
import com.wanghuan.utils.IdGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Transactional
@RestController
public class InsuranceItemController {
    @Resource(name = "insuranceItemServiceImpl")
    private InsuranceItemService insuranceItemService;


    /**
     * 新建保险信息
     * @param request
     * @return
     */
    @PostMapping(value = "/insertInsurance")
    public BaseResponse insertInsurance(@RequestBody InsuranceRequest request){
        BaseResponse response = new BaseResponse();
        try {
            InsuranceItem insurance = new InsuranceItem();
            BeanUtils.copyProperties(request,insurance);
            insurance.setInsuranceId(IdGeneratorUtil.generatId());
            insurance.setCreateTime(new Date());
            insurance.setDeleteStatus(0);
            insuranceItemService.insertInsurance(insurance);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("新建失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 移除一个保险
     * @param request
     * @return
     */
    @PostMapping(value = "/removeInsurance")
    public BaseResponse removeInsurance(@RequestBody InsuranceRequest request){
        BaseResponse response = new BaseResponse();
        try {
            insuranceItemService.removeInsurance(request.getInsuranceId());
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("删除失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }

    }





    /**
     * 更新保险信息
     * @param request
     * @return
     */
    @PostMapping(value = "/updateInsurance")
    public BaseResponse updateInsurance(@RequestBody InsuranceRequest request ){
        BaseResponse response = new BaseResponse();
        try {
            InsuranceItem insurance = new InsuranceItem();
            BeanUtils.copyProperties(request,insurance);
            insuranceItemService.updateInsurance(insurance);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("更新失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }



    /**
     * 根据id查询保险信息。
     * @param request
     * @return
     */
    @PostMapping(value = "/findInsuranceById")
    public BaseResponse findInsuranceById(@RequestBody InsuranceRequest request){
        BaseResponse response = new BaseResponse();
        try {
            InsuranceItemVO insurance = insuranceItemService.findInsuranceById(request.getInsuranceId());
            response.setData(insurance);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("查找失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }

    }


    /*
     *通过categoryId来查询属于这个险种的保险item有多少，具体是什么样的。返回类型是item还是list
     * 应该是返回一个itemList，可以放很多，也可以是一个，也可以是空。
     *
     */
    @PostMapping(value = "/findInsuranceCategory")
    public BaseResponse findInsuranceCategory(@RequestBody InsuranceRequest request){
        BaseResponse response = new BaseResponse();
        try {
            List insurance = insuranceItemService.findInsuranceCategory(request.getCategoryId());
            response.setData(insurance);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("查找失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

    /**
     * 分类查询出数以
     * @param request
     * @return
     */
    @PostMapping(value = "/findInsuranceDetail")
    public BaseResponse findInsuranceDetail(@RequestBody InsuranceRequest request){
        BaseResponse response = new BaseResponse();
        try {
            //需要返回的是一个list，list中存放着各种保险信息，包括该保险属于的保险计划。而保险计划也是一个list，存放在该保险计划的各种信息，保险计划中有一个planItemList
            //大list=其他信息+中list   中list=其他信息+小型list 小list=各种planItem信息。
            InsuranceDetailVO detail = insuranceItemService.findInsuranceDetail(request.getInsuranceId());
            response.setData(detail);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("查找失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

    /**
     * 分类查询出数以
     * @param request
     * @return
     */
    @PostMapping(value = "/findInsuranceDetail2")
    public BaseResponse findInsuranceDetail2(@RequestBody InsuranceRequest request){
        BaseResponse response = new BaseResponse();
        try {
            InsuranceDetailVO detail = insuranceItemService.findInsuranceDetail2(request.getInsuranceId());
            response.setData(detail);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("查找失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }



}

