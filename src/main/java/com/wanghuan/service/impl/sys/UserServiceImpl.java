package com.wanghuan.service.impl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanghuan.dao.UserDao;
import com.wanghuan.model.sys.UserEntity;
import com.wanghuan.service.sys.UserService;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insert(UserEntity userEntity) {
        userDao.insert(userEntity);
    }

    @Override
    public void del(UserEntity userEntity) {
        userDao.del(userEntity);
    }

    @Override
    public UserEntity getUserEntityByLoginName(String loginName) {
        return userDao.getUserEntityByLoginName(loginName);
    }

    @Override
    public List<UserEntity> usersList(String loginName, int pageSize, int start) {
        return userDao.usersList(loginName, pageSize, start);
    }

    @Override
    public Integer usersSize(String loginName, int pageSize, int start) {
        return userDao.usersSize(loginName, pageSize, start);
    }


    @Override
    public void deleteUsers(List<String> groupId) {
        userDao.deleteUsers(groupId);
    }

}
