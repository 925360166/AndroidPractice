package com.example.songwei.androidpractice.IntentService;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.songwei.androidpractice.R;

/**
 *
 * https://www.jianshu.com/p/af62781fefba
 *
 */
public class IntentServiceActivity extends AppCompatActivity {

    private static final String TAG = "IntentServiceActivity";
    private static final String Intent_Filter = "com.songwei.practice.intent_service";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        findViewById(R.id.bt_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentService();
            }
        });
    }

    private void startIntentService() {
        //同一服务只会开启1个工作线程
        //在onHandleIntent()函数里，依次处理传入的Intent请求
        //将请求通过Bundle兑对象传入到Intent,再传入到服务里

        //请求1
//        Intent i1 = new Intent(Intent_Filter);
        Intent i1 = new Intent(IntentServiceActivity.this, MyIntentService.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("taskname", "task1");
        i1.putExtras(bundle1);
        startService(i1);

        //请求2
//        Intent i2 = new Intent(Intent_Filter);
        Intent i2 = new Intent(IntentServiceActivity.this, MyIntentService.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("taskname", "task2");
        i2.putExtras(bundle2);
        startService(i2);

        //多次启动
        startService(i1);
    }
}
