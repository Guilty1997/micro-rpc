package org.micro.server.proxy;

import micro.rpc.common.client.ServiceInvocation;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author：HeHongyi
 * @date: 2023/9/18
 * @description: 代理工厂
 */
public class ProxyFactory {

    public static <T> T getInvokeProxyBean(Class<T> interfaceClass, ServiceInvocation target) {
        // 通过CGLIB动态代理获取代理对象的过程
        // 创建Enhancer对象，类似于JDK动态代理的Proxy类
        Enhancer enhancer = new Enhancer();
        // 设置目标类的字节码文件
        enhancer.setSuperclass(interfaceClass);
        // 设置回调函数
        enhancer.setCallback(new CglibBeanInvocationHandler(target));
        // create方法正式创建代理类
        return (T) enhancer.create();
    }
}
