package com.example.songwei.androidpractice.SimpleNet;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列，使用优先队列，使得请求可以按照优先级进行处理。【Thread safe】
 */
public final class RequestQueue {
    /**
     * 请求队列[ Thread-safe]
     */
    //线程安全的请求队列
    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<Request<?>>();
    //请求的序列化生成器
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);
    //默认的核心数，为CPU格式加1
    public static int DEFAULT_CORE_NUMS = Runtime.getRuntime().availableProcessors() + 1;
    //

}
