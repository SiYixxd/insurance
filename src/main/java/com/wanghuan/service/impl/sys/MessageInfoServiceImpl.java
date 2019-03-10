package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.MessageInfoDao;
import com.wanghuan.service.sys.MessageInfoService;
import org.springframework.stereotype.Service;

@Service(value = "messageInfoServiceImpl")
public class MessageInfoServiceImpl implements MessageInfoService {
    private MessageInfoDao messageInfoDao;
    @Override
    public String getCode(String mobile) {
        return messageInfoDao.getCode(mobile);
    }
}
