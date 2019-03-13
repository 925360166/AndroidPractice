package com.example.songwei.androidpractice.AIDL;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songwei.androidpractice.R;

import java.util.List;

/**
 * AIDL通信的客户端
 */
public class AIDLRestaurantActivity extends AppCompatActivity {

    private static final String TAG = "AIDLRestaurantActivity";

    //由AIDL文件生成的java类
    private IRestaurant mIRestaurant = null;

    private int customerName = 0;

    private TextView tvLog;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(AIDLRestaurantActivity.this, "已建立连接", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "service connected");
            mIRestaurant = IRestaurant.Stub.asInterface(service);
            try {
                mIRestaurant.registerCallback(mINotifyCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(AIDLRestaurantActivity.this, "连接已断开", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "service disconnected");
            try {
                mIRestaurant.unregisterCallback(mINotifyCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private INotifyCallback mINotifyCallback = new INotifyCallback.Stub() {

        @Override
        public void notifyMainUiThread(String name, boolean isJoin) throws RemoteException {
            String toastStr = "";
            if (isJoin) {
                toastStr = name + "进入了餐厅";
            } else {
                toastStr = name + "离开了餐厅";
            }
            tvLog.setText(toastStr);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_restaurant);
        tvLog = (TextView) findViewById(R.id.tv_log);
    }

    public void bindService(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.songwei.aidl.restaurant");
        intent.setPackage("com.example.songwei.androidpractice");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(mServiceConnection);
    }

    public void addCustomer(View view) {
        try {
            mIRestaurant.join(new Binder(), String.valueOf(++customerName));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void leaveCustomer(View view) {
        try {
            // mIRestaurant.registerCallBack(mNotifyCallBack);
            mIRestaurant.leave();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void showAIDLRestaurantActivity(Context context) {
        context.startActivity(new Intent(context, AIDLRestaurantActivity.class));
    }

}
