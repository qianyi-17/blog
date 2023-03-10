package com.qianyi.runner;

import com.qianyi.domain.entity.Article;
import com.qianyi.mapper.ArticleMapper;
import com.qianyi.service.ArticleService;
import com.qianyi.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SpringBoot应用启动初始化操作
 * @author 千亦
 * @create 2022-11-04-11:09
 */
@Component  //注入到容器里面
public class ViewCountRunner implements CommandLineRunner {
    @Autowired  //注入mapper，使用其从数据库查询
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;



    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id(键) viewCount(值)
        List<Article> articles = articleMapper.selectList(null);//查询到所有的博客信息
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(new Function<Article, String>() {
                    @Override
                    public String apply(Article article) {
                        return article.getId().toString();
                    }
                }, new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));

        //存储到redis中
        redisCache.setCacheMap("articleViewCount",viewCountMap);

    }
}
