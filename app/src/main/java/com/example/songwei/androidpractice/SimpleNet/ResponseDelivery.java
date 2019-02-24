package com.example.songwei.androidpractice.SimpleNet;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class ResponseDelivery implements Executor {

    //关联主线程消息队列的handler
    Handler mResponseHandler = new Handler(Looper.getMainLooper());

    public void deliveryResponse(final Request<?> request, final Response response){
        Runnable respRunnable = new Runnable() {
            @Override
            public void run() {
                request.deliveryResponse(response);
            }
        };

        execute(respRunnable);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mResponseHandler.post(command);
    }
}
