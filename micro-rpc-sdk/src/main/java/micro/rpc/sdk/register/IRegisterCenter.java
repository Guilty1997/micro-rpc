package micro.rpc.sdk.register;

import micro.rpc.common.register.ServiceInstance;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: 注册中心接口
 */
public interface IRegisterCenter {

    void register(ServiceInstance serviceInstance);

    void remove(String serviceName);

    ServiceInstance get(String serviceName);

    void subscribe(String serviceName);

}
