package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.UserContactDao;
import com.wanghuan.model.sys.UserContact;
import com.wanghuan.service.sys.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userContactServiceImpl")
public class UserContactServiceImpl implements UserContactService {
    @Autowired
    private UserContactDao userContactDao;


    @Override
    public void insertContact(UserContact userContact) {
        userContactDao.insertContact(userContact);
    }

    @Override
    public void removeContact(String contactId) {
        userContactDao.removeContact(contactId);
    }

    @Override
    public void updateContact(UserContact userContact) {
        userContactDao.updateContact(userContact);
    }

    @Override
    public List<UserContact> findContactByUserId(String userId) {
        return userContactDao.findContactByUserId(userId);
    }
}
