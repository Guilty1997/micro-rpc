package org.micro.server.register;

import micro.rpc.common.register.ServiceObject;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: 注册中心接口
 */
public interface IRegisterCenter {

    void register(ServiceObject serviceObject);

    void remove(String serviceName);

    ServiceObject get(String serviceName);

}
