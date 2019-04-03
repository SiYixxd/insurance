package com.wanghuan.service.sys;


import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.vo.InsuranceDetailVO;
import com.wanghuan.model.sys.vo.InsuranceItemVO;

import java.util.List;

public interface InsuranceItemService {

    String findInsuranceAndUserInfoById();

    //增加一个保险
    void insertInsurance(InsuranceItem insuranceItem);

    //移除一个保险
    void removeInsurance(String id);

    //更新保险信息
    void updateInsurance(InsuranceItem insuranceItem);

    //查询保险
    InsuranceItemVO findInsuranceById(String id);

    List<InsuranceItemVO> findInsuranceCategory(String id);



    /**
     * 查询保险产品的详情 --- 第一种方法 程序中遍历查询 多次查询
     *
     * @param insuranceId
     * @return
     */
    InsuranceDetailVO findInsuranceDetail(String insuranceId);

    /**
     * 查询保险产品的详情 --- 第二 种方法 sql 一步实现
     *
     * @param insuranceId
     * @return
     */
    InsuranceDetailVO findInsuranceDetail2(String insuranceId);
}
