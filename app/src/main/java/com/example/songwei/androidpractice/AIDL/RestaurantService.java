package com.example.songwei.androidpractice.AIDL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestaurantService extends Service {

    private static final String TAG = "RestaurantService";

    //这个list 就是用来存储当前餐厅有多少顾客 注意我们为什么没有用顾客的名字来存储？
    //而是用了这个CustomerClient的类 看这个类的注释即可明白
    private List<MyDeathRecipient> mDeathRecipientList = new ArrayList<>();

    //上面用CustomerClient 的原因是因为害怕客户端异常销毁时，服务器收不到消息 造成资源浪费等异常
    //同样的 我们在服务端通知客户端消息的时候 也害怕 服务端 会异常销毁 导致客户端收不到消息
    //好在谷歌早就为我们考虑到这种情况  提供了RemoteCallbackList 来完成对应的功能
    //避免我们再重复一遍上述的过程
    private RemoteCallbackList<INotifyCallback> mCallBacks = new RemoteCallbackList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        //销毁回调资源 否则要内存泄露
        mCallBacks.kill();
        super.onDestroy();
    }

    private final IRestaurant.Stub mBinder = new IRestaurant.Stub() {

        @Override
        public void join(IBinder token, String name) throws RemoteException {
            MyDeathRecipient dp = new MyDeathRecipient(token, name);
            mDeathRecipientList.add(dp);
            notifyCallback(name, true);
        }

        @Override
        public void leave() throws RemoteException {
            //顾客离开的时候 我们随机让他离开一个就行了
            int length = mDeathRecipientList.size();
            int randomIndex = new Random().nextInt(length - 1);
            notifyCallback(mDeathRecipientList.get(randomIndex).mCustomerName, false);
            mDeathRecipientList.remove(randomIndex);
        }

        @Override
        public void registerCallback(INotifyCallback cb) throws RemoteException {
            mCallBacks.register(cb);
        }

        @Override
        public void unregisterCallback(INotifyCallback cb) throws RemoteException {
            mCallBacks.unregister(cb);
        }
    };

    private void notifyCallback(String customerName, boolean isJoin) {
        final int len = mCallBacks.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                // 通知回调
                mCallBacks.getBroadcastItem(i).notifyMainUiThread(customerName, isJoin);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallBacks.finishBroadcast();
    }

    //实际上 这个接口 就是用来 当客户端自己发生崩溃时， 我们的服务端也能收到这个崩溃的消息
    //并且会调用binderDied 这个回调方法，所以你看这个内部类的代码 就明白了 无非就是保证当客户端异常销毁的时候
    //我们服务端也要保证收到这个消息 然后做出相应的应对
    final class MyDeathRecipient implements IBinder.DeathRecipient {
        public final IBinder mToken;
        public final String mCustomerName;

        public MyDeathRecipient(IBinder mToken, String mCustomerName) {
            this.mToken = mToken;
            this.mCustomerName = mCustomerName;
        }

        @Override
        public void binderDied() {
            //我们的应对方法就是当客户端 也就是顾客异常消失的时候 我们要把这个list里面 的对象也移出掉
            if (mDeathRecipientList.indexOf(this) >= 0) {
                mDeathRecipientList.remove(this);
            }
        }
    }
}
