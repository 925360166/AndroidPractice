package com.example.songwei.androidpractice.Glide_Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.songwei.androidpractice.R;

public class GlideTestActivity extends AppCompatActivity {

    private static final String TAG = "GlideTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        setGlide();
    }

    private void setGlide() {
//        Glide.with(this).load("");
    }
}
