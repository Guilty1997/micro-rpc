package org.micro.server.socket.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.micro.server.socket.client.hanlder.ClientChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：HeHongyi
 * @date: 2023/7/6
 * @description: 客户端节点
 */
public class RpcClient implements Runnable {
    private Logger log = LoggerFactory.getLogger(RpcClient.class);


    private final String inetHost;
    private final int inetPort;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private ChannelFuture channelFuture;


    private Channel channel;

    public RpcClient(String inetHost, int inetPort) {
        this.inetHost = inetHost;
        this.inetPort = inetPort;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     */
    @Override
    public void run() {
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("连接失败");
            throw new RuntimeException(e);
        } finally {
            destroy();
        }

    }

    public ChannelFuture connect() {
        Bootstrap b = new Bootstrap();
        b.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.AUTO_READ, true)
                .handler(new ClientChannelInitializer())
                .remoteAddress(inetHost, inetPort);
        channelFuture = b.connect().syncUninterruptibly();
        channelFuture.addListener(event -> {
            if (event.isSuccess()) {
                log.info("连接Rpc Server 成功,地址：{},端口：{}", inetHost, inetPort);
            } else {
                throw new Exception("客户端连接失败");
            }
        });
        return channelFuture;
    }

    private void destroy() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public Channel getChannel() {
        return channel;
    }
}
