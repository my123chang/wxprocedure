package com.wxprocedure.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxprocedure.constant.SystemConstant;
import com.wxprocedure.entity.R;
import com.wxprocedure.entity.WxUserInfo;
import com.wxprocedure.properties.WeixinProperties;
import com.wxprocedure.service.IWxUserInfoService;
import com.wxprocedure.util.HttpClientUtil;
import com.wxprocedure.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信用户Controller
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private IWxUserInfoService wxUserInfoService;

    /**
     * 微信用户登录
     * @return
     */
    @RequestMapping("/wxlogin")
    public R wxLogin(@RequestBody WxUserInfo wxUserInfo){
        System.out.println(wxUserInfo.getCode());
        String jscode2sessionUrl=weixinProperties.getJscode2sessionUrl()+"?appid="+weixinProperties.getAppid()+"&secret="+weixinProperties.getSecret()+"&js_code="+wxUserInfo.getCode()+"&grant_type=authorization_code";
        System.out.println(jscode2sessionUrl);
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.get("openid").toString();
        System.out.println(openid);
        // 插入用户到数据库 如：用户不存在 或 插入用户  若：用户存在 更新用户
        WxUserInfo resultWxuserInfo = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("openid",openid));
        if (resultWxuserInfo==null){
            System.out.println("不存在 插入用户");
            wxUserInfo.setOpenid(openid);
            wxUserInfo.setRegisterDate(new Date());
            wxUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.save(wxUserInfo);
        }else {
            System.out.println("用户存在 更新用户");
            resultWxuserInfo.setNickName(wxUserInfo.getNickName());
            resultWxuserInfo.setAvatarUrl(wxUserInfo.getAvatarUrl());
            resultWxuserInfo.setLastLoginDate(new Date());
            wxUserInfoService.updateById(resultWxuserInfo);
        }
        // 利用jwt生存token返回前端
        String token = JwtUtils.createJWT(openid,wxUserInfo.getNickName(), SystemConstant.JWT_TTL);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        return R.ok(resultMap);
    }
}
