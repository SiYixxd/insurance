package com.wanghuan.dao;

import com.wanghuan.model.sys.UserInfo;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoDao {
    public ArrayList<UserInfo> select(@Param("userInfo")UserInfo userInfo);

    public void update(@Param("userInfo") UserInfo userInfo);

    public void insert(@Param("userInfo") UserInfo userInfo);
    /*根据电话号码查询用户信息
    * @return
    */
    public UserInfo getUserInfoByUserMobile(@Param("userMobile") String userMobile);
    /*增加一个用户
    * @param userInfo
    */
    public void insertUser(@Param("userInfo") UserInfo userInfo);
    /*更新一个用户
     * @param userInfo
     */
    public void updateUser(@Param("userInfo") UserInfo userInfo);
    /*更新用户密码
    *@param password
    * */
    public void updateUserPassword(@Param("password") String password);

}
