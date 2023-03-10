package com.qianyi.job;

import com.qianyi.domain.entity.Article;
import com.qianyi.service.ArticleService;
import com.qianyi.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 千亦
 * @create 2022-11-05-21:03
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/55 * * * * ?")
    public void UpdateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> articleViewCount = redisCache.getCacheMap("articleViewCount");

        List<Article> articles = articleViewCount.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库
        articleService.updateBatchById(articles);
    }
}
