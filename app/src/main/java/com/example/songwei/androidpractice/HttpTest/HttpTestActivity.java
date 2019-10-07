package com.example.songwei.androidpractice.HttpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.songwei.androidpractice.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: SongWei
 * Date:   2019/10/5
 * Usage:
 */
public class HttpTestActivity extends AppCompatActivity {

    private static final String TAG = "HttpTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
//        initGetNetData();
        initPostNetData();
    }

    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient();// okhttp 配置一些默认
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "0f08cd674792667feb5ce236ea028747");

        //建造者设计模式
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(builder.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 发送网络请求数据
     */
    private void initGetNetData() {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
        builder.addQueryParameter("sort", "desc");
        builder.addQueryParameter("page", "1");
        builder.addQueryParameter("pagesize", "2");
        builder.addQueryParameter("time", "" + System.currentTimeMillis()/1000);
        builder.addQueryParameter("key", "" + "bbc57dd5e4f05991aff09eafd2e667e0");

        //建造者设计模式
        Request request = new Request.Builder()
                .url(builder.build())
                .get()
                .build();
        Call call = client.newCall(request);
//        try {
//            Response response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}
