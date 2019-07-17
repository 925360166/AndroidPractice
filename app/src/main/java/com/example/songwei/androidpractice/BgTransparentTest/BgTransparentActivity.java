package com.example.songwei.androidpractice.BgTransparentTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.songwei.androidpractice.R;

public class BgTransparentActivity extends Activity {

    private static final String TAG = "BgTransparentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgtransparent);
        Toast.makeText(this, "无contentView的Activity已显示", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate called, no set contentView");
    }
}
