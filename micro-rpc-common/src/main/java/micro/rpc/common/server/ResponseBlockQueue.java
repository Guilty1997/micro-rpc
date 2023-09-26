package micro.rpc.common.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author：HeHongyi
 * @date: 2023/9/26
 * @description:
 */
public class ResponseBlockQueue {

    /**
     * 存储返回结果的阻塞队列
     */
    private final BlockingQueue<RpcResponse> responseBlockingQueue = new ArrayBlockingQueue<>(1);

    /**
     * 结果返回时间
     */
    private long responseTime;

    public static ResponseBlockQueue getInstance() {
        return new ResponseBlockQueue();
    }


    public BlockingQueue<RpcResponse> getResponseBlockingQueue() {
        return responseBlockingQueue;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }


}
