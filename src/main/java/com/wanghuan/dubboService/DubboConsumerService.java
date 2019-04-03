package com.wanghuan.dubboService;

import com.insurance.model.sys.MessageInfo;

public interface DubboConsumerService {

    String findById();

    void insertCode(String mobile);

    MessageInfo getMsgByMobile(String mobile);

}
