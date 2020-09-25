package com.teacherblitz.springboot3.pay.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 支付工厂
 * 
 * @author: <a href="mailto:teacherblitz@gmail.com">teacherblitz</a>
 * @since: 2020/9/25
 */
@Component
public class PayFactory implements ApplicationContextAware {

    private static Map<String, PayService> SERVICE_MAP;

    /**
     * 根据供应商名称获取对应支付系统实例
     * @param providerName
     * @return
     */
    public static PayService getInstance(String providerName) {
        PayService payService = SERVICE_MAP.get(providerName);
        return Optional
                .ofNullable(payService)
                .orElseThrow(() -> new RuntimeException(
                        String.format("系统尚未与[%s]支付服务商对接", providerName)));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext ctx) throws BeansException {
        Map<String, PayService> beanMap = ctx.getBeansOfType(PayService.class);
        SERVICE_MAP = new HashMap<>(beanMap.size());
        beanMap.forEach((beanName, bean) -> SERVICE_MAP.put(bean.getProviderName(), bean));
    }
}
