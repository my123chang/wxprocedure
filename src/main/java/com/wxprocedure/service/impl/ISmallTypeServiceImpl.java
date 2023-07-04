package com.wxprocedure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxprocedure.dto.SmallTypeSearchDto;
import com.wxprocedure.entity.BigType;
import com.wxprocedure.entity.PageBean;
import com.wxprocedure.entity.SmallType;
import com.wxprocedure.mapper.BigTypeMapper;
import com.wxprocedure.mapper.SmallTypeMapper;
import com.wxprocedure.service.ISmallTypeService;
import com.wxprocedure.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 商品小类service实现类
 */
@Service("smallTypeService")
public class ISmallTypeServiceImpl extends ServiceImpl<SmallTypeMapper, SmallType> implements ISmallTypeService {

    private final SmallTypeMapper smallTypeMapper;
    private final BigTypeMapper bigTypeMapper;

    public ISmallTypeServiceImpl(SmallTypeMapper smallTypeMapper, BigTypeMapper bigTypeMapper) {
        this.smallTypeMapper = smallTypeMapper;
        this.bigTypeMapper = bigTypeMapper;
    }

    @Override
    public List<SmallType> list(Map<String, Object> map) {
        return smallTypeMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return smallTypeMapper.getTotal(map);
    }

    @Override
    public Map<String, Object> search(SmallTypeSearchDto smallTypeSearchDto) {
        // 搜索全部大类
        List<BigType> bigTypeList = bigTypeMapper.selectList(null);


        PageBean pageBean = smallTypeSearchDto.getPageBean();
        String smallTypeName = smallTypeSearchDto.getSmallTypeName().trim();
        Page<SmallType> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        Page<SmallType> pageResult = this.page(page, new QueryWrapper<SmallType>().like(StringUtil.isNotEmpty(smallTypeName), "name", smallTypeName));
        Map<String, Object> resultMap = new HashMap<>();
        List<SmallType> smallTypeList = pageResult.getRecords().stream().map(smallType -> {
            // 小类拥有大类的ID，所以通过大类ID获取大类名称
            List<BigType> collect = bigTypeList.stream().filter(bigType ->
                    bigType.getId() == smallType.getBigTypeId()
            ).collect(Collectors.toList());

            smallType.setBigType(collect.get(0));
            return smallType;
        }).collect(Collectors.toList());
        resultMap.put("smallTypeList", smallTypeList);
        resultMap.put("total", pageResult.getTotal());
        return resultMap;
    }
}
