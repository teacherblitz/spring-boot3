package com.teacherblitz.springboot3.task;

import com.teacherblitz.springboot3.Run;
import com.teacherblitz.springboot3.pojo.bo.OrderBO;
import com.teacherblitz.springboot3.submodule.SubMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义线程类，用来处理订单
 *
 * @author: teacherblitz
 * @since: 2020/7/3
 */
@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class SubMessageTask implements Runnable{

    private OrderBO orderBO;

    @Override
    public void run() {
        log.info("【系统消息】饿了么来新消息啦,order={}", orderBO);
        // -----------开始提交订单------------------
        SubMessage subMessage = Run.getContext().getBean(SubMessage.class);
        String result = subMessage.strategy(orderBO, "1");
        if ("success".equals(result)) {
            orderBO.setStatus(1);
        } else {
            orderBO.setStatus(2);
        }
        // -----------开始回调订单------------------
        returnOrderTask(orderBO);
    }

    /**
     * 回调业务
     * @param orderBO
     * @return
     */
    String returnOrderTask(OrderBO orderBO){
        log.info("【回调信息】开始异步通知下游订单状态,\nOrderBo={}", orderBO);
        String notifyUrl = "http://localhost:8080/chenxiang/notify";
        log.info("【回调消息】完成回调通知：\nnotifyUrl={}", notifyUrl);
        return "success";
    }
}
