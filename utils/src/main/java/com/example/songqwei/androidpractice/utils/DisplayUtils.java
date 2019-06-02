package com.example.songqwei.androidpractice.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * dp、sp转换为px的工具
 *
 * 《Android群英传》第6章：Android绘图机制与处理技巧
 */
public class DisplayUtils {

    /**
     * 将px值转换为dip或dp的值，保证尺寸大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * dp2px，使用系统提供的TypedValue类帮助进行转换
     *
     * @param context
     * @param dp
     * @return
     */
    protected int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    /**
     * sp2px，使用系统提供的TypedValue类帮助进行转换
     *
     * @param context
     * @param sp
     * @return
     */
    protected int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }
}
