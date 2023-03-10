package com.qianyi.controller;

import com.qianyi.domain.entity.Article;
import com.qianyi.domain.ResponseResult;
import com.qianyi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章接口：热门文章展示、主页分类页文章展示、
 * @author 千亦
 * @create 2022-10-06-20:12
 */
@RestController //restful范式+controller
@RequestMapping("/article") //访问前缀
public class ArticleController {
    @Autowired  //注入service层接口
    private ArticleService articleService;



//    @GetMapping("/list")      //为什么希望是get请求
//    public List<Article> test(){
//        return articleService.list();
//
//    }
    @GetMapping("/hotArticleList") //为什么是get请求:get请求查询相关、post请求：新增相关、delete请求：删除相关、put请求：修改相关
    public ResponseResult hotArticleList(){
        //查询热门文章，封装成responseresult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    //指定请求映射规则
    @GetMapping("/articleList")
    public ResponseResult articleList(Long categoryId,Integer pageNum,Integer pageSize){
        ResponseResult result = articleService.articleList(categoryId,pageNum,pageSize);
        return result;
    }

    //是查询操作，使用get请求
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        //调用service层方法，使用id查询文章详情，并将查询结果封装返回
        ResponseResult result = articleService.getArticleDetail(id);
        return result;
    }

    //更新redis数据
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable Long id){
        ResponseResult result = articleService.updateViewCount(id);
        return result;
    }








}
