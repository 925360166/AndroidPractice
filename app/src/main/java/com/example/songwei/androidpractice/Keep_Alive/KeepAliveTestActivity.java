package com.example.songwei.androidpractice.Keep_Alive;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.songwei.androidpractice.R;

/**
 * Author: SongWei
 * Date:   2019/12/8
 * Usage:
 */
public class KeepAliveTestActivity extends Activity {

    private static final String TAG = "KeepAliveTestActivity";

    private RadioButton rbOn, rbOff;
    private static final String SHAREDPRE_KEEP_ALIVE = "keep_alive";
    private boolean keepAliveOn = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive_test);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences sp = this.getSharedPreferences(SHAREDPRE_KEEP_ALIVE, Context.MODE_PRIVATE);
        keepAliveOn = sp.getBoolean("keep_alive", false);
        rbOn.setChecked(keepAliveOn);
        rbOff.setChecked(!keepAliveOn);
    }

    //进程保活开关的状态
    public static boolean isKeepAliveOn(Context context){
        SharedPreferences sp = context.getSharedPreferences(SHAREDPRE_KEEP_ALIVE, Context.MODE_PRIVATE);
        return sp.getBoolean("keep_alive", false);
    }

    private void initView() {
        rbOn = findViewById(R.id.rb_keep_alive_on);
        rbOff = findViewById(R.id.rb_keep_alive_off);
        rbOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //keep_alive_on
                keepAliveOn(isChecked);
            }
        });
        rbOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //keep_alive_off
                keepAliveOff(isChecked);
            }
        });
    }

    private void keepAliveOn(boolean isChecked) {
        Log.e(TAG, "keepAliveOn: " + (isChecked ? "On" : "Off"));

    }

    private void keepAliveOff(boolean isChecked) {
        Log.e(TAG, "keepAliveOff: " + (isChecked ? "On" : "Off"));
    }
}
