package com.example.songwei.androidpractice.SocketTest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songwei.androidpractice.R;

/**
 * Android的socket通信的长连接，有心跳检测
 * https://blog.csdn.net/zh724738989/article/details/42007099
 */
public class SocketFrontActivity extends AppCompatActivity {

    private static final String TAG = "SocketFrontActivity";

    private Intent mServiceIntent;
    private IBackService iBackService;

    private TextView tv;
    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_front);
        initViews();
        initData();
    }

    private void initViews() {
        tv = (TextView)findViewById(R.id.tv);
        et = (EditText)findViewById(R.id.edit_text);
        btn = (Button)findViewById(R.id.button_socket_send);
    }

    private void initData() {
        mServiceIntent = new Intent(this, BackService.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et.getText().toString().trim();
                Log.i(TAG, str);
                try{
                    Log.i(TAG, "是否连接到Service: " + iBackService);
                    if(iBackService == null){
                        Toast.makeText(getApplicationContext(), "没有连接，可能是服务器已断开", Toast.LENGTH_SHORT).show();
                    }else{
                        boolean isSend = iBackService.sendMessage(str);
                        Toast.makeText(getApplicationContext(), isSend ? "success" : "fail", Toast.LENGTH_SHORT).show();
                        et.setText("");
                    }
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //已连接
            iBackService = IBackService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //未连接为空
            iBackService = null;

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        bindService(mServiceIntent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始服务， 最好在onResume中注册
        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 注销广播 最好在onPause上注销
        unregisterReceiver(mReceiver);
        // 注销服务
        unbindService(conn);
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BackService.HEART_BEAT_ACTION);
        intentFilter.addAction(BackService.MESSAGE_ACTION);
        registerReceiver(mReceiver, intentFilter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 消息广播
            if (action.equals(BackService.MESSAGE_ACTION)) {
                String stringExtra = intent.getStringExtra("message");
                tv.setText(stringExtra);
            } else if (action.equals(BackService.HEART_BEAT_ACTION)) {// 心跳广播
                tv.setText("正常心跳");
            }
        }
    };
}
