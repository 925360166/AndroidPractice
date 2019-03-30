package com.example.songwei.androidpractice.CrashHandler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.songwei.androidpractice.R;

public class CrashActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里模拟异常抛出情况，人为地抛出一个运行时异常
                throw new RuntimeException("自定义异常：在CrashActivity中抛出的异常");
            }
        });
    }
}
