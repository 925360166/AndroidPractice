package com.example.songwei.androidpractice.ui_optimise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewStub;

import com.example.songwei.androidpractice.R;

public class UiOptimiseActivity extends AppCompatActivity {

    private static final String TAG = "UiOptimiseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_optimise_include);
        showViewStub();
    }

    private void showViewStub() {
        //以下两种方式均能加载ViewStub
//        ((ViewStub)findViewById(R.id.stub_import)).setVisibility(View.VISIBLE);
        ((ViewStub)findViewById(R.id.stub_import)).inflate();
    }
}
