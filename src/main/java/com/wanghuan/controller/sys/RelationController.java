package com.wanghuan.controller.sys;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wanghuan.model.sys.RelationEntity;
import com.wanghuan.service.sys.RelationService;

@RestController
public class RelationController {

	private Logger log = LoggerFactory.getLogger(RelationController.class);

	@Resource(name = "relationServiceImpl")
	private RelationService relationService;

	/**
	 * 通过userId得到关系List
	 * @param userId
	 * @return
	 */
	@GetMapping("/relations/{userId}")
	public List<RelationEntity> getRelationByUserId(@PathVariable int userId){
		log.debug("The method is ending");
		return relationService.getRelationByUserId(userId);
	}
}