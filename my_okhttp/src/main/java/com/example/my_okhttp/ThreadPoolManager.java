package com.example.my_okhttp;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
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
    public void addTask(HttpTask httpTask) {
        if (httpTask != null) {
            try {
                mQueue.put(httpTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //失败队列（重复次数，延迟时间）
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

    //将失败的任务添加到delayQueue
    public void addDelayTask(HttpTask t){
        if(t != null){
            t.setDelayTime(3000);
            mDelayQueue.offer(t);
        }
    }

    //延迟队列调度线程
    public Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            HttpTask ht = null;
            while(true){
                try{
                    ht = mDelayQueue.take();
                    if(ht.getRetryCount() < 3){
                        mThreadPoolExecutor.execute(ht);
                        ht.setRetryCount(ht.getRetryCount() + 1);
                        Log.e("===重试机制===", ht.getRetryCount() + "");
                    }else{
                        Log.e("===重试机制===", "总是失败，不处理了");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    };


    //创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        HttpTask task = (HttpTask)r;
                        if(task.getRetryCount() <= 0){
                            addTask(task);
                        }else{
                            addDelayTask(task);
                        }
                    }
                });
        mThreadPoolExecutor.execute(ddThread);
        mThreadPoolExecutor.execute(delayThread);
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
