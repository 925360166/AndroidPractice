package com.example.my_okhttp;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public class HttpTask<T> implements Runnable {

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
        this.request.execute();
    }
}
