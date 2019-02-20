package com.example.songwei.androidpractice.SimpleNet;

import android.util.Log;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列，使用优先队列，使得请求可以按照优先级进行处理。【Thread safe】
 */
public final class RequestQueue {
    private static final String TAG = "RequestQueue";

    /**
     * 请求队列[ Thread-safe]
     */
    //线程安全的请求队列
    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<Request<?>>();

    //请求的序列化生成器
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);

    //默认的核心数：为CPU核心数 + 1
    public static int DEFAULT_CORE_NUMS = Runtime.getRuntime().availableProcessors() + 1;

    //分发线程数：为CPU核心数 + 1
    private int mDispatcherNums = DEFAULT_CORE_NUMS;

    //NetworkExecutor,执行网络请求的线程
    private NetworkExecutor[] mDispatchers = null;

    //Http请求的真正执行者
    private HttpStack mHttpStack;

    protected RequestQueue(int coreNums, HttpStack httpStack){
        mDispatcherNums = coreNums;
        mHttpStack = httpStack != null ? httpStack : HttpStackFactory.createHttpStack();
    }

    //启动NetworkExecutor
    private final void startNetworkExecutors(){
        mDispatchers = new NetworkExecutor[mDispatcherNums];
        for(int i=0; i< mDispatcherNums; i++){
            mDispatchers[i] = new NetworkExecutor(mRequestQueue, mHttpStack);
            mDispatchers[i].start();
        }
    }

    public void start(){
        stop();
        startNetworkExecutors();
    }

    /**
     * 停止NetworkExecutor
     */
    public void stop(){
        if(mDispatchers != null && mDispatchers.length > 0){
            for(int i = 0; i< mDispatchers.length; i++){
                mDispatchers[i].quit();
            }
        }
    }

    //添加请求到队列中
    public void addRequest(Request<?> request){
        if(!mRequestQueue.contains(request)){
            //为请求设置序列号
            request.setSerialNumber(this.generateSerialNumber());
            mRequestQueue.add(request);
        }else{
            Log.d(TAG, "### 请求队列中已经含有");
        }
    }

    public void clear(){
        mRequestQueue.clear();
    }

    public BlockingQueue<Request<?>> getAllRequest(){
        return mRequestQueue;
    }

    /**
     * 为每一个请求生成一个序列号
     *
     * @return 序列号
     */
    private int generateSerialNumber(){
        return mSerialNumGenerator.incrementAndGet();
    }



}
