package com.wanghuan.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MessageInfoDao {
    /*随机生成code存入数据库；
    通过第三方向用户发送code；
    通过用户mobile获取code；
    判断code是否过时，false发送信息；
    true比对code，
    false发送信息，
    true保存用户信息；
    * */
    public String getCode (@Param("mobile") String mobile);
}
