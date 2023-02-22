package com.wxprocedure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxprocedure.entity.ProductSwiperImage;
import com.wxprocedure.mapper.ProductSwiperImageMapper;
import com.wxprocedure.service.IProductSwiperImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 产品轮播图片service实现类
 */
@Service("productSwiperImageService")
public class IProductSwiperImageServiceImpl extends ServiceImpl<ProductSwiperImageMapper, ProductSwiperImage> implements IProductSwiperImageService {

    @Autowired
    private ProductSwiperImageMapper productSwiperImageMapper;
}
