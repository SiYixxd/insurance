package com.wanghuan.dao;

import com.wanghuan.model.sys.InsurancePlan;
import com.wanghuan.model.sys.vo.PlanVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsurancePlanDao {
    //增加一个保险
    void insertPlan(InsurancePlan insurancePlan);

    //移除一个保险
    void removePlan(String id);

    //更新保险信息
    void updatePlan(InsurancePlan insurancePlan);

    //查询保险
    InsurancePlan findPlanById(String id);

    List<PlanVO> findPlanVOByInsuranceId(String insuranceId);

}
