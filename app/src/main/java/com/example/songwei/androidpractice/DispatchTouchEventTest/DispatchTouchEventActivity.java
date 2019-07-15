package com.example.songwei.androidpractice.DispatchTouchEventTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * ##什么是UI事件？
 *
 * 触摸屏幕中UI控件的那一刻即为事件发生
 *
 * MotionEvent对象包含了所有的触摸事件，如“触摸的位置”、多指触摸等
 * MotionEvent描述了当前的操作类型，以下为常见类型（数字代表对应的值）：
 *
 * ACTION_DOWN=0 按下
 * ACTION_UP=1 抬起
 * ACTION_MOVE=2 移动
 * ACTION_CANCEL=3 动作取消
 * ACTION_OUTSIDE=4 动作超出边界
 * ACTION_POINTER_DOWN=5 已有一个点被按住，此时再按下一个点
 * ACTION_POINTER_UP= 6 多个点被按住，非最后放开的点都会调用
 *
 *
 *
 *
 */
public class DispatchTouchEventActivity extends AppCompatActivity {

    private static final String TAG = "DispatchTouchEvent";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "11");
    }
}
