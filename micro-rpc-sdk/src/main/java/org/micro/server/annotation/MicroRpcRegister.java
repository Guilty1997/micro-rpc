package org.micro.server.annotation;

import java.lang.annotation.*;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: 注册注解
 */
@Documented
@Inherited
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MicroRpcRegister {

    String beanName() default "";

    String version() default "";

}
