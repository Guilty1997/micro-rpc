package micro.rpc.sdk.utils;

import io.netty.channel.ChannelFuture;
import micro.rpc.common.RpcMsg;
import micro.rpc.common.server.ResponseBlockQueue;
import micro.rpc.common.server.RpcResponse;
import micro.rpc.sdk.cache.RpcResponseCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author：HeHongyi
 * @date: 2023/9/25
 * @description: 超时工具
 */
public class RpcResponseFuture implements Callable<RpcResponse> {
    private static final Logger log = LoggerFactory.getLogger(RpcResponseFuture.class);

    private RpcResponse Response;

    private final String requestId;
    private ChannelFuture channelFuture;

    private final RpcMsg rpcMsg;

    public RpcResponseFuture(String requestId, RpcMsg rpcMsg, ChannelFuture channelFuture) {
        this.requestId = requestId;
        this.channelFuture = channelFuture;
        this.rpcMsg = rpcMsg;
    }

    public void setResponse(RpcResponse response) {
        Response = response;
    }

    @Override
    public RpcResponse call() throws Exception {
        ResponseBlockQueue instance = ResponseBlockQueue.getInstance();

        RpcResponseCache.addTimeOutUtil(requestId, instance);
        channelFuture.channel().writeAndFlush(rpcMsg);
        //syncUninterruptibly()让主线程同步等待子线程结果。
        channelFuture.syncUninterruptibly();

        RpcResponse poll = instance.getResponseBlockingQueue().poll(1, TimeUnit.SECONDS);

        if (poll == null) {
            throw new RuntimeException("requestId:" + requestId + " 请求超时");
        }
        return poll;
    }
}
