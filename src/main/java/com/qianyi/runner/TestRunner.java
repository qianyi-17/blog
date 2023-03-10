package com.qianyi.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author 千亦
 * @create 2022-11-04-10:06
 */
//@Component
public class TestRunner implements CommandLineRunner {
    /**
     * 程序启动之后，所有的bean都初始化结束，才会执行的
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化");
    }
}
