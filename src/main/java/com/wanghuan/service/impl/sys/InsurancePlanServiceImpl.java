package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.InsuranceItemDao;
import com.wanghuan.dao.InsurancePlanDao;
import com.wanghuan.dao.PlanItemDao;
import com.wanghuan.model.sys.InsurancePlan;
import com.wanghuan.model.sys.PlanItem;
import com.wanghuan.model.sys.vo.PlanVO;
import com.wanghuan.service.sys.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "insurancePlanServiceImpl")
public class InsurancePlanServiceImpl implements InsurancePlanService {
    @Autowired
    private InsurancePlanDao insurancePlanDao;
    @Autowired
    private PlanItemDao planItemDao;


    @Override
    public void insertPlan(InsurancePlan insurancePlan) {
        insurancePlanDao.insertPlan(insurancePlan);
    }

    @Override
    public void removePlan(String id) {
        insurancePlanDao.removePlan(id);
    }

    @Override
    public void updatePlan(InsurancePlan insurancePlan) {
        insurancePlanDao.updatePlan(insurancePlan);
    }

    @Override
    public InsurancePlan findPlanById(String id) {
        return   insurancePlanDao.findPlanById(id);
    }

    @Override
    public List<InsurancePlan> findPlanByInsuranceId(String insuranceId) {
        return null;
    }

    @Override
    public List<PlanVO> findPlanVOByInsuranceId(String insuranceId) {
        //通过保险Id查询出存放保险计划的list
        List<PlanVO> list = insurancePlanDao.findPlanVOByInsuranceId(insuranceId);
        //遍历这个list，其中有planId。
        list.forEach(vo->{
            //planItem  get到planId后通过这个id查询出 与它对应的planItem，然后返回一个存放planItem的list
            List<PlanItem> planItemList = planItemDao.findByPlanId(vo.getPlanId());
            //把得到的planItemList set到planVOlist中
            vo.setItemList(planItemList);
        });
        return list;
    }
}
