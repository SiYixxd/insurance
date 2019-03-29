package com.wanghuan.dao;

import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.PlanItem;
import com.wanghuan.model.sys.vo.InsuranceItemVO;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuranceItemDao {
    //增加一个保险
    void insertInsurance(InsuranceItem insuranceItem);
    //移除一个保险
    void removeInsurance(String id);
    //更新保险信息
    void updateInsurance(InsuranceItem  insuranceItem);
    //查询保险
    InsuranceItemVO findInsuranceById(String id);


    //通过categoryId分类查询
    List<InsuranceItemVO> findInsuranceCategory(String id);

    //通过categoryId来查询产品详情
    InsuranceDetailVO findInsuranceDetail(String insuranceId);


    List<PlanItem> findByPlanId(String planId);
}
