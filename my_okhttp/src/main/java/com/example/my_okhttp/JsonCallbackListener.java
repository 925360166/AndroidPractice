package com.example.my_okhttp;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public class JsonCallbackListener<T> implements CallBackListener {

    private Class<T> responseClass;
    private IJsonDataTransforListener dataTransforListener;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    public JsonCallbackListener(Class<T> responseClass, IJsonDataTransforListener dataTransforListener) {
        this.responseClass = responseClass;
        this.dataTransforListener = dataTransforListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //将流转换为对应的bean
        String response = getContent(inputStream);
        final T clazz = JSON.parseObject(response, responseClass);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                dataTransforListener.onSuccess(clazz);
            }
        });
    }

    @Override
    public void onFailure(Exception e) {
        dataTransforListener.onFailure(e);
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error=" + e.toString());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error=" + e.toString());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
