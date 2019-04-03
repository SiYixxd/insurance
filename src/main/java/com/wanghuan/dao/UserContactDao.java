package com.wanghuan.dao;

import com.wanghuan.model.sys.UserContact;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserContactDao {
    //增
    void insertContact(UserContact userContact);

    //删
    void removeContact(String contactIdNumber);

    //改
    void updateContact(UserContact userContact);

    // 查 参数是用户的id，根据用户的id查找用户的常用联系人，跟常用联系人的id没关系。查询出结果后返回一个list
    List<UserContact> findContactByUserId(String userId);
}
