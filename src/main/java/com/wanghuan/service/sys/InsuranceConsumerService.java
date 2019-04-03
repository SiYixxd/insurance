package com.wanghuan.service.sys;

import com.wanghuan.model.sys.Consumer;

import java.util.List;

public interface InsuranceConsumerService {
    void insertConsumer(Consumer consumer);

    Consumer findConsumerByOrderId(String OrderId);

    List findConsumerByOrderIdOnDetail(String OrderId);
}
