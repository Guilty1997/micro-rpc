package micro.rpc.sdk.register;

import com.alibaba.fastjson.JSON;
import micro.rpc.common.exception.RpcException;
import micro.rpc.common.register.ServiceInstance;
import micro.rpc.sdk.config.RpcConfig;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description:
 */

public class ZoomKeeperRegisterCenter implements IRegisterCenter {

    private final static Logger log = LoggerFactory.getLogger(ZoomKeeperRegisterCenter.class);

    private volatile ZkClient zkClient;

    private RpcConfig rpcConfig;


    public ZoomKeeperRegisterCenter(String address, RpcConfig rpcConfig) {
        try {
            this.zkClient = new ZkClient(address, 1000, 2000);
        } catch (Exception e) {
            throw new RpcException("连接ZK超时", e);
        }
        this.rpcConfig = rpcConfig;
    }

    @Override
    public void register(ServiceInstance serviceInstance) {
        this.registerService(serviceInstance);

    }

    private void registerService(ServiceInstance serviceInstance) {
        String applicationName = rpcConfig.getApplication().getName();
        //根节点
        String servicePath = "/rpc/" + applicationName + "/service";
        if (!zkClient.exists(servicePath)) {
            //没有就注册
            zkClient.createPersistent(servicePath, true);
        }

        String urlInfo = serviceInstance.getIp() + ":" + serviceInstance.getPort() + JSON.toJSONString(serviceInstance.getRegisterData());
        String uri;
        try {
            uri = URLEncoder.encode(urlInfo, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

//        注册子服务节点
        String uriPath = servicePath + "/" + uri;
        //同一个service可能有多个版本的实现，所以可能已经被注册到注册中心，这样就不需要注册了
        if (!zkClient.exists(uriPath)) {
            //创建一个临时节点，会话失效即被清理
            zkClient.createEphemeral(uriPath);
            //发布的URI记录到map里，方便关机取消注册
//            ExportedServiceURI.add(uriPath);
            log.info("service :{} exported zk", uriPath);
        }
    }

    @Override
    public void remove(String serviceName) {

    }

    @Override
    public ServiceInstance get(String serviceName) {
        return null;
    }

    @Override
    public void subscribe(String serviceName) {

    }
}
