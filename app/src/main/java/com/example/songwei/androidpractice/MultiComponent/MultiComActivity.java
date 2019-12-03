package com.example.songwei.androidpractice.MultiComponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.common_lib.ServiceFactory;
import com.example.songwei.androidpractice.R;

/**
 * Author: SongWei
 * Date:   2019/11/28
 * Usage:
 */
public class MultiComActivity extends AppCompatActivity {

//    private TextView tvGoLogin;
//    private TextView tvGoMine;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_component);
        findViewById(R.id.tv_go_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ILoginService
                ServiceFactory.getInstance().getLoginService().launchLoginActivity(MultiComActivity.this);
            }
        });

        findViewById(R.id.tv_go_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceFactory.getInstance().getMineService().launchMineActivity(MultiComActivity.this);
            }
        });

    }
}
