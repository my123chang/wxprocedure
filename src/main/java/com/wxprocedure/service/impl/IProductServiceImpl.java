package com.wxprocedure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxprocedure.entity.Product;
import com.wxprocedure.mapper.ProductMapper;
import com.wxprocedure.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商品service实现类
 */
@Service("productService")
public class IProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
}
