package com.example.songwei.androidpractice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.songwei.androidpractice.HttpTest.HttpTestActivity;
import com.example.songwei.androidpractice.ImageLoader.ImageLoaderActivity;
import com.example.songwei.androidpractice.IntentService.IntentServiceActivity;
import com.example.songwei.androidpractice.MaterialDesign.TabLayoutActivity;
import com.example.songwei.androidpractice.MyOkHttpTest.MyOkHttpActivity;
import com.example.songwei.androidpractice.RecyclerView.RecyclerView2Activity;
import com.example.songwei.androidpractice.RecyclerView.RecyclerViewActivity;
import com.example.songwei.androidpractice.ScreenAdaptation.ScreenAdaptationActivity;
import com.example.songwei.androidpractice.SocketTest.SocketTestActivity;
import com.example.songwei.androidpractice.todayinformation.splash.SplashActivity;
import com.example.songwei.androidpractice.ui_optimise.UiOptimiseActivity;

import com.example.songwei.androidpractice.BgTransparentTest.BgTransparentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //声明一个数组permissions，将所有需要申请的权限都放在里面
    private String[] permissions = new String[]{
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    private int mRequestCode = 1;

    //逐个判断哪些权限未授权，将未授权的权限存储到mPermissionList中
    private List<String> mPermissionsDenied = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermissions();
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
        findViewById(R.id.button17).setOnClickListener(this);
        findViewById(R.id.button18).setOnClickListener(this);
        findViewById(R.id.button19).setOnClickListener(this);
        findViewById(R.id.button20).setOnClickListener(this);
    }

    //动态申请权限
    private void initPermissions(){
        if (Build.VERSION.SDK_INT >= 23){
            mPermissionsDenied.clear();
            //逐个判断是否还有未通过的权限
            for(int i = 0; i < permissions.length; i++){
                if(ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED){
                    //添加未授权的权限到mPermissionsDenied中
                    mPermissionsDenied.add(permissions[i]);
                }
            }
            //申请权限
            if(mPermissionsDenied.size() > 0){
                ActivityCompat.requestPermissions(this, permissions, mRequestCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //是否有权限申请未被通过
        boolean hasPerDenied = false;
        if(mRequestCode == requestCode){
            for(int i = 0; i < grantResults.length; i++){
                if(grantResults[i] == -1){
                    hasPerDenied = true;
                    break;
                }
            }
        }
        //有权限未被允许
        if(hasPerDenied){
            Toast.makeText(this, "有权限被拒绝，请在设置页面允许所有权限，谢谢~", Toast.LENGTH_SHORT).show();
        }
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
                startActivity(TabLayoutActivity.class);
                break;
            case R.id.button16:
                startActivity(SplashActivity.class);
                break;
            case R.id.button17:
                startActivity(HttpTestActivity.class);
                break;
            case R.id.button18:
                startActivity(ScreenAdaptationActivity.class);
                break;
            case R.id.button19:
                startActivity(SocketTestActivity.class);
                break;
            case R.id.button20:
                startActivity(MyOkHttpActivity.class);
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
