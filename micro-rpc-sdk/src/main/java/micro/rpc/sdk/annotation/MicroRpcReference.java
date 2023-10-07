package micro.rpc.sdk.annotation;

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
public @interface MicroRpcReference {

    String beanName() default "";

    String host();
}
 