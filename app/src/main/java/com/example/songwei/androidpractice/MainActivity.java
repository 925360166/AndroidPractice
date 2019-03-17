package com.example.songwei.androidpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.songwei.androidpractice.AIDL.AIDLBookActivity;
import com.example.songwei.androidpractice.AIDL.AIDLRestaurantActivity;
import com.example.songwei.androidpractice.AIDL.Messenger.MessengerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    //TODO: 添加测试页面时，在此补充 fixme
    private void initView() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    //TODO: 添加测试页面时，在此补充 fixme
    @Override
    public void onClick(View view) {
        Context context = MainActivity.this;
        switch (view.getId()) {
            case R.id.button1:
                RecyclerViewActivity.showRecyclerView(context);
                break;
            case R.id.button2:
                AIDLBookActivity.showAIDLBookActivity(context);
                break;
            case R.id.button3:
                AIDLRestaurantActivity.showAIDLRestaurantActivity(context);
                break;
            case R.id.button4:
                MessengerActivity.startActivity(context);
                break;
            default:
                Toast.makeText(MainActivity.this, "无效跳转", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
