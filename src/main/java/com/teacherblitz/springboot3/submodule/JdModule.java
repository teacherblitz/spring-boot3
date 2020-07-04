package com.teacherblitz.springboot3.submodule;

import com.teacherblitz.springboot3.config.SubModule;
import com.teacherblitz.springboot3.pojo.bo.OrderBO;
import lombok.extern.slf4j.Slf4j;

/**
 * 金蝶->饿了么充值订单
 *
 * @author: teacherblitz
 * @since: 2020/7/4
 */
@Slf4j
@SubModule(key = "2")
public class JdModule implements SubModuleInterface{

    @Override
    public String subElement(OrderBO orderBO) {
        log.info("【充值信息】进入金蝶->饿了么订单充值中心：\nOrderBO={}", orderBO);
        return "success";
    }
}
