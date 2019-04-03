package com.wanghuan.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wanghuan.dao.InsuranceItemDao;
import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import com.wanghuan.model.sys.vo.InsuranceItemVO;
import com.wanghuan.model.sys.vo.PlanVO;
import com.wanghuan.service.sys.InsuranceItemService;
import com.wanghuan.service.sys.InsurancePlanService;
import com.wanghuan2.dubboService.ProviderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "insuranceItemServiceImpl")
@Transactional
public class InsuranceItemServiceImpl implements InsuranceItemService {
    @Autowired
    private InsuranceItemDao insuranceItemDao;

    @Reference(version = "1.0.0", timeout = 10000, retries = 2)
    private ProviderService providerService;

    @Autowired
    private InsurancePlanService insurancePlanService;

    @Override
    public String findInsuranceAndUserInfoById() {
        return providerService.findById("userId");
    }

    @Override
    public void insertInsurance(InsuranceItem insuranceItem) {
        insuranceItemDao.insertInsurance(insuranceItem);
    }

    @Override
    public void removeInsurance(String id) {
        insuranceItemDao.removeInsurance(id);
    }

    @Override
    public void updateInsurance(InsuranceItem insuranceItem) {
        insuranceItemDao.updateInsurance(insuranceItem);
    }

    @Override
    public InsuranceItemVO findInsuranceById(String id) {
        return insuranceItemDao.findInsuranceById(id);

    }

    @Override
    public List<InsuranceItemVO> findInsuranceCategory(String id) {
        return insuranceItemDao.findInsuranceCategory(id);
    }

    @Override
    public InsuranceDetailVO findInsuranceDetail(String insuranceId) {
        //新建一个需要返回的对象，vo
        InsuranceDetailVO vo = new InsuranceDetailVO();
        //再通过保险Id获取到保险的详细信息。
        InsuranceItemVO item = insuranceItemDao.findInsuranceById(insuranceId);
       //再把获取到的保险详情copy到新建的vo中
        BeanUtils.copyProperties(item, vo);
        //新建的vo中的list还是空的，而里面封装着PlanVo，PlanVo中还有一个list
        //调用保险计划的Service，通过保险id来获取到其所属的保险计划。
        List<PlanVO> planList = insurancePlanService.findPlanVOByInsuranceId(insuranceId);
        //将获取的保险计划list set 到vo中去。再返回。
        vo.setPlanList(planList);
        return vo;
    }

    @Override
    public InsuranceDetailVO findInsuranceDetail2(String insuranceId) {
        InsuranceDetailVO detail = insuranceItemDao.findInsuranceDetail(insuranceId);
        return detail;
    }
}
