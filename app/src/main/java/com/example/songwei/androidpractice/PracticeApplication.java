package com.example.songwei.androidpractice;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.songwei.androidpractice.CrashHandler.CrashHandler;

public class PracticeApplication extends Application {
    private static final String TAG = "PracticeApplication";

    private static PracticeApplication sInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //mark process start
        Log.e(TAG, "attachBaseContext");
        AppEnv.setContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //在这里为应用设置异常处理，然后程序才能获取未处理的异常
        CrashHandler crashHandler = CrashHandler.getsInstance();
        crashHandler.init(this);
    }


    public static PracticeApplication getInstance(){
        return sInstance;
    }
}
