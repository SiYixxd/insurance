package com.wanghuan.service.sys;


import com.wanghuan.model.sys.InsuranceCompany;

public interface InsuranceCompanyService {
    //增加一个保险公司
    void insertCompany(InsuranceCompany company);
    //删除一个保险公司
    void removeCompany(String id);
    //更新保险公司数据
    void updateCompany(InsuranceCompany company);
    //通过id查找保险公司
    InsuranceCompany findCompanyById(String id);
}
