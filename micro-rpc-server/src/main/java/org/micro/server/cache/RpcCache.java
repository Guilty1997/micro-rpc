package org.micro.server.cache;

import io.netty.channel.ChannelFuture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：HeHongyi
 * @date: 2023/9/22
 * @description: Rpc缓存
 */
public class RpcCache {


    /**
     * 客户端调用实例通道缓存
     */
    private static Map<String, ChannelFuture> CLIENT_CHANNEL_CACHE = new ConcurrentHashMap<>(16);


    /**
     * 生产者Bean缓存 beanName:bean
     */
    private static Map<String, Object> PRODUCER_BEAN_CACHE = new ConcurrentHashMap<>(32);


    public static void registerClientChannel(String instanceInfo, ChannelFuture channel) {
        CLIENT_CHANNEL_CACHE.put(instanceInfo, channel);
    }

    public static ChannelFuture getClientChannel(String instanceInfo) {
        return CLIENT_CHANNEL_CACHE.get(instanceInfo);
    }

    public static ChannelFuture removeClientChannel(String instanceInfo) {
        return CLIENT_CHANNEL_CACHE.remove(instanceInfo);
    }


    public static void registerProducerCache(String beanName, Object producerBen) {
        PRODUCER_BEAN_CACHE.put(beanName, producerBen);
    }

    public static Object getProducerCache(String beanName) {
        return PRODUCER_BEAN_CACHE.get(beanName);
    }


}
