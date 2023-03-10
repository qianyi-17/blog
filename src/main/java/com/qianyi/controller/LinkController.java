package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 友链表接口
 * @author 千亦
 * @create 2022-10-20-20:21
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink") //请求映射规则
    public ResponseResult getAllLink(){
        ResponseResult result=linkService.getAllLink();
        return result;
    }
}
