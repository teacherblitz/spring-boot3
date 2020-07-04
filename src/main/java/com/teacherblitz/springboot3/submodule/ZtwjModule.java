package com.teacherblitz.springboot3.submodule;

import com.teacherblitz.springboot3.config.SubModule;
import com.teacherblitz.springboot3.pojo.bo.OrderBO;
import lombok.extern.slf4j.Slf4j;

/**
 * 中天网景->饿了么充值订单
 *
 * @author: teacherblitz
 * @since: 2020/7/3
 */
@Slf4j
@SubModule(key = "1")
public class ZtwjModule implements SubModuleInterface{

    @Override
    public String subElement(OrderBO orderBO) {
        log.info("【充值信息】进入中天网景->饿了么订单充值中心：\nOrderBO={}", orderBO);
        return "success";
    }
}
