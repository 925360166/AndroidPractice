package com.example.songwei.androidpractice.CustomViews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.songwei.androidpractice.AppEnv;
import com.example.songwei.androidpractice.CustomViews.views.ButtonSelectorActivity;
import com.example.songwei.androidpractice.CustomViews.views.CommonNotifyActivity;
import com.example.songwei.androidpractice.CustomViews.views.Shape1Activity;
import com.example.songwei.androidpractice.R;

public class CustomViewsActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "CustomViewsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_views);
        initView();
    }

    private void initView() {
        findViewById(R.id.cv_circle_view).setVisibility(View.GONE);
        findViewById(R.id.cv_circle_wave_view).setVisibility(View.GONE);
        findViewById(R.id.cv_step_arc_view).setVisibility(View.GONE);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                CommonNotifyActivity.startActvity(AppEnv.getContext(), "标题", "信息的标题", new String[]{"内容1", "内容2", "内容3"}, "按钮文字");
                break;
            case R.id.bt_2:
                startActivity(new Intent(this, Shape1Activity.class));
                break;
                case R.id.bt_3:
                startActivity(new Intent(this, ButtonSelectorActivity.class));
                break;
            default:
                break;
        }
    }
}
