package com.example.songwei.androidpractice.SimpleNet;

import android.os.Build;

/**
 * 根据api版本选择HttpClient或者HttpUrlConnection
 */
public final class HttpStackFactory {

    private static final int GINGERBREAD_SDK_NUM = 9;

    /**
     * 根据SDK版本号来创建不同的HTTP执行器，
     * 即SDK 9之前使用HttpClient，之后则使用HttpUrlConnection
     * @return 具体的HttpStack
     */
    public static HttpStack createHttpStack(){
        int runtimeSDKApi = Build.VERSION.SDK_INT;
        if(runtimeSDKApi >= GINGERBREAD_SDK_NUM){
            return new HttpUrlConnStack();
        }
        return new HttpClientStack();
    }
}
