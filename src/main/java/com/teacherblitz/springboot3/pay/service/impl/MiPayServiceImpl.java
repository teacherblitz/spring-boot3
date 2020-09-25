package com.teacherblitz.springboot3.pay.service.impl;

import com.teacherblitz.springboot3.pay.service.PayService;
import com.teacherblitz.springboot3.pojo.dto.PayPushDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 小米支付业务
 * 
 * @author: <a href="mailto:teacherblitz@gmail.com">teacherblitz</a>
 * @since: 2020/9/25
 */
@Slf4j
@Service
public class MiPayServiceImpl implements PayService {

    @Override
    public String getProviderName() {
        return "mi";
    }

    @Override
    public void push(PayPushDto pushDto) {
        log.info("【小米支付】推送数据：{}", pushDto.toString());
    }
}
