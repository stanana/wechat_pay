package com.xmcc.service;

import com.lly835.bestpay.model.PayResponse;
import com.xmcc.entity.OrderMaster;

public interface PayService {
    //根据ID查询订单
    OrderMaster findOrderById(String orderId);

    PayResponse create(OrderMaster orderMaster);

    void weixin_notify(String notifyData);
}
