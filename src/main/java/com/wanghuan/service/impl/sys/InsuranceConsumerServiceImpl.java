package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.InsuranceConsumerDao;
import com.wanghuan.model.sys.Consumer;
import com.wanghuan.service.sys.InsuranceConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "insuranceConsumerServiceImpl")
public class InsuranceConsumerServiceImpl  implements InsuranceConsumerService {

    @Autowired
    private InsuranceConsumerDao insuranceConsumerDao;

    @Override
    public void insertConsumer(Consumer consumer) {
        insuranceConsumerDao.insertConsumer(consumer);

    }

    @Override
    public Consumer findConsumerByOrderId(String orderId) {
        return insuranceConsumerDao.findConsumerByOrderId(orderId);

    }

    @Override
    public List findConsumerByOrderIdOnDetail(String orderId) {
        return insuranceConsumerDao.findConsumerByOrderIdOnDetail(orderId);
    }
}
