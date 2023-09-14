package org.micro.server.event;

import micro.rpc.common.register.ServiceInstance;
import org.apache.commons.collections.CollectionUtils;
import org.micro.server.cache.ServerCache;
import org.micro.server.config.RpcConfig;
import org.micro.server.register.IRegisterCenter;
import org.micro.server.register.ZoomKeeperRegisterCenter;
import org.micro.server.socket.server.RpcServer;
import org.micro.server.socket.server.handler.ServerChannelInitializer;
import org.micro.server.socket.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author：HeHongyi
 * @date: 2023/9/10
 * @description:
 */
public class RpcStartEvent implements ApplicationListener<ContextRefreshedEvent> {
    private Logger log = LoggerFactory.getLogger(RpcStartEvent.class);

    private RpcConfig rpcConfig;

    public RpcStartEvent(RpcConfig rpcConfig) {
        this.rpcConfig = rpcConfig;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!CollectionUtils.isEmpty(ServerCache.serviceRegisterDataList)) {
            String ip = rpcConfig.getRegistry().getIp();
            int port = rpcConfig.getRegistry().getPort();
            String host = ip + ":" + port;
            log.info("注册中心地址：{}", host);
            //启动远程服务
            ThreadPoolUtil.startNettyPool.execute(new RpcServer(new ServerChannelInitializer(), rpcConfig.getProtocol().getPort()));

            String protocolIp = null;
            try {
                protocolIp = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }

            ServiceInstance serviceInstance = new ServiceInstance(protocolIp, rpcConfig.getProtocol().getPort(), ServerCache.serviceRegisterDataList);
            //注册中心
            IRegisterCenter registerCenter = new ZoomKeeperRegisterCenter(host, rpcConfig);
            registerCenter.register(serviceInstance);
        }

    }
}
