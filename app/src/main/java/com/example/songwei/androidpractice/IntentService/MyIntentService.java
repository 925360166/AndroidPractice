package com.example.songwei.androidpractice.IntentService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 1. 定义
 * Android里的一个封装类，继承四大组件之一的Service
 * <p>
 * 2. 作用
 * 处理异步请求 & 实现多线程
 * <p>
 * 3. 使用场景
 * 线程任务 需 按顺序、在后台执行
 * <p>
 * <p>
 * 最常见的场景：离线下载
 * 不符合多个数据同时请求的场景：所有的任务都在同一个Thread looper里执行
 * <p>
 * <p>
 * <p>
 * 4. 使用步骤
 * 步骤1：定义 IntentService的子类，需复写onHandleIntent()方法
 * 步骤2：在Manifest.xml中注册服务
 * 步骤3：在Activity中开启Service服务
 * <p>
 * 作者：Carson_Ho
 * 链接：https://www.jianshu.com/p/af62781fefba
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyIntentService() {
        //调用父类的构造函数
        //参数 = 工作线程的名字
        super("myIntentService");
    }

    /**
     * 复写onHandleIntent()方法
     * 根据Intent实现耗时任务、操作
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //根据Intent的不同，进行不同的事务处理
        String taskname = intent.getExtras().getString("taskname");
        switch (taskname) {
            case "task1":
                Log.d(TAG, "do task1");
                break;
            case "task2":
                Log.d(TAG, "do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    /**
     *
     * 复写onStartCommand()方法
     * 默认实现 = 将请求的Intent添加到工作队列里
     * @return
     */
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
