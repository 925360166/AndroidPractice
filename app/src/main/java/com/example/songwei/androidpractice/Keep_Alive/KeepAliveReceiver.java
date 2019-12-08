package com.example.songwei.androidpractice.Keep_Alive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Author: SongWei
 * Date:   2019/12/8
 * Usage:
 */
public class KeepAliveReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)){
            //关屏

        }else if(TextUtils.equals(action, intent.ACTION_SCREEN_ON)){
            //开屏

        }
    }
}
