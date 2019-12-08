package com.example.songwei.androidpractice.Keep_Alive;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Author: SongWei
 * Date:   2019/12/8
 * Usage: 设置1px的activity进行保活
 */
public class KeepAliveActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //设置activity右上角
        window.setGravity(Gravity.START | Gravity.TOP);

        WindowManager.LayoutParams attr = window.getAttributes();
        attr.width = 1;
        attr.height = 1;
        attr.x = 0;
        attr.y = 0;

        window.setAttributes(attr);

    }
}
