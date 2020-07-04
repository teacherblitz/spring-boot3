package com.teacherblitz.springboot3.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @author: teacherblitz
 * @since: 2020/7/1
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "thread")
public class ThreadPool {

    private Integer corePoolSize;
    private Integer maximumPoolSize;

    @Bean
    public ThreadPoolExecutor subThreadPool(){
        // 阻塞队列（两个独立锁提高并发），按照先进先出对元素进行排序
        BlockingQueue blockingQueue = new LinkedBlockingQueue(1500);
        if(corePoolSize == 0 || corePoolSize == null){
            // TODO 核心线程数xx 取决于cpu核数
            corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        }
        if(maximumPoolSize == 0 || maximumPoolSize == null){
            // TODO 最大峰值xx 取决于cpu核数
            maximumPoolSize = Runtime.getRuntime().availableProcessors() * 3;
        }
        // 创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60, TimeUnit.SECONDS, blockingQueue, new OrderReject());
        // 设置线程工厂
        poolExecutor.setThreadFactory(r -> {
            Thread thread = new Thread(r, "order");
            thread.setDaemon(true);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        });
        return poolExecutor;
    }
}

