package com.teacherblitz.springboot3.config;

import com.teacherblitz.springboot3.pojo.bo.OrderBO;
import com.teacherblitz.springboot3.task.SubMessageTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池拒绝策略
 *
 * @author: teacherblitz
 * @since: 2020/7/3
 */
@Slf4j
public class OrderReject implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("任务线程满负荷，暂时缓存订单。待扫描线程进行处理。");
        if(r != null){
            if(r instanceof SubMessageTask){
                SubMessageTask task = (SubMessageTask) r;
                OrderBO order = task.getOrderBO();
                order.setStatus(2);
                // 缓存订单
            }
        }
    }
}
