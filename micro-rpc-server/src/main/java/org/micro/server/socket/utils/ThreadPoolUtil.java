package org.micro.server.socket.utils;

import java.util.concurrent.*;

/**
 * @author：HeHongyi
 * @date: 2023/9/13
 * @description: 线程池工具类
 * 1	corePoolSize	int	核心线程池大小
 * 2	maximumPoolSize	int	最大线程池大小
 * 3	keepAliveTime	long	线程最大空闲时间
 * 4	unit	TimeUnit	时间单位
 * 5	workQueue	BlockingQueue<Runnable>	线程等待队列
 * 6	threadFactory	ThreadFactory	线程创建工厂
 * 7	handler	RejectedExecutionHandler	拒绝策略
 */
public class ThreadPoolUtil {


    public static Executor startNettyPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("Rpc-netty-server");
                    return thread;
                }
            });

}
