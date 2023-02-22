package com.wxprocedure.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxprocedure.entity.BigType;
import com.wxprocedure.entity.Product;
import com.wxprocedure.entity.R;
import com.wxprocedure.entity.SmallType;
import com.wxprocedure.service.IBigTypeService;
import com.wxprocedure.service.IProductService;
import com.wxprocedure.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品大类controller
 */

@RestController
@RequestMapping("/bigType")
public class BigTypeController {

    @Autowired
    private IBigTypeService bigTypeService;

    @Autowired
    private ISmallTypeService smallTypeService;

    @Autowired
    private IProductService productService;

    /**
     * 查询所有商品大类
     */
    @GetMapping("/findAll")
    public R findAll() {
        List<BigType> bigTypeList = bigTypeService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("message", bigTypeList);
        return R.ok(map);
    }

    /**
     * 获取所有菜单信息
     * @return
     */
    @GetMapping("/findCategories")
    public R findCategories(){
        List<BigType> bigTypeList = bigTypeService.list();
        for (BigType bigType:bigTypeList){
            List<SmallType> smallTypeList = smallTypeService.list(new QueryWrapper<SmallType>().eq("bigTypeId", bigType.getId()));
            bigType.setSmallTypesList(smallTypeList);
            for (SmallType smallType:smallTypeList){
                List<Product> productList = productService.list(new QueryWrapper<Product>().eq("typeId", smallType.getId()));
                smallType.setProductList(productList);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("message", bigTypeList);
        return R.ok(map);
    }

}
