package com.qianyi.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 千亦
 * @create 2022-11-04-10:20
 */
//@Component
public class TestJob {
    int i=0;
    @Scheduled(cron = "0/5 * * * * ?")  //从0秒开始，每隔5秒执行一次下列代码
    public void testJob(){

        System.out.println("执行了"+i+"次");
        i++;
    }
}
