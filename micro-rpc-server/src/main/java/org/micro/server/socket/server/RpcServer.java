package org.micro.server.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.micro.server.socket.server.handler.ServerChannelInitializer;

/**
 * @author：HeHongyi
 * @date: 2023/6/26
 * @description: 服务端
 */
public class RpcServer implements Runnable {

    private final ServerChannelInitializer serverChannelInitializer;

    private final int port;

    public RpcServer(ServerChannelInitializer serverChannelInitializer, int port) {
        this.serverChannelInitializer = serverChannelInitializer;
        this.port = port;
    }

    public void bind() {
        //配置服务端NiO线程组
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup c = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, c)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(this.serverChannelInitializer);
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.shutdownGracefully();
            boosGroup.shutdownGracefully();

        }

    }

    @Override
    public void run() {
        this.bind();
    }
}
