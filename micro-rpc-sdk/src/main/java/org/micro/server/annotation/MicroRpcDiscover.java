package org.micro.server.annotation;

import java.lang.annotation.*;

/**
 * @author：HeHongyi
 * @date: 2023/9/15
 * @description: RPC 服务发现
 */

@Documented
@Inherited
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MicroRpcDiscover {

    String beanName() default "";

    String host();
}
 