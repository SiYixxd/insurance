package com.wanghuan.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.wanghuan.dao.MessageInfoDao;
import com.wanghuan.model.sys.MessageInfo;
import com.wanghuan.service.sys.MessageInfoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class MessageInfoServiceImpl implements MessageInfoService {
    @Autowired
    private MessageInfoDao messageInfoDao;

    @Override
    public void insertCode(String mobile) {
    }

    @Override
    public MessageInfo getMsgByMobile(String mobile) {
        return messageInfoDao.getMsgByMobile(mobile);
    }

    @Override
    public void saveMessageInfo(MessageInfo info) {
        messageInfoDao.saveMessageInfo(info);
    }
}
