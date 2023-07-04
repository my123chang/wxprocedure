package com.wxprocedure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxprocedure.entity.WxUserInfo;


/**
 * 用户信息mapper接口
 */
public interface WxUserInfoMapper extends BaseMapper<WxUserInfo> {

    public WxUserInfo findByOpenId(String openId);
}
