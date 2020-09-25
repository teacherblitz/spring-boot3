package com.teacherblitz.springboot3.pay.service;

import com.teacherblitz.springboot3.pojo.dto.PayPushDto;

/**
 * 支付系统服务
 * 
 * @author: <a href="mailto:teacherblitz@gmail.com">teacherblitz</a>
 * @since: 2020/9/25
 */
public interface PayService {

    /**
     * 获取支付系统供应商名称
     * @return
     */
    String getProviderName();

    /**
     * 支付信息推送
     * @param pushDto
     */
    void push(PayPushDto pushDto);
}
