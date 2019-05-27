
package com.example.songqwei.androidpractice.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class CommonUIUtils {

    private static final String TAG = CommonUIUtils.class.getName();

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String getTextFromAttrs(Context context, AttributeSet attrs) {
        return getTextFromAttrs(context, attrs, "text");
    }

    public static String getTextFromAttrs(Context context, AttributeSet attrs, String name) {
        String value = getValueFromAttrs(context, attrs, name);
        if (!TextUtils.isEmpty(value)) {
            if (value.startsWith("@")) {
                int res = CommonUIUtils.str2Int(value.substring(1), -1);
                if (res != -1) {
                    try {
                        value = context.getString(res);
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return value;
    }

    public static int getDimenFromAttrs(Context context, AttributeSet attrs, String name) {
        String value = getValueFromAttrs(context, attrs, name);
        if (TextUtils.isEmpty(value)) {
            return -1;
        }
        if (value.startsWith("@")) {
            int res = CommonUIUtils.str2Int(value.substring(1), -1);
            if (res != -1) {
                try {
                    return context.getResources().getDimensionPixelSize(res);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
            return -1;
        } else {
            return Integer.parseInt(value);
        }
    }


    public static Integer getColorFromAttrs(Context context, AttributeSet attrs, String name) {
        String value = getValueFromAttrs(context, attrs, name);
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        if (value.startsWith("@")) {
            int res = CommonUIUtils.str2Int(value.substring(1), -1);
            if (res != -1) {
                try {
                    return context.getResources().getColor(res);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

    public static Drawable getDrawableFromAttrs(Context context, AttributeSet attrs, String key) {
        String value = getValueFromAttrs(context, attrs, key);
        if (!TextUtils.isEmpty(value)) {
            if (value.startsWith("@")) {
                int res = CommonUIUtils.str2Int(value.substring(1), -1);
                if (res != -1) {
                    try {
                        Drawable drawable = context.getResources().getDrawable(res);
                        return drawable;
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static String getValueFromAttrs(Context context, AttributeSet attrs, String key) {
        if (attrs != null) {
            int aCount = attrs.getAttributeCount();
            for (int i = 0; i < aCount; i++) {
                String name = attrs.getAttributeName(i);
                if (!TextUtils.isEmpty(name) && name.equals(key)) {
                    return attrs.getAttributeValue(i);
                }
            }
        }
        return null;
    }

    /**
     * 字符串转换成int
     *
     * @param str
     * @return
     */
    public static int str2Int(String str, int defValue) {
        int ret = defValue;
        try {
            if (!TextUtils.isEmpty(str)) {
                ret = Integer.parseInt(str.trim());
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * 字符串转换成int
     *
     * @param str
     * @return
     */
    public static int str2Int(String str, int defValue, int radix) {
        int ret = defValue;
        try {
            if (!TextUtils.isEmpty(str)) {
                ret = Integer.parseInt(str.trim(), radix);
            }
        } catch (Exception ex) {
            Log.e(TAG, "str2Int radix error", ex);
        }
        return ret;
    }

    // FIXME
    // setEnable(false)时需要改变子view的背景，android:duplicateParentState不管用，暂时用这种方法
    public static void setViewGroupEnabled(ViewGroup v, boolean enabled) {
        for (int i = 0; i < v.getChildCount(); i++) {
            setViewGroupEnabled(v.getChildAt(i), enabled);
        }
    }

    private static void setViewGroupEnabled(View v, boolean enabled) {
        if (v == null) {
            return;
        }
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            vg.setEnabled(enabled);
            for (int i = 0; i < vg.getChildCount(); i++) {
                setViewGroupEnabled(vg.getChildAt(i), enabled);
            }
        } else {
            v.setEnabled(enabled);
        }
    }

    public static boolean getBooleanFromTheme(Context context, int attrs, boolean defValue) {
        if (context == null) {
            return defValue;
        }
        boolean result = defValue;
        TypedArray array = null;
        try {
            array = context.getTheme().obtainStyledAttributes(new int[]{attrs});
            result = array.getBoolean(0, defValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        return result;
    }

    public static int getIntFromTheme(Context context, int attrs, int defValue) {
        if (context == null) {
            return defValue;
        }
        int result = defValue;
        TypedArray array = null;
        try {
            array = context.getTheme().obtainStyledAttributes(new int[]{attrs});
            result = array.getInt(0, defValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        return result;
    }

    public static int getIntColorFromTheme(Context context, int attrs, int defValue) {
        if (context == null) {
            return defValue;
        }
        int result = defValue;
        TypedArray array = null;
        try {
            array = context.getTheme().obtainStyledAttributes(new int[]{attrs});
            result = array.getColor(0, defValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        return result;
    }

}
