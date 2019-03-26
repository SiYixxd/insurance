package com.wanghuan.dao;

import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.PlanItem;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import com.wanghuan.model.sys.vo.InsuranceItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanItemDao {

    List<PlanItem> findByPlanId(String planId);
}
