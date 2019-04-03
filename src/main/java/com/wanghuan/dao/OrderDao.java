package com.wanghuan.dao;

import com.wanghuan.model.sys.Order;
import com.wanghuan.model.sys.vo.OrderDetailVO;
import com.wanghuan.model.sys.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;


import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {
    void insertOrder(Order order);

    void updateOrder(Order order);

    List<OrderVO> findOrderPage(Map<String,Object> map, RowBounds rowBounds);

    OrderDetailVO showOrderDetails(String orderId);

    Order  showOrderDetail(String orderId);

}
