package com.wanghuan.dao;

import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.model.sys.UserSign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSignDao {
    //如果新注册的用户没有签到过，可以新建用户，把用户插入到table中，
    void insertSign(UserSign userSign);

    //完成签到功能
    void updateSign(UserSign userSign);

    //
    UserSign findSignStatus(String userId);


    void updateIrregularSign();

    void updateContinousSign();

    void uopdateAll();
}
