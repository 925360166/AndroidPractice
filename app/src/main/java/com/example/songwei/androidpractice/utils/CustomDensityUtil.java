package com.example.songwei.androidpractice.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Author: SongWei
 * Date:   2019/10/6
 * Usage:
 */
public class CustomDensityUtil {

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    /**
     * 下面假设设计图宽度是360dp，以宽维度来适配
     * 在Activity#onCreate方法中调用下
     *
     * @param activity
     * @param application
     */
    public static void setCustomDensity(Activity activity, Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if(sNoncompatDensity == 0){
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale > 0){
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;

                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = (float)appDisplayMetrics.widthPixels / 360;
        final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int)(160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
