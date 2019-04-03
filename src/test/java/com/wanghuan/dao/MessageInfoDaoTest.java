package com.wanghuan.dao;

import com.wanghuan.WhSpringBootApplicationTests;
import com.wanghuan.model.sys.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MessageInfoDaoTest extends WhSpringBootApplicationTests {

    @Autowired
    MessageInfoDao infoDao;

    private MessageInfo messageInfo;

    @Before
    public void init() {
        log.info("before 方法执行了");
    }

    @Test
    public void testSave() {
        MessageInfo info = new MessageInfo();


        //...

        infoDao.saveMessageInfo(info);
    }

    @Test
    public void testSave2() {
        MessageInfo info = new MessageInfo();


        //...

        infoDao.saveMessageInfo(info);
    }

}
