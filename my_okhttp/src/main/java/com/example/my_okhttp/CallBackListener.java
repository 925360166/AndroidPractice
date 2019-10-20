package com.example.my_okhttp;

import java.io.InputStream;

/**
 * Author: SongWei
 * Date:   2019/10/17
 * Usage:
 */
public interface CallBackListener {

    void onSuccess(InputStream inputStream);

    void onFailure(Exception e);
}
