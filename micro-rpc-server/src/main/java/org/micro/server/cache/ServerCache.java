package org.micro.server.cache;

import micro.rpc.common.register.ServiceObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：HeHongyi
 * @date: 2023/9/10
 * @description: 服务缓存
 */
public class ServerCache {

    /**
     * 需要发布的服务元数据
     */
    public static List<ServiceObject> serviceMetaDataList = new ArrayList<>(16);
}
