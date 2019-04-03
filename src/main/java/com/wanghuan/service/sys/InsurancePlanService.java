package com.wanghuan.service.sys;


import com.wanghuan.model.sys.InsurancePlan;
import com.wanghuan.model.sys.vo.PlanVO;

import java.util.List;

public interface InsurancePlanService {
    //增加一个保险
    void insertPlan(InsurancePlan insurancePlan);

    //移除一个保险
    void removePlan(String id);

    //更新保险信息
    void updatePlan(InsurancePlan insurancePlan);

    //查询保险
    InsurancePlan findPlanById(String id);

    List<InsurancePlan> findPlanByInsuranceId(String insuranceId);

    //
    List<PlanVO> findPlanVOByInsuranceId(String insuranceId);
}
