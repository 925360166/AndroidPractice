package com.example.my_okhttp;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public class NEHttp {

    public static<T, M> void sendJsonRequest(String url, T requestData,
                                             Class<M> response, IJsonDataTransforListener jsonDataTransforListener){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener = new JsonCallbackListener<>(response, jsonDataTransforListener);
        HttpTask httpTask = new HttpTask(url, requestData, httpRequest, callBackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
