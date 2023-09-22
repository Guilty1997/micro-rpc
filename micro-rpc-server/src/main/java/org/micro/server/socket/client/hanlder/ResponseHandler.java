package org.micro.server.socket.client.hanlder;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import micro.rpc.common.server.RpcResponse;
import org.micro.server.cache.RpcResponseCache;
import org.micro.server.socket.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：HeHongyi
 * @date: 2023/9/21
 * @description:
 */
@ChannelHandler.Sharable
public class ResponseHandler extends SimpleChannelInboundHandler<RpcResponse> {

    private Logger log = LoggerFactory.getLogger(ResponseHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }

    public void channelRead(Channel channel, RpcResponse msg) {
        log.info("收到服务端返回{}", JSON.toJSONString(msg));
        RpcResponseCache.put(msg.getRequestId(), msg);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ctx.channel().closeFuture();
        System.out.println("断开连接了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().closeFuture();
        System.out.println("关闭" + ctx.channel().id());
    }
}
