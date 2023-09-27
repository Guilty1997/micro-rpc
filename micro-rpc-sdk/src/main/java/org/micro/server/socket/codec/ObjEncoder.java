package org.micro.server.socket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import micro.rpc.common.RpcMsg;
import org.micro.server.utils.SerializationUtil;


/**
 * @author：HeHongyi
 * @date: 2023/6/27
 * @description: 编码器
 * 协议：
 * - 魔数：用来在第一时间判定是否是无效数据包
 * - 例如：Java Class文件都是以0x CAFEBABE开头的。Java这么做的原因就是为了快速判断一个文件是不是有可能为class文件，以及这个class文件有没有受损。
 * - 版本号：可以支持协议的升级
 * - 序列化算法：消息正文到底采用哪种序列化反序列化方式
 * - 指令类型：针对业务类型指定
 * - 请求序号：为了双工通信，提供异步能力，序号用于回调
 * - 正文长度：没有长度会导致浏览器持续加载
 * - 消息正文：具体消息内容
 */
public class ObjEncoder extends MessageToByteEncoder<RpcMsg> {

    @Override
    protected void encode(ChannelHandlerContext context, RpcMsg in, ByteBuf out) throws Exception {


        // 4 字节的魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        // 1 字节的版本,
        out.writeByte(1);
        // 1 字节的序列化方式 0:jdk
        out.writeByte(0);
        // 1 字节的指令类型
        out.writeByte(in.getMsgType());
        // 4 个字节的请求序号
        out.writeInt(in.getSequenceId());
        // 无意义，对齐填充,使其满足2的n次方
        out.writeByte(0xff);
        // 获取内容的字节数组
        byte[] data = SerializationUtil.serialize(in.getData());

        // 长度
        out.writeInt(data.length);
        // 写入内容
        out.writeBytes(data);
    }
}
