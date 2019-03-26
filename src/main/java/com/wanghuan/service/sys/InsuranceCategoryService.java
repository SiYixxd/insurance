package com.wanghuan.service.sys;


import com.wanghuan.model.sys.InsuranceCategory;

public interface InsuranceCategoryService {
    void insertCategory(InsuranceCategory insuranceCategory);
    void removeCategory(String id);
    void updateCategory(InsuranceCategory insuranceCategory);
    InsuranceCategory findCategoryById(String id);

}
