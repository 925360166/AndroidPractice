package com.example.songwei.androidpractice.CustomViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.songwei.androidpractice.R;

public class CustomViewsActivity extends AppCompatActivity {

    private static final String TAG = "CustomViewsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_views);
        initView();
    }

    private void initView() {
        findViewById(R.id.cv_circle_view).setVisibility(View.GONE);
        findViewById(R.id.cv_circle_wave_view).setVisibility(View.VISIBLE);
    }
}
