package com.wxprocedure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxprocedure.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 订单service接口
 */
public interface IOrderService extends IService<Order> {

    /**
     * 根据条件分页查询订单
     * @param map
     * @return
     */

    List<Order> list(Map<String, Object> map);

    /**
     * 根据条件查询订单总记录数
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);
}
