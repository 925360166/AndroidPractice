package com.example.my_okhttp;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest request;

    public HttpTask(String url, T requestData, IHttpRequest httpRequest, CallBackListener listener){
        this.request = httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(listener);
        String content = JSON.toJSONString(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            this.request.execute();
        }catch (Exception e){
            ThreadPoolManager.getInstance().addDelayTask(this);
        }
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    private long delayTime;
    private int retryCount;



    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return unit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return 0;
    }
}
