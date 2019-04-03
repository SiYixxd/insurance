package com.wanghuan.dao;

import com.wanghuan.model.sys.UserInfo;

import java.util.ArrayList;
import java.util.List;

import com.wanghuan.model.sys.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoDao {
    /*根据电话号码查询用户信息
     * @return
     */
    public UserInfo getUserInfoByUserMobile(@Param("userMobile") String userMobile);

    /*增加一个用户
     * @param userInfo
     */
    public void insertUser(UserInfo userInfo);

    /*更新一个用户
     * @param userInfo
     */
    public void updateUser(UserInfo userInfo);
    //void removeUser(String userId);

    /*更新用户密码
     *@param password
     * */
    public void updateUserPassword(@Param("password") String password, @Param("userMobile") String userMobile);

    List getUserInfoVO(String useId);

}
