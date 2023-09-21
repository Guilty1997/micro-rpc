package org.micro.server.proxy;


import micro.rpc.common.client.ServiceInvocation;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author：HeHongyi
 * @date: 2023/9/18
 * @description: 对象生成代理
 */
public class CglibBeanInvocationHandler implements MethodInterceptor {

    private final ServiceInvocation target;

    public CglibBeanInvocationHandler(ServiceInvocation target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(o, objects);
    }
}
