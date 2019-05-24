package com.example.songwei.androidpractice;

import android.content.Context;
import android.text.TextUtils;

import com.example.songwei.androidpractice.env.EnvUtils;

public class AppEnv {

    private static Context sContext;
    private static String sPackageName;
    private static int sVersionCode;
    private static boolean isUIProcess;

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context){
        AppEnv.sContext = context;
        String processName = EnvUtils.getProcessName();
        isUIProcess = processName.equals(context.getPackageName());
    }

    public static String getPackageName(){
        if(TextUtils.isEmpty(sPackageName)){
            sPackageName = getContext().getPackageName();
        }
        return sPackageName;
    }

    public static int getVersionCode(){
        if(sVersionCode <= 0){
            sVersionCode = EnvUtils.getVersionCode(sContext, getPackageName());
        }
        return sVersionCode;
    }

    public static boolean isUIProcess(){
        return isUIProcess;
    }


}
