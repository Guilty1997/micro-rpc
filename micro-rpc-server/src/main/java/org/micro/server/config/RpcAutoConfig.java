package org.micro.server.config;

import org.micro.server.event.RpcStartEvent;
import org.micro.server.processor.RpcRegisterBeanPostProcessor;
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
    @Bean
    public RpcRegisterBeanPostProcessor microRpcRegisterBeanPostProcessor() {
        return new RpcRegisterBeanPostProcessor();
    }


    @Bean
    public ApplicationListener applicationListener() {
        return new RpcStartEvent();
    }


}
