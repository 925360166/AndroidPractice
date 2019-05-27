package com.example.songwei.androidpractice.CustomViews;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by ${zuowp291} on 2015/10/23.
 */
public class RoundCornorDrawable extends Drawable {
    private static final String TAG = "RoundRectDrawable";
    private final Paint mFillPaint;
    private final Paint mFramePaint;
    private final int colorFill;
    private final int colorStroke;
    private final float strokeWidth;


    public RoundCornorDrawable(int strokeColor, int strokeWidth, int fillColor, int radius) {
        this.strokeWidth = strokeWidth;
        colorFill = fillColor;
        colorStroke = strokeColor;

        mFramePaint = new Paint();
        mFramePaint.setAntiAlias(true);
        mFramePaint.setStrokeWidth(strokeWidth);
        mFramePaint.setColor(colorStroke);
        mFramePaint.setStyle(Paint.Style.STROKE);

        mFillPaint = new Paint();
        mFillPaint.setAntiAlias(true);
        mFillPaint.setColor(colorFill);
        mFillPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect r = getBounds();
        //先画左半圆
        drawSemiArc(canvas, true, 0, 0, r.height(), r.height());
        //再画中间的矩形
        drawRectangle(canvas, ((0.0f + r.height()) / 2f), 0, r.height(), r.width() - r.height() + 0.5f);
        //再画右半圆
        drawSemiArc(canvas, false, r.width() - r.height(), 0, r.height(), r.height());
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    private void drawRectangle(Canvas canvas, float left, float top, float height, float width) {
        RectF oval = new RectF(left, top, left + width, top + height);
        canvas.drawRect(oval, mFillPaint);

        //draw line
        canvas.drawLine(left, top + strokeWidth / 2, left + width, top + strokeWidth / 2, mFramePaint);
        canvas.drawLine(left, top + height - strokeWidth / 2, left + width, top + height - strokeWidth / 2, mFramePaint);
    }

    private void drawSemiArc(Canvas canvas, boolean leftCircle, float left, float top, float height, float width) {
        RectF oval = new RectF(left, top, left + width, top + height);
        if (leftCircle) {
            canvas.drawArc(oval, 90, 180, true, mFillPaint);
            RectF ovalL = new RectF(left + strokeWidth / 2, top + strokeWidth / 2, left + width, top + height - strokeWidth / 2);
            canvas.drawArc(ovalL, 90, 180, false, mFramePaint);
        } else {
            canvas.drawArc(oval, -90, 180, true, mFillPaint);
            RectF ovalR = new RectF(left, top + strokeWidth / 2, left + width - strokeWidth / 2, top + height - strokeWidth / 2);
            canvas.drawArc(ovalR, -90, 180, false, mFramePaint);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
