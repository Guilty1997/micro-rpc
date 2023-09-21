package org.micro.server.processor;

import micro.rpc.common.client.ServiceInvocation;
import org.apache.commons.lang.StringUtils;
import org.micro.server.annotation.MicroRpcDiscover;
import org.micro.server.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author：HeHongyi
 * @date: 2023/9/15
 * @description:
 */
public class RpcDiscoverBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private final static Logger log = LoggerFactory.getLogger(RpcDiscoverBeanPostProcessor.class);

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        rpcServiceInvokeAnnotationHandler(bean.getClass(), bean);
        return pvs;
    }

    private void rpcServiceInvokeAnnotationHandler(Class<?> aClass, Object bean) {
        ReflectionUtils.doWithLocalFields(aClass, field -> {
            if (field.isAnnotationPresent(MicroRpcDiscover.class)) {
                if (Modifier.isStatic(field.getModifiers())) {
                    throw new IllegalStateException("@MicroRpcDiscover 注解不支持static领域");
                }
                MicroRpcDiscover microRpcDiscover = AnnotationUtils.getAnnotation(field, MicroRpcDiscover.class);
                ServiceInvocation serviceInvokeInfo = getServiceInvokeInfo(field, microRpcDiscover);

                field.setAccessible(true);
                Object invokeProxyBean = ProxyFactory.getInvokeProxyBean(field.getType(), serviceInvokeInfo);
                field.set(bean, invokeProxyBean);
                if (microRpcDiscover != null) {
                    log.info("列名称{}", field.getName());
                }
            }

        });
    }

    private ServiceInvocation getServiceInvokeInfo(Field field, MicroRpcDiscover annotation) {
        String[] split = field.getType().toString().split("\\.");
        String interfaces = field.getType().toString().split(" ")[1];
        String beanRef = StringUtils.isEmpty(annotation.beanName()) ? StringUtils.uncapitalize(split[split.length - 1]) : annotation.beanName();

        ServiceInvocation serviceInvocation = new ServiceInvocation();
        serviceInvocation.setBeanRef(beanRef);
        serviceInvocation.setInterfaceName(interfaces);
        log.info("beanName:{}", beanRef);
        return serviceInvocation;

    }
}