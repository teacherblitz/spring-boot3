package com.teacherblitz.springboot3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 程序主入口
 *
 * @author: teacherblitz
 * @since: 2020/7/1
 */
@SpringBootApplication
public class Run {

    private static ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    /**
     * 一些初始化的事情可以放在这里
     *
     * @param ctx 应用问下文
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        context = ctx;
        return (args) -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            AtomicInteger index = new AtomicInteger(1);
            Arrays.stream(beanNames).sorted().forEach(i -> {
                System.out.println(String.format("%s:%s", index, i));
                index.getAndIncrement();
            });
        };
    }

    /**
     * 获取应用上下文
     *
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }
}
