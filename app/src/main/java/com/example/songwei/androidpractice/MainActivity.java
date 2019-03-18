package com.example.songwei.androidpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.songwei.androidpractice.AIDL.AIDLBookActivity;
import com.example.songwei.androidpractice.AIDL.AIDLRestaurantActivity;
import com.example.songwei.androidpractice.AIDL.ContentProvider.ProviderActivity;
import com.example.songwei.androidpractice.AIDL.Messenger.MessengerActivity;
import com.example.songwei.androidpractice.AIDL.Socket.TCPClientActivity;

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
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
    }

    //TODO: 添加测试页面时，在此补充 fixme
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(RecyclerViewActivity.class);
                break;
            case R.id.button2:
                startActivity(AIDLBookActivity.class);
                break;
            case R.id.button3:
                startActivity(AIDLRestaurantActivity.class);
                break;
            case R.id.button4:
                startActivity(MessengerActivity.class);
                break;
            case R.id.button5:
                startActivity(ProviderActivity.class);
                break;
            case R.id.button6:
                startActivity(TCPClientActivity.class);
                break;
            case R.id.button7:

                break;
            case R.id.button8:

                break;
            case R.id.button9:

                break;
            case R.id.button10:

                break;
            default:
                Toast.makeText(MainActivity.this, "无效跳转", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void startActivity(Class clazz){
        Intent intent = new Intent(MainActivity.this, clazz);
        MainActivity.this.startActivity(intent);
    }

}
