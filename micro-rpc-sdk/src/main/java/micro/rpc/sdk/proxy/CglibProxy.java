package micro.rpc.sdk.proxy;


import io.netty.channel.ChannelFuture;
import micro.rpc.common.RpcMsg;
import micro.rpc.common.client.RpcRequest;
import micro.rpc.common.client.ServiceInvocation;
import micro.rpc.common.server.RpcResponse;
import micro.rpc.sdk.cache.RpcCache;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import micro.rpc.sdk.socket.client.RpcClient;
import micro.rpc.sdk.utils.RpcResponseFuture;
import micro.rpc.sdk.utils.ThreadPoolUtil;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
        String host = invokeInfo.getIp() + ":" + invokeInfo.getPort();
        String methodName = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();
        String uuId = UUID.randomUUID().toString();
        RpcRequest rpcRequest = new RpcRequest(uuId, invokeInfo.getBeanRef(), invokeInfo.getInterfaceName(), methodName, paramTypes, args);

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

        Future<RpcResponse> submit = ThreadPoolUtil.timeOutPool.submit(new RpcResponseFuture(rpcRequest.getRequestId(), rpcMsg, clientChannel));
        RpcResponse rpcResponse = submit.get(1000L, TimeUnit.SECONDS);

        //todo 完成反馈
        return rpcResponse.getData();
    }
}
