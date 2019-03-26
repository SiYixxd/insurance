package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.CategoryRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.InsuranceCategory;
import com.wanghuan.model.sys.vo.PlanVO;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import com.wanghuan.service.sys.InsuranceCategoryService;
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
public class InsuranceCategoryController {
    @Resource(name = "insuranceCategoryServiceImpl")
    private InsuranceCategoryService insuranceCategoryService;



/**
     * 新建保险计划
     * @param request
     * @return
     */

    @PostMapping(value = "/insertCategory")
    public BaseResponse insertPlan(@RequestBody CategoryRequest request){
        BaseResponse response = new BaseResponse();
        try{
            InsuranceCategory category = new InsuranceCategory();
            BeanUtils.copyProperties(request,category);
            category.setCreateTime(new Date());
            category.setCategoryId(IdGeneratorUtil.generatId());
            category.setDeleteStatus(0);
            insuranceCategoryService.insertCategory(category);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            InsuranceDetailVO vo = new InsuranceDetailVO();
            vo.setPlanList(new java.util.ArrayList<PlanVO>());
            response.setData(vo);

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

    @PostMapping(value = "/removeCategory")
    public BaseResponse removeCategory(@RequestBody CategoryRequest request){
        BaseResponse response = new BaseResponse();
        try {
            insuranceCategoryService.removeCategory(request.getCategoryId());
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

    @PostMapping(value = "/updateCategory")
    public BaseResponse updateCategory(@RequestBody CategoryRequest request ){
        BaseResponse response = new BaseResponse();
        try {
            InsuranceCategory category = new InsuranceCategory();
            BeanUtils.copyProperties(request,category);
            insuranceCategoryService.updateCategory(category);
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

    @PostMapping(value = "/findCategoryById")
    public BaseResponse findCategoryById(@RequestBody CategoryRequest request){
        BaseResponse response = new BaseResponse();
        try {
            InsuranceCategory category = insuranceCategoryService.findCategoryById(request.getCategoryId());
            response.setData(category);
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
