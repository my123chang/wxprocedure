package com.wxprocedure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxprocedure.entity.BigType;
import org.springframework.stereotype.Repository;


/**
 * 商品大类mapper接口
 */
@Repository
public interface BigTypeMapper extends BaseMapper<BigType> {

    public BigType findById(Integer id);
}
