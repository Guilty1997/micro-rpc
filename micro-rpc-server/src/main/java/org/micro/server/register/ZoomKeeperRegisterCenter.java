package org.micro.server.register;

import micro.rpc.common.register.ServiceObject;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description:
 */

public class ZoomKeeperRegisterCenter extends DefaultRegisterCenter {
    private volatile ZkClient zkClient;


    public ZoomKeeperRegisterCenter(String address) {
        this.zkClient = new ZkClient(address);
    }

    @Override
    public void register(ServiceObject serviceObject) {
        super.register(serviceObject);
        this.registerService(serviceObject);

    }

    private void registerService(ServiceObject serviceObject) {
        String name = serviceObject.getName();
        //根节点
        String servicePath = "/rpc/" + name + "/service";
        if (!zkClient.exists(servicePath)) {
            //没有就注册
            zkClient.createPersistent(servicePath, true);
        }
        //注册子服务节点
//        String uriPath = servicePath + RpcConstant.ZK_PATH_DELIMITER + uri;
//        //同一个service可能有多个版本的实现，所以可能已经被注册到注册中心，这样就不需要注册了
//        if (!zkClient.exists(uriPath)) {
//            //创建一个临时节点，会话失效即被清理
//            zkClient.createEphemeral(uriPath);
//            //发布的URI记录到map里，方便关机取消注册
//            ExportedServiceURI.add(uriPath);
//            log.debug("service :{} exported zk", serviceResource);
//        }
    }

    @Override
    public void remove(String serviceName) {

    }
}
