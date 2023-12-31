package micro.rpc.sdk.config;

import micro.rpc.sdk.event.RpcStartEvent;
import micro.rpc.sdk.processor.RpcDiscoverBeanPostProcessor;
import micro.rpc.sdk.processor.RpcRegisterBeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：HeHongyi
 * @date: 2023/9/10
 * @description: 配置类
 */
@Configuration
@EnableConfigurationProperties(RpcConfig.class)
public class RpcAutoConfig {
    /**
     * 注解解析
     *
     * @return
     */
    @Bean
    public RpcRegisterBeanPostProcessor microRpcRegisterBeanPostProcessor() {
        return new RpcRegisterBeanPostProcessor();
    }


    @Bean
    public RpcDiscoverBeanPostProcessor microRpcDiscoverBeanPostProcessor() {
        return new RpcDiscoverBeanPostProcessor();
    }

    @Bean
    public ApplicationListener applicationListener(RpcConfig rpcConfig) {
        return new RpcStartEvent(rpcConfig);
    }


}
