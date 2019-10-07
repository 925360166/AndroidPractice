package com.example.songwei.androidpractice.ScreenAdaptation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.songwei.androidpractice.R;
import com.example.songwei.androidpractice.utils.CustomDensityUtil;

/**
 * Author: SongWei
 * Date:   2019/10/6
 * Usage:
 */
public class ScreenAdaptationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomDensityUtil.setCustomDensity(this, this.getApplication());
        setContentView(R.layout.activity_screen_adaptation);
    }
}
