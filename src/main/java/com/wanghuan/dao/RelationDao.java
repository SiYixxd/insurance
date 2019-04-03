package com.wanghuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wanghuan.model.sys.RelationEntity;

@Mapper
public interface RelationDao {

    /**
     * 通过userId得到关系List
     *
     * @param userId
     * @return
     */
    public List<RelationEntity> getRelationByUserId(@Param("userId") int userId);

    /**
     * 通过userId删除关系
     *
     * @param userId
     */
    public void delById(@Param("userId") int userId);

    /**
     * 批量插入关系数据
     *
     * @param relationList
     */
    public void insertRelations(List<RelationEntity> relationList);
}
