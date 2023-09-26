package org.micro.server.socket.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.micro.server.socket.codec.ObjDecoder;
import org.micro.server.socket.codec.ObjEncoder;

/**
 * @author：HeHongyi
 * @date: 2023/6/26
 * @description: 管道配置
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //自定义协议解码器
        channel.pipeline().addLast(new ObjDecoder());
        channel.pipeline().addLast(new RequestHandler());
        //自定义协议编码器
        channel.pipeline().addLast(new ObjEncoder());

    }
}
