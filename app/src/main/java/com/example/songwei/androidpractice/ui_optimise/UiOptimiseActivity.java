package com.example.songwei.androidpractice.ui_optimise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.example.songwei.androidpractice.R;

public class UiOptimiseActivity extends AppCompatActivity {

    private static final String TAG = "UiOptimiseActivity";

    private ViewStub viewStub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_optimise_include);
        initView();
//        showViewStub();
    }

    private void initView() {
        viewStub = (ViewStub)findViewById(R.id.stub_import);


        findViewById(R.id.bt_inflate_view_stub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateViewStub();
            }
        });
        findViewById(R.id.bt_hide_view_stub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViewStub();
            }
        });
        findViewById(R.id.bt_gone_view_stub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goneViewStub();
            }
        });
        findViewById(R.id.bt_show_view_stub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewStub();
            }
        });

    }

    private void inflateViewStub(){
        viewStub.inflate();
    }
    private void hideViewStub() {
        viewStub.setVisibility(View.INVISIBLE);
    }

    private void goneViewStub() {
        viewStub.setVisibility(View.GONE);
    }

    private void showViewStub() {
        //以下两种方式均能加载ViewStub
//        ((ViewStub)findViewById(R.id.stub_import)).setVisibility(View.VISIBLE);
        viewStub.setVisibility(View.VISIBLE);
    }


}
