package com.wanghuan.controller;


import com.wanghuan.common.Constants;
import com.wanghuan.common.InsuranceException;
import com.wanghuan.controller.request.OrderListRequest;
import com.wanghuan.controller.request.OrderRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.model.sys.Consumer;
import com.wanghuan.model.sys.Order;
import com.wanghuan.model.sys.dto.OrderDTO;
import com.wanghuan.model.sys.vo.OrderDetailVO;
import com.wanghuan.model.sys.vo.OrderVO;
import com.wanghuan.service.sys.InsuranceConsumerService;
import com.wanghuan.service.sys.InsuranceOrederService;
import com.wanghuan.utils.InsuranceLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Transactional
public class OrderController extends InsuranceLog {
    @Resource(name = "insuranceOrderServiceImpl")
    private InsuranceOrederService insuranceOrederService;

    @Resource
    private InsuranceConsumerService insuranceConsumerService;


    /**
     * @param request
     * @return
     */
    @PostMapping(value = "/placeOrder")
    public BaseResponse placeOrder(@RequestBody OrderRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //请求过来后直接将被保人信息存入数据库，因为一个被保人可能有很多张保单，所以不用考虑数据库中是否已经存在该consumer
            Consumer c = new Consumer();
            BeanUtils.copyProperties(request, c);
            insuranceConsumerService.insertConsumer(c);
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(request, orderDTO);
            insuranceOrederService.placeOrder(orderDTO);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (InsuranceException e) {
            logError("insertOrder","下单失败", "下单参数信息:" + request.toString(), e);
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
            return response;
        } catch (Exception e) {
            log.error("insertOrder","下单失败", "下单参数信息:" + request.toString(), e);
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 用户查看订单列表。
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/findOrderPage")
    public BaseResponse findOrderPage (@RequestBody OrderListRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //定义是否结束标记
            Boolean endFlag = false;
            //新建一个map，用来存放用户请求的参数
            Map<String, Object> map = new HashMap<>();
            //将用户求情的页码存放到map中。
            map.put("page", request.getPage());
            map.put("userId", request.getUserId());//想要通过哪些条件来获取数据，就往map中添加什么元素……
            //调用方法返回订单列表
            List<OrderVO> orderVOList = insuranceOrederService.findOrderPage(map);
            //比较list和pageSize的大小，如果list小，那么就是最后一页，设置结束标记为true。
            //如果还是list大，那么结束标记仍未false，继续显示列表。
            //   WHERE USER_ID = #{userId}
            if (orderVOList.size() < Constants.PAGE_SIZE)
                endFlag = true;
            response.setEndFlag(endFlag);
            response.setData(orderVOList);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

    /**
     * 返回订单详情
     * @param request
     * @return
     */
    @PostMapping(value = "showOrderDetail")
    public BaseResponse showOrderDetail(@RequestBody OrderRequest request) {
        BaseResponse response = new BaseResponse();
        try {
          OrderDetailVO  orderDetail= insuranceOrederService.showOrderDetail(request.getOrderId());
          //  OrderDetailVO o =    insuranceOrederService.showOrderDetail(request.getOrderId());
            response.setData(orderDetail);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }
}
