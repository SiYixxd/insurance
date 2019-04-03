package com.wanghuan.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.CompanyRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.InsuranceCompany;
import com.wanghuan.service.sys.InsuranceCompanyService;
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

@Slf4j
@Transactional
@RestController
public class InsuranceCompanyController {
    @Resource(name = "insuranceCompanyServiceImpl")
    private InsuranceCompanyService insuranceCompanyService;


    /**
     * 新建一个保险公司信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/insertCompany")
    public BaseResponse insertCompany(@RequestBody CompanyRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            InsuranceCompany company = new InsuranceCompany();
            BeanUtils.copyProperties(request, company);
            company.setCompanyId(IdGeneratorUtil.generatId());
            company.setCreateTime(new Date());
            company.setDeleteStatus(0);
            insuranceCompanyService.insertCompany(company);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("新建失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 删除一个公司信息，将status改为1；
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/removeCompany")
    public BaseResponse removeCompany(@RequestBody CompanyRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            insuranceCompanyService.removeCompany(request.getCompanyId());
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("删除失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

    /**
     * 更新公司信息，(logo和名称)
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/updateCompany")
    public BaseResponse updateCompany(@RequestBody CompanyRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            InsuranceCompany company = new InsuranceCompany();
            BeanUtils.copyProperties(request, company);
            insuranceCompanyService.updateCompany(company);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("更新失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }

    }


    /**
     * 根据id查询公司信息。
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/findCompanyById")
    public BaseResponse findCompanyById(@RequestBody CompanyRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            InsuranceCompany company = insuranceCompanyService.findCompanyById(request.getCompanyId());
            response.setData(company);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (BeansException e) {
            log.error("查找失败！", "参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }

    }


}
