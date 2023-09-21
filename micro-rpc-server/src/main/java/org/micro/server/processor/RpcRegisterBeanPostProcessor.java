package org.micro.server.processor;

import micro.rpc.common.register.ServiceRegisterData;
import org.apache.commons.lang.StringUtils;
import org.micro.server.annotation.MicroRpcRegister;
import org.micro.server.cache.ServerCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Method;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: 注解处理器
 */
public class RpcRegisterBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private final static Logger log = LoggerFactory.getLogger(RpcRegisterBeanPostProcessor.class);


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> clazz = bean.getClass();
        MicroRpcRegister service = clazz.getAnnotation(MicroRpcRegister.class);
        if (service != null) {
            beanName = StringUtils.isEmpty(service.beanName()) ? beanName : service.beanName();
            Class<?>[] interfaces = clazz.getInterfaces();
            Method[] declaredMethods = clazz.getDeclaredMethods();
            String version = service.version();


            ServerCache.serviceRegisterDataList.add(new ServiceRegisterData(beanName, interfaces[0].getName(), version));
        }

        return bean;
    }
}
