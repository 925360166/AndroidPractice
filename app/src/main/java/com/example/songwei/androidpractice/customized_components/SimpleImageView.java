package com.example.songwei.androidpractice.customized_components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.songwei.androidpractice.R;

/**
 * 简单的ImageView,用于显示图片
 */
public class SimpleImageView extends View {

    //画笔
    private Paint mBitmapPaint;
    //图片 drawable
    private Drawable mDrawable;
    //View的宽度
    private int mWidth;
    //View的高度
    private int mHeight;



    public SimpleImageView(Context context) {
        super(context);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //根据属性初始化
        initAttrs(attrs);
        //初始化画笔
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs) {
        if(attrs != null){
            TypedArray array = null;
            try{
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                //根据图片的id获取到Drawable对象
                mDrawable = array.getDrawable(R.styleable.SimpleImageView_src);
                //测量Drawable对象的宽、高
                measureDrawable();
            }finally{
                if(array != null){
                    array.recycle();
                }
            }
        }
    }

    private void measureDrawable() {
        if(mDrawable == null){
            throw new RuntimeException("drawable 不能为空！");
        }
        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();
    }


    public SimpleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
