package com.example.my_okhttp;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public interface IJsonDataTransforListener<T> {

    void onSuccess(T m);

    void onFailure(Exception e);
}
