package com.wanghuan.dao;

import com.wanghuan.model.sys.Consumer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuranceConsumerDao {
    void insertConsumer(Consumer consumer);

    Consumer findConsumerByOrderId(String OrderId);

    List findConsumerByOrderIdOnDetail(String OrderId);
}
