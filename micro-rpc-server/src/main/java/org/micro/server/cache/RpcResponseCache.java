package org.micro.server.cache;

import micro.rpc.common.server.RpcResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：HeHongyi
 * @date: 2023/9/23
 * @description: 返回缓存
 */
public class RpcResponseCache {
    private static Map<String, RpcResponse> rpcResponses = new ConcurrentHashMap<>(32);

    public static RpcResponse get(String key) {
        return rpcResponses.get(key);
    }

    public static void put(String key, RpcResponse rpcResponse) {
        rpcResponses.put(key, rpcResponse);
    }
}
