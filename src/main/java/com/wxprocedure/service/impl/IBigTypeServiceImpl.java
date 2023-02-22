package com.wxprocedure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxprocedure.entity.BigType;
import com.wxprocedure.mapper.BigTypeMapper;
import com.wxprocedure.service.IBigTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商品大类service实现类
 */
@Service("bigTypeService")
public class IBigTypeServiceImpl extends ServiceImpl<BigTypeMapper, BigType> implements IBigTypeService {

    @Autowired
    private BigTypeMapper bigTypeMapper;
}
