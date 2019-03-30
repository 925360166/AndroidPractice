package com.example.songwei.androidpractice;

import android.app.Application;

import com.example.songwei.androidpractice.CrashHandler.CrashHandler;

public class AndroidPracticeApp extends Application {

    private static AndroidPracticeApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //在这里为应用设置异常处理，然后程序才能获取未处理的异常
        CrashHandler crashHandler = CrashHandler.getsInstance();
        crashHandler.init(this);
    }


    public static AndroidPracticeApp getInstance(){
        return sInstance;
    }
}
