package com.example.songwei.androidpractice.Keep_Alive;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Author: SongWei
 * Date:   2019/12/8
 * Usage: 管理开屏关屏保活
 */
public class KeepAliveManager {

    private static final KeepAliveManager ourInstance = new KeepAliveManager();

    public static KeepAliveManager getInstance(){
        return ourInstance;
    }

    private KeepAliveManager(){
    }

    private KeepAliveReceiver keepAliveReceiver;

    /**
     * 注册 关屏、开屏的广播接收者
     */
    public void registerKeepAlive(Context context){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        keepAliveReceiver = new KeepAliveReceiver();
        context.registerReceiver(keepAliveReceiver, filter);
    }

    /**
     * 注销
     */
    public  void unregisterKeepAlive(Context context){
        if(null != keepAliveReceiver){
            context.unregisterReceiver(keepAliveReceiver);
        }
    }

}
