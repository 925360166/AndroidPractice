package com.example.songwei.androidpractice.CustomViews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.songwei.androidpractice.R;

/**
 * Created by zheng on 2018/9/11.
 */

public class SimpleTitleBar extends LinearLayout {
    private TextView mTvTitle;

    public SimpleTitleBar(Context context) {
        this(context, null);
    }

    public SimpleTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.simple_title_bar, this);
        mTvTitle = (TextView) findViewById(R.id.common_txt_title);
    }

    public void setTitle(CharSequence title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    public void setBackgroundIcon(Drawable background) {
        findViewById(R.id.ll_simple_title_bar_root).setBackground(background);
    }
}
