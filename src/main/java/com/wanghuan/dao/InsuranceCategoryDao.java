package com.wanghuan.dao;

import com.wanghuan.model.sys.InsuranceCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsuranceCategoryDao {

    void insertCategory(InsuranceCategory insuranceCategory);

    void removeCategory(String id);

    //更新保险信息
    void updateCategory(InsuranceCategory insuranceCategory);

    //查询保险
    InsuranceCategory findCategoryById(String id);


}
