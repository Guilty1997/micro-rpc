package org.micro.server.socket.client.hanlder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.micro.server.socket.codec.ObjDecoder;
import org.micro.server.socket.codec.ObjEncoder;

/**
 * @author：HeHongyi
 * @date: 2023/7/9
 * @description: 客户端处理器
 */

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理[解码]
        channel.pipeline().addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new ResponseHandler());
        //对象传输处理[编码]
        channel.pipeline().addLast(new ObjEncoder());
    }
}
