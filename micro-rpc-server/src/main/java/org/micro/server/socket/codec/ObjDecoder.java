package org.micro.server.socket.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.micro.server.socket.server.handler.RpcRequest;
import org.micro.server.socket.utils.SerializationUtil;

import java.util.List;

/**
 * @author：HeHongyi
 * @date: 2023/6/27
 * @description: 编码器
 */
public class ObjDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf in, List<Object> out) throws Exception {
        //todo 待完善字节长度判断

        // 4 字节的魔数
        int magicNum = in.readInt();
        // 1 字节的版本,
        byte version = in.readByte();
        // 1 字节的序列化方式 0:jdk
        byte serializerType = in.readByte();
        // 1 字节的指令类型
        byte messageType = in.readByte();
        // 4 个字节的请求序号
        int sequenceId = in.readInt();
        in.readByte();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);
        Object object = SerializationUtil.deserialize(bytes, RpcRequest.class);
        System.out.println("decode:" + magicNum + "," + version + "," + serializerType + "," + messageType + "," + sequenceId + "," + length);
        System.out.println("decode:" + JSON.toJSONString(object));
        out.add(object);
    }
}
