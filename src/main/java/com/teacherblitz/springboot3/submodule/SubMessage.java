package com.teacherblitz.springboot3.submodule;

import com.teacherblitz.springboot3.pojo.bo.OrderBO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;



/**
 * 制定通道充值策略
 *
 * @author: teacherblitz
 * @since: 2020/7/4
 */
@Slf4j
@AllArgsConstructor
@Component
public class SubMessage {

    private static Map<String, SubModuleInterface> subModuleInterfaceMap;

    public static void setSubModuleInterfaceMap(Map<String, SubModuleInterface> subModuleInterfaceMap) {
        SubMessage.subModuleInterfaceMap = subModuleInterfaceMap;
    }

    /**
     * 指定通道提交充值订单
     * @param orderBO 订单信息
     * @param moduleKey 模块key（可读取db配置）
     * @return
     */
    public String strategy(OrderBO orderBO, String moduleKey){
        log.info("【策略消息】进入预充值操作ing");
        SubModuleInterface subModuleInterface = subModuleInterfaceMap.get(moduleKey);
        if(null == subModuleInterface){
            return "error";
        }
        return subModuleInterface.subElement(orderBO);
    }
}
