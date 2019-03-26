package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.InsuranceCompanyDao;
import com.wanghuan.model.sys.InsuranceCompany;
import com.wanghuan.service.sys.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "insuranceCompanyServiceImpl")
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {
    @Autowired
    private InsuranceCompanyDao insuranceCompanydao;

    @Override
    public void insertCompany(InsuranceCompany company) {
        insuranceCompanydao.insertCompany(company);
    }

    @Override
    public void removeCompany(String id) {
        insuranceCompanydao.removeCompany(id);
    }

    @Override
    public void updateCompany(InsuranceCompany company) {
        insuranceCompanydao.updateCompany(company);
    }

    @Override
    public InsuranceCompany findCompanyById(String id) {
        InsuranceCompany company = insuranceCompanydao.findCompanyById(id);
        return company;
    }
}
