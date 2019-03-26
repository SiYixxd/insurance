package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.InsuranceItemDao;
import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import com.wanghuan.model.sys.vo.InsuranceItemVO;
import com.wanghuan.model.sys.vo.PlanVO;
import com.wanghuan.service.sys.InsuranceItemService;
import com.wanghuan.service.sys.InsurancePlanService;
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

    @Autowired
    private InsurancePlanService insurancePlanService;

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
        return insuranceItemDao.findInsuranceCategory(id) ;
    }

    @Override
    public InsuranceDetailVO findInsuranceDetail(String insuranceId) {
        InsuranceItemVO item = insuranceItemDao.findInsuranceById(insuranceId);
        InsuranceDetailVO vo = new InsuranceDetailVO();
        BeanUtils.copyProperties(item, vo);
        List<PlanVO> planList = insurancePlanService.findPlanVOByInsuranceId(insuranceId);
        vo.setPlanList(planList);
        return vo;
    }

    @Override
    public InsuranceDetailVO findInsuranceDetail2(String insuranceId) {
        InsuranceDetailVO detail = insuranceItemDao.findInsuranceDetail(insuranceId);
        return detail;
    }
}
