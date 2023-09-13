package org.micro.server.socket.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author：HeHongyi
 * @date: 2023/7/11
 * @description: 消息处理器
 */
@ChannelHandler.Sharable
public class RequestHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("客户端连接通知：{}", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
//
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("服务端异常断开", cause.getMessage());

    }


    public void channelRead(Channel channel, RpcRequest msg) {
        //todo 处理信息


    }
}
