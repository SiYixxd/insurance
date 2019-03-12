package com.wanghuan.service.sys;

import com.wanghuan.model.sys.UserInfo;

public interface UserInfoService {
    //添加新用户
    public void insertUser(UserInfo userInfo);
    //更新用户信息
    public void updateUser(UserInfo userInfo);
    /*通过手机号查找用户
    * @param userMobile
    * @return
    * */
    public UserInfo getUserInfoByUserMobile(String userMobile);
    //更新用户密码
    public void updateUserPassword(String password);

}
