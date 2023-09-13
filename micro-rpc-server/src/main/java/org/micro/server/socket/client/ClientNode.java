package org.micro.server.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author：HeHongyi
 * @date: 2023/7/6
 * @description: 客户端节点
 */
public class ClientNode {
//        implements Callable<Channel> {
    //private Logger logger = LoggerFactory.getLogger(ClientNode.class);
//
//    private String inetHost = "127.0.0.1";
//    private int inetPort = 7397;
//
//    private EventLoopGroup workerGroup = new NioEventLoopGroup();
//    private Channel channel;
//
//    /**
//     * Computes a result, or throws an exception if unable to do so.
//     *
//     * @return computed result
//     * @throws Exception if unable to compute a result
//     */
//    @Override
//    public Channel call() throws Exception {
//        ChannelFuture channelFuture = null;
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(workerGroup);
//            b.channel(NioSocketChannel.class);
//            b.option(ChannelOption.AUTO_READ, true);
//            b.handler(new ClientChannelInitializer());
//            channelFuture = b.connect(inetHost, inetPort).syncUninterruptibly();
//            this.channel = channelFuture.channel();
//        } catch (Exception e) {
//            logger.error("socket client start error", e.getMessage());
//        } finally {
//            if (null != channelFuture && channelFuture.isSuccess()) {
//                logger.info("socket client start done. ");
//            } else {
//                logger.error("socket client start error. ");
//            }
//        }
//        return channel;
//    }
//
//    public void destroy() {
//        if (null == channel) return;
//        channel.close();
//        workerGroup.shutdownGracefully();
//    }
//
//    public boolean isActive() {
//        return channel.isActive();
//    }
//
//    public Channel channel() {
//        return channel;
//    }
}
