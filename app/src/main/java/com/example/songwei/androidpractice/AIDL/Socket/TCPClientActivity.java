package com.example.songwei.androidpractice.AIDL.Socket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songwei.androidpractice.AIDL.ContentProvider.ProviderActivity;
import com.example.songwei.androidpractice.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClientActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TCPClientActivity";

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private static final int MESSAGE_SEND_MSG = 3;
    private static final int MESSAGE_REFRESH_VIEW = 4;

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mMessageTextView.setText(mMessageTextView.getText()
                            + (String) msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    Toast.makeText(TCPClientActivity.this, "socket连接已建立", Toast.LENGTH_SHORT).show();
                    mSendButton.setEnabled(true);
                    break;
                case MESSAGE_SEND_MSG:
                    final String msgSend = mMessageEditText.getText().toString();
                    new Thread() {
                        @Override
                        public void run() {
                            if (!TextUtils.isEmpty(msgSend) && mPrintWriter != null) {
                                mPrintWriter.println(msgSend);
                                String time = formatDateTime(System.currentTimeMillis());
                                final String showedMsg = "self " + time + ": " + msgSend + "\n";

                                Message message = mHandler.obtainMessage(MESSAGE_REFRESH_VIEW);
                                message.obj = showedMsg;
                                message.sendToTarget();
                            }
                        }
                    }.start();
                    break;
                case MESSAGE_REFRESH_VIEW:
                    String showedMsg = (String) msg.obj;
                    mMessageEditText.setText("");
                    mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_client);
        mMessageTextView = (TextView) findViewById(R.id.msg_container);
        mSendButton = (Button) findViewById(R.id.send);
        mSendButton.setOnClickListener(this);
        mMessageEditText = (EditText) findViewById(R.id.msg);
        Intent intentService = new Intent(this, TCPServerService.class);
        startService(intentService);
        new Thread() {
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == mSendButton) {
            mHandler.obtainMessage(MESSAGE_SEND_MSG).sendToTarget();
        }
    }

    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream()
                                )
                        ), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success.");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                System.out.println("connect tcp server failed, retry...");
            }
        }

        try {
            //接收服务器端的消息
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("receive: " + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server: " + time + ": " + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget();
                }
            }
            System.out.println("quit...");
            mPrintWriter.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TCPClientActivity.class));
    }
}
