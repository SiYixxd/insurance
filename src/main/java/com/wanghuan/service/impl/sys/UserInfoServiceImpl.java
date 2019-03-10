package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.UserInfoDao;
import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.service.sys.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao ;


    @Override
    public void insertUser(UserInfo userInfo) {
        userInfoDao.insertUser(userInfo);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userInfoDao.updateUser(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUserMobile(String userMobile) {
            return userInfoDao.getUserInfoByUserMobile(userMobile);
    }
}
