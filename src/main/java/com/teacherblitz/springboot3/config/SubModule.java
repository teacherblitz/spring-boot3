package com.teacherblitz.springboot3.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 饿了么充值模块注解
 *
 * @author: teacherblitz
 * @since: 2020/7/4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface SubModule {

    String key() default "";
}
