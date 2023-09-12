package org.micro.server.event;

import org.apache.commons.collections.CollectionUtils;
import org.micro.server.annotation.MicroRpcRegister;
import org.micro.server.cache.ServerCache;
import org.micro.server.register.IRegisterCenter;
import org.micro.server.register.ZoomKeeperRegisterCenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author：HeHongyi
 * @date: 2023/9/10
 * @description:
 */
public class RpcStartEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!CollectionUtils.isEmpty(ServerCache.serviceMetaDataList)) {
            IRegisterCenter registerCenter = new ZoomKeeperRegisterCenter("127.0.0.1:2181");

            registerCenter.register(ServerCache.serviceMetaDataList.get(0));
        }

    }
}
