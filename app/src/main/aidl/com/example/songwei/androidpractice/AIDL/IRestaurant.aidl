// IRestaurant.aidl
package com.example.songwei.androidpractice.AIDL;

// Declare any non-default types here with import statements
import com.example.songwei.androidpractice.AIDL.INotifyCallback;

interface IRestaurant {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     //新来了一个顾客
    void join(IBinder token,String name);

    //走了一个顾客
    void leave();

    //注册回调接口
    void registerCallback(INotifyCallback cb);

    //解除注册接口
    void unregisterCallback(INotifyCallback cb);
}
