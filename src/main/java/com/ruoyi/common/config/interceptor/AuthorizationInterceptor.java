package com.ruoyi.common.config.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.redis.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RedisUtils redisUtils;
    Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setCharacterEncoding("UTF-8");
        //获取用户凭证
        String token = request.getHeader("Access-Token");
        if(StringUtils.isBlank(token)){
            token = request.getParameter("Access-Token");
        }

        //凭证为空
        if(StringUtils.isBlank(token)){
            JSONObject msg = new JSONObject();
            msg.put("code",500);
            msg.put("msg","Access-Token不能为空");
            msg.put("data",new JSONObject());
            response.getWriter().print(msg.toJSONString());
            logger.debug("Access-Token不能为空");
            return false;
        }
        if(null == redisUtils.get(Constants.APP_TOKEN+token)){
            JSONObject msg = new JSONObject();
            msg.put("code",-100);
            msg.put("msg","请先登陆或签到");
            msg.put("data",new JSONObject());
            response.getWriter().print(msg.toJSONString());
            logger.debug("请先登陆或签到");
            return false;
        }
        return true;
    }
}
