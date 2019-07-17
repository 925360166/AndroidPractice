package com.example.songwei.androidpractice;

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
import com.example.songwei.androidpractice.CrashHandler.CrashActivity;
import com.example.songwei.androidpractice.CustomViews.CustomViewsActivity;
import com.example.songwei.androidpractice.HandlerThread.HandlerThreadActivity;
import com.example.songwei.androidpractice.ImageLoader.ImageLoaderActivity;
import com.example.songwei.androidpractice.IntentService.IntentServiceActivity;
import com.example.songwei.androidpractice.RecyclerView.RecyclerView2Activity;
import com.example.songwei.androidpractice.RecyclerView.RecyclerViewActivity;
import com.example.songwei.androidpractice.ui_optimise.UiOptimiseActivity;

import com.example.songwei.androidpractice.BgTransparentTest.BgTransparentActivity;

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
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button14).setOnClickListener(this);
        findViewById(R.id.button15).setOnClickListener(this);
        findViewById(R.id.button16).setOnClickListener(this);
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
                startActivity(ImageLoaderActivity.class);
                break;
            case R.id.button8:
                startActivity(CrashActivity.class);
                break;
            case R.id.button9:
                startActivity(UiOptimiseActivity.class);
                break;
            case R.id.button10:
                startActivity(HandlerThreadActivity.class);
                break;
            case R.id.button11:
                startActivity(IntentServiceActivity.class);
                break;
            case R.id.button12:
                startActivity(CustomViewsActivity.class);
                break;
            case R.id.button13:
                startActivity(RecyclerView2Activity.class);
                break;
            case R.id.button14:
                startActivity(BgTransparentActivity.class);
                break;
            case R.id.button15:
//                startActivity( );
                break;
            case R.id.button16:
//                startActivity( );
                break;
            case R.id.button17:
//                startActivity( );
                break;
            case R.id.button18:
//                startActivity( );
                break;
            case R.id.button19:
//                startActivity( );
                break;
            case R.id.button20:
//                startActivity( );
                break;
            default:
                Toast.makeText(MainActivity.this, "无效跳转", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void startActivity(Class clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        MainActivity.this.startActivity(intent);
    }

}
