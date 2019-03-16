package com.wanghuan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wanghuan.model.sys.PageResult;
import com.wanghuan.model.sys.UserEntity;
import com.wanghuan.service.sys.UserService;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	public Map<String, UserEntity> cacheMap = new HashMap<>();

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@GetMapping("/user123/{loginName}")
	public UserEntity userGet(@PathVariable String loginName) {


		UserEntity userEntity
				= userService.getUserEntityByLoginName(loginName);
		// 复杂对象
		log.debug("The method is ending");

		return userEntity;
	}

	/**
	 * 获取user表数据
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/users")
	public PageResult usersList(String loginName, int pageSize, int page) {

		PageResult pageResult = new PageResult();
		pageResult.setData(userService.usersList(loginName, pageSize, page * pageSize));
		pageResult.setTotalCount(userService.usersSize(loginName, pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/users/user")
	public UserEntity insertUser(@RequestBody UserEntity userEntity) {
//		userService.insertUser(userEntity);
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/users/{id}")
	public UserEntity updateUser(@RequestBody UserEntity userEntity, @PathVariable int id) {
		if (userEntity.getId() == id) {
//			userService.updateUser(userEntity);
		}
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/users")
	public List<String> deleteUsers(@RequestBody List<String> groupId) {
		userService.deleteUsers(groupId);
		return groupId;
	}
}
