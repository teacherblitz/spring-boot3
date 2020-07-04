package com.teacherblitz.springboot3.controller;

import com.teacherblitz.springboot3.pojo.bo.OrderBO;
import com.teacherblitz.springboot3.task.SubMessageTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 接受下游ele订单
 *
 * @author: teacherblitz
 * @since: 2020/7/3
 */
@Slf4j
@RestController
@RequestMapping("/v1")
public class OrderController {

    @Resource(name = "subThreadPool")
    private ThreadPoolExecutor subThreadPool;

    /**
     * 饿了么订单
     * @param orderNo
     * @return
     */
    @RequestMapping("/ele_order")
    public String eleOrder(@RequestParam("orderNo") String orderNo){
        log.info("【系统消息】饿了么接受到下游的新订单啦！orderNo={}", orderNo);
        if(StringUtils.isEmpty(orderNo)){
            return "error";
        }
        OrderBO orderBO = OrderBO
                .builder()
                .orderNo(orderNo)
                .build();
        try {
            subThreadPool.execute(new SubMessageTask(orderBO));
        }catch (Exception e){
            e.printStackTrace();
            log.error("【系统异常】饿了么订单发送异常了,errorMessage={}", e.getMessage());
        }
        return null;
    }
}
