package micro.rpc.sdk.cache;

import micro.rpc.common.server.ResponseBlockQueue;
import micro.rpc.common.server.RpcResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：HeHongyi
 * @date: 2023/9/24
 * @description: 响应缓存
 */
public class RpcResponseCache {

    /**
     * 请求超时时间混存
     */
    private static final ConcurrentHashMap<String, Long> REQUEST_MAP = new ConcurrentHashMap<>(32);

    /**
     * 响应缓存
     */
    private static final Map<String, RpcResponse> RESPONSES_MAP = new ConcurrentHashMap<>(32);


    private static final Map<String, ResponseBlockQueue> TIME_OUT_UTILS = new ConcurrentHashMap<>(32);

    public static ConcurrentHashMap<String, Long> getRequestMap() {
        return REQUEST_MAP;
    }


    public static void setRequestMap(String requestId, long timeout) {
        REQUEST_MAP.putIfAbsent(requestId, timeout);
    }

    public static Map<String, RpcResponse> getResponsesMap() {
        return RESPONSES_MAP;
    }


    public static void setResponsesMap(String requestId, RpcResponse response) {
        RESPONSES_MAP.putIfAbsent(requestId, response);
    }

    public static RpcResponse getResponse(String requestId) {
        return RESPONSES_MAP.get(requestId);
    }

    public static void addTimeOutUtil(String requestId, ResponseBlockQueue rpcResponseFuture) {
        TIME_OUT_UTILS.put(requestId, rpcResponseFuture);
    }

    public static ResponseBlockQueue getTimeOutUtils(String requestId) {
        return TIME_OUT_UTILS.get(requestId);
    }
}
