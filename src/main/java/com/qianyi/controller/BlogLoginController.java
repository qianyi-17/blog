package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.User;
import com.qianyi.enums.AppHttpCodeEnum;
import com.qianyi.exception.SystemException;
import com.qianyi.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义登录接口
 * @author 千亦
 * @create 2022-10-22-15:13
 */
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        ResponseResult result = blogLoginService.login(user);
        return result;

    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        ResponseResult result = blogLoginService.logout();
        return result;
    }
}
