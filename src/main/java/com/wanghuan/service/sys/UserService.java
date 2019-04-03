package com.wanghuan.service.sys;

import java.util.List;

import com.wanghuan.model.sys.UserEntity;

public interface UserService {
    public void insert(UserEntity userEntity);

    public void del(UserEntity userEntity);

    /**
     * 通过登录名得到用户信息
     *
     * @param loginName
     * @return
     */
    public UserEntity getUserEntityByLoginName(String loginName);

    /**
     * 获取user列表
     *
     * @param loginName
     * @param pageSize
     * @param page
     * @return
     */
    public List<UserEntity> usersList(String loginName, int pageSize, int start);

    /**
     * 获取user列表的总量
     *
     * @param loginName
     * @param pageSize
     * @param page
     * @return
     */
    public Integer usersSize(String loginName, int pageSize, int start);

    /**
     * 删除用户信息
     *
     * @param groupId
     */
    public void deleteUsers(List<String> groupId);
}
