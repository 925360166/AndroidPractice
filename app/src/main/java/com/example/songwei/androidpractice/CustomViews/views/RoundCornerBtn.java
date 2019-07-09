package com.example.songwei.androidpractice.CustomViews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.widget.TextView;

import com.example.songqwei.androidpractice.utils.CommonUIUtils;
import com.example.songwei.androidpractice.R;

/**
 * Created by ${zuowp291} on 2015/10/23.
 */
public class RoundCornerBtn extends TextView {
    private static final String TAG = "RoundCornerTextView";
    private static final int DISABLE_ALPHA = 71;
    private int colorStroke;
    private int strokeWidth;
    private int colorNormal;
    private int colorPressed;
    private int colorDisabled;
    private boolean isSettingBg;

    public RoundCornerBtn(Context context) {
        super(context);
    }

    public RoundCornerBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBgColor(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (!isSettingBg) {
            setRoundCornorBg(h);
        }
    }

    private void initBgColor(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.roundCornorBg, 0, 0);
        try {
            colorStroke = array.getColor(R.styleable.roundCornorBg_stroke_color, Color.BLACK);
            strokeWidth = array.getDimensionPixelSize(R.styleable.roundCornorBg_stroke_width,
                    CommonUIUtils.dip2px(context, 1));
            colorNormal = array.getColor(R.styleable.roundCornorBg_default_color, Color.WHITE);
            colorPressed = array.getColor(R.styleable.roundCornorBg_pressed_color, Color.LTGRAY);
            int defaultColor = Color.argb(DISABLE_ALPHA, Color.red(colorNormal), Color.green(colorNormal), Color.blue(colorNormal));
            colorDisabled = array.getColor(R.styleable.roundCornorBg_disabled_color, defaultColor);
        } finally {
            array.recycle();
        }
    }

    public void setBgStyle(int d, int p, int s, int sw) {
        colorNormal = getContext().getResources().getColor(d);
        colorPressed = getContext().getResources().getColor(p);
        colorStroke = getContext().getResources().getColor(s);
        strokeWidth = getContext().getResources().getDimensionPixelSize(sw);
        colorDisabled = Color.argb(DISABLE_ALPHA, Color.red(colorNormal), Color.green(colorNormal), Color.blue(colorNormal));
        setRoundCornorBg(getHeight());
    }

    private void setRoundCornorBg(int height) {
        isSettingBg = true;
        Drawable d = getBackgroundDrawable(height);
        if (d != null) {
            setBackgroundDrawable(d);
        }
        isSettingBg = false;
    }

    private Drawable getBackgroundDrawable(int height) {
        if (height > 0) {
            StateListDrawable sd = new StateListDrawable();
            sd.addState(new int[]{android.R.attr.state_pressed}, new RoundCornorDrawable(colorStroke, strokeWidth, colorPressed, height / 2));
            sd.addState(new int[]{-android.R.attr.state_enabled}, new RoundCornorDrawable(colorStroke, strokeWidth, colorDisabled, height / 2));
            sd.addState(StateSet.WILD_CARD, new RoundCornorDrawable(colorStroke, strokeWidth, colorNormal, height / 2));
            return sd;
        }
        return null;
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }
}
