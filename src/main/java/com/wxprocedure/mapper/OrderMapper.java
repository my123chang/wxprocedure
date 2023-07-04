package com.wxprocedure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxprocedure.entity.Order;

import java.util.List;
import java.util.Map;


/**
 * 订单mapper接口
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> list(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);
}
