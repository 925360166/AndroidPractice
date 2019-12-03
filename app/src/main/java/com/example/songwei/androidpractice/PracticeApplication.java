package com.example.songwei.androidpractice;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.common_lib.AppConfig;
import com.example.common_lib.IAppCompat;
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


        //初始化Login以及Mine组件里的Service
        for(String component : AppConfig.COMPONENTS){
            try{
                Class<?> clazz = Class.forName(component);
                Object object = clazz.newInstance();
                if(object instanceof IAppCompat){
                    ((IAppCompat) object).initialize(this);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    public static PracticeApplication getInstance(){
        return sInstance;
    }
}
