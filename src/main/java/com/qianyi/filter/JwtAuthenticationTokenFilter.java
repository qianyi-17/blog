package com.qianyi.filter;

import com.alibaba.fastjson.JSON;
import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.LoginUser;
import com.qianyi.enums.AppHttpCodeEnum;
import com.qianyi.utils.JwtUtil;
import com.qianyi.utils.RedisCache;
import com.qianyi.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 定义Jwt认证过滤器
 * @author 千亦
 * @create 2022-10-24-16:56
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录，直接放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析获取userid
        Claims claims = null; //解析
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception exception) {
            exception.printStackTrace();
            //token超时、token非法
            //响应告诉前端，需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId);
        //如果获取不到userid
        if(Objects.isNull(loginUser)){
            //说明登录过期i，提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        //存入SercurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
