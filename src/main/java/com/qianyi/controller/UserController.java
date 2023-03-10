package com.qianyi.controller;

import com.qianyi.annotation.SystemLog;
import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.User;
import com.qianyi.service.UserService;
import com.qianyi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 个人信息的接口
 * @author 千亦
 * @create 2022-10-31-15:47
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户个人信息
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult getUserInfo(){
        ResponseResult result = userService.getUserInfo();
        return result;
    }

    /**
     * 更新个人用户信息
     * @return
     */
    @PutMapping("/userInfo")
    @SystemLog(businessName="更新个人用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        ResponseResult result = userService.updateUserInfo(user);
        return result;
    }

    /**
     * 用户注册方法
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseResult userRegister(@RequestBody User user){
        ResponseResult result = userService.userRegister(user);
        return result;
    }




}
