package com.example.songwei.androidpractice.CustomViews;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.songwei.androidpractice.R;

public class CommonDialogActivity extends Activity {

    private static final String TAG = "CommonBaseDlgActivity";
    protected TextView mTitleTxt;
    protected LinearLayout mLLContent;
    protected TextView mBottomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.common_dialog);
        setFinishOnTouchOutside(false);
        initView();
    }

    private void initView() {
        mTitleTxt = (TextView) findViewById(R.id.common_txt_title);
        mBottomBtn = (TextView) findViewById(R.id.common_bottom_btn);
        mLLContent = (LinearLayout) findViewById(R.id.common_ll_content);
        mTitleTxt.setText("提醒");
    }

    protected void addContent(String title, String[] subItems) {
        addContent(title, subItems, R.color.common_color_c11);
    }

    protected void addContent(String title, String[] subItems, int itemColorId) {
        TextView tv = new TextView(this);
        tv.setTextColor(this.getResources().getColor(R.color.common_color_c10));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getResources().getDimension(R.dimen.common_font_size_f32));
        tv.setTypeface(null, Typeface.BOLD);
        tv.setText(title);
        tv.setPadding(0, this.getResources().getDimensionPixelOffset(R.dimen.common_margin_h30),
                0, this.getResources().getDimensionPixelOffset(R.dimen.common_margin_h20));

        final LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        mLLContent.addView(tv, l);

        if (subItems != null) {
            for (String item : subItems) {
                Log.i(TAG, item);
                tv = new TextView(this);
                tv.setTextColor(this.getResources().getColor(itemColorId));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getResources().getDimension(R.dimen.common_font_size_f24));
                tv.setText(item);
                mLLContent.addView(tv, l);
            }
        }
    }

    protected void clearContent() {
        mLLContent.removeAllViews();
    }

    protected CommonDialogActivity setDialogTitle(CharSequence title) {
        mTitleTxt.setText(title);
        return this;
    }

    protected CommonDialogActivity setBottomBtnText(CharSequence text) {
        mBottomBtn.setText(text);
        return this;
    }

    protected CommonDialogActivity setDialogTitle(int title) {
        mTitleTxt.setText(title);
        return this;
    }

    protected CommonDialogActivity setBottomBtnText(int text) {
        mBottomBtn.setText(text);
        return this;
    }

    protected CommonDialogActivity setBottomBtnListener(View.OnClickListener l) {
        mBottomBtn.setOnClickListener(l);
        return this;
    }

    protected void addContent(int title, int[] subItems) {
        addContent(title, subItems, R.color.common_color_c11);
    }

    protected void addContent(int title, int[] subItems, int itemColorId) {
        TextView tv = new TextView(this);
        tv.setTextColor(this.getResources().getColor(R.color.common_color_c10));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getResources().getDimension(R.dimen.common_font_size_f32));
        tv.setTypeface(null, Typeface.BOLD);
        tv.setText(title);
        tv.setPadding(0, this.getResources().getDimensionPixelOffset(R.dimen.common_margin_h30),
                0, this.getResources().getDimensionPixelOffset(R.dimen.common_margin_h20));

        final LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        mLLContent.addView(tv, l);

        if (subItems != null) {
            for (int item : subItems) {
                tv = new TextView(this);
                tv.setTextColor(this.getResources().getColor(itemColorId));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getResources().getDimension(R.dimen.common_font_size_f24));
                tv.setText(item);
                mLLContent.addView(tv, l);
            }
        }
    }

}
