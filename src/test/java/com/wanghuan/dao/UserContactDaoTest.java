package com.wanghuan.dao;

import com.wanghuan.WhSpringBootApplicationTests;
import com.wanghuan.model.sys.UserContact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserContactDaoTest  {
    @Autowired
    UserContactDao userContactDao;


    @Test
    public void testInsert(){
        UserContact userContact = new UserContact();
        userContactDao.insertContact(userContact);;
    }
}
