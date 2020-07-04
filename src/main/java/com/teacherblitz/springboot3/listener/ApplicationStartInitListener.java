package com.teacherblitz.springboot3.listener;

import com.teacherblitz.springboot3.config.SubModule;
import com.teacherblitz.springboot3.submodule.SubMessage;
import com.teacherblitz.springboot3.submodule.SubModuleInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: teacherblitz
 * @since: 2020/7/4
 */
@Slf4j
@Component
public class ApplicationStartInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ctx = event.getApplicationContext();
        // 根据类型获取beans
        Map<String, SubModuleInterface> subModules = ctx.getBeansOfType(SubModuleInterface.class);
        // 创建一个新的map用来装过滤掉的模块
        Map<String, SubModuleInterface> subModuleInterfaceMap = new HashMap<>(16);
        // 开始迭代subModules
        for (Iterator<Map.Entry<String, SubModuleInterface>> it = subModules.entrySet().iterator(); it.hasNext();) {
            // 如果有则取下一个
            Map.Entry<String, SubModuleInterface> entry = it.next();
            // 获取具体一个模块
            SubModuleInterface module = entry.getValue();
            // 得到当前模块的具体元注解
            SubModule declaredAnnotation = module.getClass().getDeclaredAnnotation(SubModule.class);
            if(declaredAnnotation instanceof SubModule){
                subModuleInterfaceMap.put(declaredAnnotation.key(), module);
            }
        }
        SubMessage.setSubModuleInterfaceMap(subModuleInterfaceMap);
        log.info("【系统消息】程序初始化完毕>>>>>>>>>>>>>>>>>>>");
    }
}
