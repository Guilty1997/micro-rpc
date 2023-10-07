package micro.rpc.sdk.processor;

import micro.rpc.common.client.ServiceInvocation;
import micro.rpc.sdk.annotation.MicroRpcReference;
import micro.rpc.sdk.proxy.ProxyFactory;
import org.apache.commons.lang.StringUtils;
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
            if (field.isAnnotationPresent(MicroRpcReference.class)) {
                if (Modifier.isStatic(field.getModifiers())) {
                    throw new IllegalStateException("@MicroRpcReference 注解不支持static领域");
                }
                MicroRpcReference microRpcReference = AnnotationUtils.getAnnotation(field, MicroRpcReference.class);
                if (microRpcReference != null) {
                    ServiceInvocation serviceInvokeInfo = getServiceInvokeInfo(field, microRpcReference);
                    field.setAccessible(true);
                    field.set(bean, ProxyFactory.getInvokeProxyBean(field.getType(), serviceInvokeInfo));
                    log.info("列名称{}", field.getName());
                }

            }

        });
    }

    private ServiceInvocation getServiceInvokeInfo(Field field, MicroRpcReference annotation) {
        String[] split = field.getType().toString().split("\\.");
        String interfaces = field.getType().toString().split(" ")[1];
        String beanRef = StringUtils.isEmpty(annotation.beanName()) ? StringUtils.uncapitalize(split[split.length - 1]) : annotation.beanName();
        String host = annotation.host();
        if (StringUtils.isEmpty(host)) {
            log.error("host为空");
        }
        String[] ipPort = host.split(":");
        ServiceInvocation serviceInvocation = new ServiceInvocation();
        serviceInvocation.setBeanRef(beanRef);
        serviceInvocation.setInterfaceName(interfaces);
        serviceInvocation.setIp(ipPort[0]);
        serviceInvocation.setPort(Integer.parseInt(ipPort[1]));
        log.info("beanName:{}", beanRef);
        return serviceInvocation;

    }
}
