package org.micro.server.proxy;


import io.netty.channel.ChannelFuture;
import micro.rpc.common.client.ServiceInvocation;
import micro.rpc.common.client.RpcRequest;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.micro.server.cache.RpcCache;
import micro.rpc.common.RpcMsg;
import org.micro.server.cache.RpcResponseCache;
import org.micro.server.socket.client.RpcClient;
import org.micro.server.socket.utils.ThreadPoolUtil;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author：HeHongyi
 * @date: 2023/9/18
 * @description: 对象生成代理
 */
public class CglibProxy implements MethodInterceptor {
    private final ServiceInvocation invokeInfo;

    public CglibProxy(ServiceInvocation invokeInfo) {
        this.invokeInfo = invokeInfo;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //todo 完成客户遍编写
        String host = invokeInfo.getIp() + ":" + invokeInfo.getPort();
        String methodName = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();
        RpcRequest rpcRequest = new RpcRequest(UUID.randomUUID().toString(), invokeInfo.getBeanRef(), invokeInfo.getInterfaceName(), methodName, paramTypes, args);

        ChannelFuture clientChannel = RpcCache.getClientChannel(host);

        if (clientChannel == null) {
            RpcClient rpcClient = new RpcClient(invokeInfo.getIp(), invokeInfo.getPort());
            clientChannel = rpcClient.connect();
            //异步等待
            ThreadPoolUtil.startNettyPool.execute(rpcClient);
        }

        RpcCache.registerClientChannel(host, clientChannel);

        RpcMsg rpcMsg = new RpcMsg();
        short i = 1;
        rpcMsg.setMsgType(i);
        rpcMsg.setSequenceId(111);
        rpcMsg.setData(rpcRequest);
        clientChannel.channel().writeAndFlush(rpcMsg);

        while (true) {
            if (RpcResponseCache.get("1111") != null) {

                break;
            }
        }

        //todo 完成反馈
        return RpcResponseCache.get("1111").getData();
    }
}
