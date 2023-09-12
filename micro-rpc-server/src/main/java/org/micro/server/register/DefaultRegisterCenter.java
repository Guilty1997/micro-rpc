package org.micro.server.register;

import micro.rpc.common.register.ServiceObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: 注册中心
 * 1.注册服务提供者
 * 2.本地服务缓存
 * 3.注册服务调用者信息
 * 4.监听节点变化，刷新服务提供者信息
 */
public abstract class DefaultRegisterCenter implements IRegisterCenter {
    private Map<String, ServiceObject> serviceObjectMap = new HashMap<>();

    @Override
    public void register(ServiceObject serviceObject) {
        if (serviceObject == null) {
            throw new RuntimeException("服务已注册");
        }
        serviceObjectMap.put(serviceObject.getName() + serviceObject.getVersion(), serviceObject);

    }

    @Override
    public ServiceObject get(String serviceName) {
        return serviceObjectMap.get(serviceName);
    }
}
