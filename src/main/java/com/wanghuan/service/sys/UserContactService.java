package com.wanghuan.service.sys;


import com.wanghuan.model.sys.UserContact;

import java.util.List;

public interface UserContactService {
    //增
    void insertContact(UserContact userContact);

    //删
    void removeContact(String contactId);

    //改
    void updateContact(UserContact userContact);

    //通过用户id查找用户的常用联系人
    List<UserContact> findContactByUserId(String userId);
}
