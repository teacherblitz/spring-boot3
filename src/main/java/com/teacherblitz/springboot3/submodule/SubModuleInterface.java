package com.teacherblitz.springboot3.submodule;

import com.teacherblitz.springboot3.pojo.bo.OrderBO;

/**
 * 多个子业务的父接口
 *
 * @author: teacherblitz
 * @since: 2020/7/3
 */
public interface SubModuleInterface {

    /**
     * 饿了么充值订单
     *
     * @param orderBO
     * @return
     */
    default String subElement(OrderBO orderBO){
        return null;
    }
}
