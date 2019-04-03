package com.wanghuan.service.sys;

import com.wanghuan.common.InsuranceException;
import com.wanghuan.model.sys.Order;
import com.wanghuan.model.sys.dto.OrderDTO;
import com.wanghuan.model.sys.vo.OrderDetailVO;
import com.wanghuan.model.sys.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface InsuranceOrederService {

    void placeOrder(OrderDTO orderDTO) throws InsuranceException;

    void updateOrder(Order order);

    List<OrderVO> findOrderPage(Map<String,Object> map);

   OrderDetailVO showOrderDetail(String orderId);

}
