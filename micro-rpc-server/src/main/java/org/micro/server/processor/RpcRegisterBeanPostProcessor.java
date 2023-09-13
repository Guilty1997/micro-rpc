package org.micro.server.processor;

import micro.rpc.common.register.ServiceObject;
import org.micro.server.annotation.MicroRpcRegister;
import org.micro.server.cache.ServerCache;
import org.micro.server.config.RpcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: 注解处理器
 */
public class RpcRegisterBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private final static Logger log = LoggerFactory.getLogger(RpcRegisterBeanPostProcessor.class);


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        try {

            Class<?> clazz = bean.getClass();
            MicroRpcRegister service = clazz.getAnnotation(MicroRpcRegister.class);
            if (service != null) {
                beanName = service.name() == null ? beanName : service.name();
                Class<?>[] interfaces = clazz.getInterfaces();
                Method[] declaredMethods = clazz.getDeclaredMethods();
                ServiceObject so = null;
                /*
                 * 如果只实现了一个接口就用接口的className作为服务名
                 * 如果该类实现了多个接口，则使用注解里的value作为服务名
                 */
                if (interfaces.length != 1) {
                    String value = service.name();
                    if ("".equals(value)) {
                        throw new UnsupportedOperationException("The exposed interface is not specific with '" + bean.getClass().getName() + "'");
                    }
                    /*
                     * bean实现多个接口时，javassist代理类中生成的方法只按照注解指定的服务类来生成
                     */

                    declaredMethods = Class.forName(value).getDeclaredMethods();

//                    Object proxy = ProxyFactory.makeProxy(value, beanName, declaredMethods);
                    so = new ServiceObject(value, Class.forName(value), "1.0");
                } else {
                    Class<?> supperClass = interfaces[0];
//                    Object proxy = ProxyFactory.makeProxy(supperClass.getName(), beanName, declaredMethods);
                    so = new ServiceObject(supperClass.getName(), supperClass, "1.0");
                }
                ServerCache.serviceMetaDataList.add(so);
            }
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }

        return bean;
    }
}
