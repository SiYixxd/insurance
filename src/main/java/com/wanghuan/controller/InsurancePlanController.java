package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.PlanRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.InsurancePlan;
import com.wanghuan.service.sys.InsurancePlanService;
import com.wanghuan.task.BaseJob;
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

@RestController
@Slf4j
@Transactional

public class InsurancePlanController {
    @Resource(name = "insurancePlanServiceImpl")
    private InsurancePlanService insurancePlanService;


    /**
     * 新建保险计划
     * @param request
     * @return
     */
    @PostMapping(value = "/insertPlan")
    public BaseResponse insertPlan(@RequestBody PlanRequest request){
        BaseResponse response = new BaseResponse();
        try{
            InsurancePlan plan = new InsurancePlan();
            BeanUtils.copyProperties(request,plan);
            plan.setCreateTime(new Date());
            plan.setPlanId(IdGeneratorUtil.generatId());
            plan.setDeleteStatus(0);
            insurancePlanService.insertPlan(plan);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        }catch (Exception e){
            log.error("新建失败！","参数信息:" + request.toString(),e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 移除一个保险计划
     * @param request
     * @return
     */
    @PostMapping(value = "/removePlan")
    public BaseResponse removePlan(@RequestBody PlanRequest request){
        BaseResponse response = new BaseResponse();
        try {
            insurancePlanService.removePlan(request.getPlanId());
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
     * 更新保险计划
     * @param request
     * @return
     */
    @PostMapping(value = "/updatePlan")
    public BaseResponse updatePlan(@RequestBody PlanRequest request ){
        BaseResponse response = new BaseResponse();
        try {
            InsurancePlan plan = new InsurancePlan();
            BeanUtils.copyProperties(request,plan);
            insurancePlanService.updatePlan(plan);
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
     * 根据id查询保险计划信息。
     * @param request
     * @return
     */
    @PostMapping(value = "/findPlanById")
    public BaseResponse findPlanById(@RequestBody PlanRequest request){
        BaseResponse response = new BaseResponse();
        try {
            InsurancePlan plan = insurancePlanService.findPlanById(request.getPlanId());
            response.setData(plan);
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
