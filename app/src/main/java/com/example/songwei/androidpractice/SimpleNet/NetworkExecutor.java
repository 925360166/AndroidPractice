package com.example.songwei.androidpractice.SimpleNet;

import java.util.concurrent.BlockingQueue;

/**
 * 网络请求Executor,继承自Thread,从网络请求队列中循环读取请求并执行
 */
public final class NetworkExecutor extends Thread {

    //网络请求队列
    private BlockingQueue<Request<?>> mRequestQueue;

    //网络请求栈
    private HttpStack mHttpStack;

    //结果分发器，将结果投递到主线程
    private static ResponseDelivery mResponseDelivery = new ResponseDelivery();

    //请求缓存
    private static Cache<String, Response> mReqCache
}
