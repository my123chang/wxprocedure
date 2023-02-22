package com.wxprocedure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxprocedure.entity.SmallType;
import com.wxprocedure.mapper.SmallTypeMapper;
import com.wxprocedure.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商品小类service实现类
 */
@Service("SmallTypeService")
public class ISmallTypeServiceImpl extends ServiceImpl<SmallTypeMapper, SmallType> implements ISmallTypeService {

    @Autowired
    private SmallTypeMapper SmallTypeMapper;
}
