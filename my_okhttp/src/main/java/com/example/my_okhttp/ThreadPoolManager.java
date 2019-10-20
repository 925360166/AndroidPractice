package com.example.my_okhttp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: SongWei
 * Date:   2019/10/17
 * Usage: 线程池管理类
 */
public class ThreadPoolManager {

    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    //创建存放网络请求的队列
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    //将请求放入队列
    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        addTask(r);
                    }
                });
        mThreadPoolExecutor.execute(ddThread);
    }

    //创建调度线程
    public Runnable ddThread = new Thread(){
        Runnable runn = null;
        @Override
        public void run() {
            while(true){
                try{
                    runn = mQueue.take();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(runn != null) {
                    mThreadPoolExecutor.execute(runn);
                }
            }
        }
    };

}
