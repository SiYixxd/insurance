package com.wanghuan.service.impl.sys;

import com.wanghuan.common.Constants;
import com.wanghuan.common.InsuranceException;
import com.wanghuan.dao.InsuranceConsumerDao;
import com.wanghuan.dao.InsuranceItemDao;
import com.wanghuan.dao.OrderDao;
import com.wanghuan.model.sys.Consumer;
import com.wanghuan.model.sys.InsuranceItem;
import com.wanghuan.model.sys.Order;
import com.wanghuan.model.sys.UserContact;
import com.wanghuan.model.sys.dto.OrderDTO;
import com.wanghuan.model.sys.vo.*;
import com.wanghuan.service.sys.*;
import com.wanghuan.utils.IdGeneratorUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "insuranceOrderServiceImpl")
@Transactional
public class InsuranceOrderServiceImpl implements InsuranceOrederService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private InsuranceConsumerService insuranceConsumerService;

    @Autowired
    private InsuranceItemService insuranceItemService;
    @Autowired
    private UserContactService userContactService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private InsurancePlanService insurancePlanService;

    @Override
    @Transactional
    public void placeOrder(OrderDTO orderDTO) throws InsuranceException {
        InsuranceItemVO item = insuranceItemService.findInsuranceById(orderDTO.getInsuranceId()); //新增保险订单，用户传入的保险ID，判断该产品是否存在。
        //用户提交订单，参数为InsuranceOrderDTO。用户请求过来一个订单对象。
        //判断用户提交的订单中，产品ID是否存在，
        // 1.1如果不存在，抛出异常
        if (item == null) {
            throw new InsuranceException(10004, "该产品不存在！");
        }
        //1.2如果存在，继续下一步判断
        if (orderDTO.isSaveAct()) {
            //  如果为true，再选中的该被保人是否已经存为了常用联系人，
            List contactList = userContactService.findContactByUserId(orderDTO.getConsumerIdNum());//通过请求的参数获取被保人的id，
            //通过被保人id去查询常用联系人表中是否已经包含此人。
            if (contactList.size() == 0) {
                //表中没有该联系人，那么执行保存。
                UserContact userContact = new UserContact();
                //拷贝属性时需要一一对应，model中consumer和contact不一致。 todo
                userContact.setContactIdNumber(orderDTO.getConsumerIdNum());
                userContact.setContactName(orderDTO.getConsumerName());
                userContact.setUserId(orderDTO.getUserId());
                userContact.setDeleteStatus(0);
                userContact.setCreateTime(new Date());
                userContactService.insertContact(userContact);
            }
            //dto(service中需要的数据)
            insuranceConsumerService.findConsumerByOrderId(orderDTO.getConsumerIdNum());
            //如果为false，什么也不做。
            //2封装订单信息。
            // 3 调用dao保存订单。
            Order order = new Order();
            BeanUtils.copyProperties(orderDTO, order);
            order.setOrderId(IdGeneratorUtil.generatId());
            order.setCreateTime(new Date());
            orderDao.insertOrder(order);
            //todo 请求第三放保险公司接口  拿到返回数据  保险公司orderId
            //order.serInsurancePolicyId();
        }


    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }


    // 通过用户的id来查看用户的订单列表，参考咨询相关分页（用户查看和后台人员查看）
    @Override
    public List<OrderVO> findOrderPage(Map<String,Object> map) {
        int pageIndex = Integer.valueOf(map.get("page").toString());
        List<OrderVO> orderList = orderDao.findOrderPage(map, new RowBounds(pageIndex * Constants.PAGE_SIZE, Constants.PAGE_SIZE));
        return orderList;
    }


    @Override
    public OrderDetailVO showOrderDetail(String orderId) {
       //通过订单id查询出该订单的详情
        Order order  =  orderDao.showOrderDetail(orderId);
        OrderDetailVO vo = new OrderDetailVO();
        BeanUtils.copyProperties(order,vo);
        List<PlanVO> planList = insurancePlanService.findPlanVOByInsuranceId(vo.getInsuranceId());
        vo.setPlanList(planList);
          vo.setUserInfoList( userInfoService.getUserInfoVO(vo.getUserId()));
        vo.setConsumerList(insuranceConsumerService.findConsumerByOrderIdOnDetail(orderId));

      /*   //开始往orderDetailVO添加属性。
        //通过orderId获取到对应的被保人信息，再将被保人信息存入到list当中
        Consumer c = insuranceConsumerService.findConsumerByOrderId(orderDetailVO.getOrderId());
        orderDetailVO.setConsumerList((List<Consumer>) c);
        UserInfoVO userInfoVO = userInfoService.getUserInfoVO(orderDetailVO.getUserId());
        orderDetailVO.setUserInfoList((List<UserInfoVO>) userInfoVO);
        List<PlanVO> planList = insurancePlanService.findPlanVOByInsuranceId(orderDetailVO.getInsuranceId());
        orderDetailVO.setPlanList(planList);
        // 订单详情是InsuranceList，list中存放着order信息，该order所含保险的信息。计划的信息
        // (保险名称，时间，生效时间和过期时间，所属计划，投保人信息，被保人信息，)
        return orderDetailVO;*/

       return vo;
    }
}
