package micro.rpc.sdk.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import micro.rpc.sdk.socket.server.handler.ServerChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author：HeHongyi
 * @date: 2023/6/26
 * @description: 服务端
 */
public class RpcServer implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(RpcServer.class);


    private final ServerChannelInitializer serverChannelInitializer;

    private final int port;


    //配置服务端NiO线程组
    private NioEventLoopGroup bossGroup = null;
    private NioEventLoopGroup workerGroup = null;

    private ChannelFuture future;


    public RpcServer(ServerChannelInitializer serverChannelInitializer, int port) {
        this.serverChannelInitializer = serverChannelInitializer;
        this.port = port;
    }

    //启动服务
    private void serverStart() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(2);
        workerGroup = new NioEventLoopGroup(16);
        // 服务端启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 传入两个线程组
        serverBootstrap.group(bossGroup, workerGroup)
                // 指定Channel 和NIO一样是采用Channel通道的方式通信 所以需要指定服务端通道
                .channel(NioServerSocketChannel.class)
                //使用指定的端口设置套接字地址
                .localAddress(port)
                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置数据处理器
                .childHandler(this.serverChannelInitializer);
        future = serverBootstrap.bind().sync();
        future.channel().closeFuture().sync();
    }

    //释放资源
    protected void destroy() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void run() {
        try {
            this.serverStart();
        } catch (Exception e) {
            log.error("服务启动失败{}", e.getMessage());
            //todo 可以尝试定时重启
        } finally {
            log.info("服务关闭释放资源");
            this.destroy();
        }
    }
}
