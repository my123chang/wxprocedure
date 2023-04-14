package com.wxprocedure.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxprocedure.entity.Product;
import com.wxprocedure.entity.ProductSwiperImage;
import com.wxprocedure.entity.R;
import com.wxprocedure.service.IProductService;
import com.wxprocedure.service.IProductSwiperImageService;
import org.bouncycastle.util.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 商品controller
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductSwiperImageService productSwiperImageService;

    /**
     * 查询轮播商品
     */
    @GetMapping("/findSwiper")
    public R findSwiper(){
       List<Product> swiperProductList = productService.list(new QueryWrapper<Product>().eq("isSwiper", true).orderByAsc("swiperSort" ));
       Map<String,Object> map = new HashMap<>();
       map.put("message", swiperProductList);
       return R.ok(map);
    }

    /**
     * 查询热门商品前8个
     * @return
     */
    @GetMapping("/findHot")
    public R findHot(){
        Page<Product> page = new Page<>(0,8);
        Page<Product>pageProduct = productService.page(page,new QueryWrapper<Product>().eq("isHot",true).orderByAsc("hotDateTime"));
        List<Product> hotProductList = pageProduct.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("message", hotProductList);
        return R.ok(map);

    }

    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public R detail(Integer id){
        Product product = productService.getById(id);
        List<ProductSwiperImage> productSwiperImagesList = productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId", product.getId()).orderByAsc("sort"));
        product.setProductSwiperImagesList(productSwiperImagesList);
        Map<String,Object> map = new HashMap<>();
        map.put("message", product);
        return R.ok(map);

    }

    /**
     * 商品搜索
     * @param q
     * @return
     */
    @GetMapping("/search")
    public R search(String q){
        List<Product> productList = productService.list(new QueryWrapper<Product>().like("name", q));
        Map<String,Object> map = new HashMap<>();
        map.put("message", productList);
        return R.ok(map);
    }

}
