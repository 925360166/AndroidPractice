package com.example.my_okhttp;

/**
 * Author: SongWei
 * Date:   2019/10/17
 * Usage:
 */
public interface IHttpRequest {

    void setUrl(String url);

    void setData(byte[] data);

    void setListener(CallBackListener listener);

    void execute();
}
