package org.micro.server.socket.server.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import micro.rpc.common.client.RpcRequest;
import micro.rpc.common.server.RpcResponse;
import org.micro.server.cache.RpcCache;
import micro.rpc.common.RpcMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


/**
 * @author：HeHongyi
 * @date: 2023/7/11
 * @description: 消息处理器
 */
@ChannelHandler.Sharable
public class RequestHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private Logger log = LoggerFactory.getLogger(RequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("客户端连接通知：{}", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
//
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("服务端异常断开", cause.getMessage());

    }


    public void channelRead(Channel channel, RpcRequest msg) {
        //todo 处理信息
        log.info("收到请求消息{}", JSON.toJSONString(msg));
        try {
            Class<?> classType = Class.forName(msg.getInterfaces());
            Method method = classType.getMethod(msg.getMethodName(), msg.getParamTypes());
            Object bean = RpcCache.getProducerCache(msg.getBeanName());
            Object invoke = method.invoke(bean, msg.getArgs());
            log.info(invoke.toString());

            RpcMsg rpcMsg = new RpcMsg();
            short i = 2;
            rpcMsg.setMsgType(i);
            rpcMsg.setSequenceId(111);
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.setCode(200);
            rpcResponse.setRequestId("1111");
            rpcResponse.setData(invoke);
            rpcMsg.setData(rpcResponse);

            channel.writeAndFlush(rpcMsg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }


    }
}
