package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.InsuranceCategoryDao;
import com.wanghuan.model.sys.InsuranceCategory;
import com.wanghuan.service.sys.InsuranceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "insuranceCategoryServiceImpl")
@Transactional
public class InsuranceCategoryServiceImpl implements InsuranceCategoryService {
    @Autowired
    private InsuranceCategoryDao insuranceCategoryDao;

    @Override
    public void insertCategory(InsuranceCategory insuranceCategory) {
        insuranceCategoryDao.insertCategory(insuranceCategory);
    }

    @Override
    public void removeCategory(String id) {
        insuranceCategoryDao.removeCategory(id);
    }

    @Override
    public void updateCategory(InsuranceCategory insuranceCategory) {
        insuranceCategoryDao.updateCategory(insuranceCategory);
    }

    @Override
    public InsuranceCategory findCategoryById(String id) {
        return insuranceCategoryDao.findCategoryById(id);
    }
}
