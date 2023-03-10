package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类列表控制层
 * @author 千亦
 * @create 2022-10-13-11:55
 */
@RestController //restful范式+controller注解
@RequestMapping("/category")  //所有的分类接口都有访问前缀
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList") //get查、post增、delete删、put改
    public ResponseResult getCategoryList(){ //响应的是responseresult类型<协议>数据
        //查询数据库分类的文章信息，封装成responseresult返回
        ResponseResult result=categoryService.getCategoryList();
        return result;
    }
}
