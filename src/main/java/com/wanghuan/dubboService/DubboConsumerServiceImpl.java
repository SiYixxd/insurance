package com.wanghuan.dubboService;

import com.alibaba.dubbo.config.annotation.Reference;
import com.insurance.model.sys.MessageInfo;
import org.springframework.stereotype.Component;
import com.wanghuan2.dubboService.ProviderService;
import com.insurance.service.sys.MessageInfoService;


@Component
public class DubboConsumerServiceImpl implements DubboConsumerService {

    /**
     * 注入生产者接口注解，version表示dubbo协议版本  timeout超时时间  retries超时后的重试次数
     */
    @Reference(version = "1.0.0", timeout = 10000, retries = 2)
    private ProviderService providerService;

    @Reference(version = "1.0.0", timeout = 10000, retries = 2)
    private MessageInfoService messageInfoService;

    @Override
    public String findById() {
        String result = providerService.findById("123");
        return "";
    }

    @Override
    public void insertCode(String mobile) {
        messageInfoService.insertCode(mobile);
    }

    /**
     * @param mobile
     * @return MessageInfo
     */
    @Override
    public MessageInfo getMsgByMobile(String mobile) {
        return messageInfoService.getMsgByMobile(mobile);
    }


}
