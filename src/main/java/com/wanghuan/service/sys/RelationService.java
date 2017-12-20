package com.wanghuan.service.sys;

import java.util.List;

import com.wanghuan.model.sys.RelationEntity;

public interface RelationService {

	/**
	 * 通过userId得到关系List
	 * @param userId
	 * @return
	 */
	public List<RelationEntity> getRelationByUserId(int userId);

	
}