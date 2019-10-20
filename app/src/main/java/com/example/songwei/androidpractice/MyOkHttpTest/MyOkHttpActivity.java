package com.example.songwei.androidpractice.MyOkHttpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.my_okhttp.IJsonDataTransforListener;
import com.example.my_okhttp.NEHttp;
import com.example.songwei.androidpractice.R;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public class MyOkHttpActivity extends AppCompatActivity {

    private String url = "http://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";
    private String urlWrong = "http://xxxxx";

    private TextView tvMyOkHttp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myokhttp);
        tvMyOkHttp = findViewById(R.id.tv_my_okhttp);
        sendRequest();
    }

    private void sendRequest() {

//        NEHttp.sendJsonRequest(url, null, NeBean.class, new IJsonDataTransforListener<NeBean>() {
        NEHttp.sendJsonRequest(urlWrong, null, NeBean.class, new IJsonDataTransforListener<NeBean>() {
            @Override
            public void onSuccess(NeBean m) {
                Log.e("======>", m.toString());
                tvMyOkHttp.setText(m.toString());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                tvMyOkHttp.setText(e.getMessage());
            }
        });
    }
}
