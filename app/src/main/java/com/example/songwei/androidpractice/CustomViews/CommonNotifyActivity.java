package com.example.songwei.androidpractice.CustomViews;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by ${zuowp291} on 2016/1/22.
 */
public class CommonNotifyActivity extends CommonDialogActivity {
    private static final String TAG = "CommonDialogActivity";
    private PendingIntent mForwardIntent;


    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_MSG_TITLE = "extra_msg_title";
    public static final String EXTRA_MSG_CONTENT = "extra_msg_content";
    public static final String EXTRA_BUTTON = "extra_button";
    public static final String EXTRA_PENDING_INTENT = "extra_pending_intent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = null;
        Intent intent = getIntent();
        if (intent != null) {
            b = intent.getExtras();
        }
        init(b);
    }

    protected void init(Bundle bundle) {
        mTitleTxt.setText(bundle.getString(EXTRA_TITLE, "提醒"));
        addContent(bundle.getString(EXTRA_MSG_TITLE, ""), bundle.getStringArray(EXTRA_MSG_CONTENT));
        mBottomBtn.setText(bundle.getString(EXTRA_BUTTON, "我知道了"));
        mForwardIntent = bundle.getParcelable(EXTRA_PENDING_INTENT);
        mBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick");
                onButtonClicked();
            }
        });
    }

    protected void onButtonClicked() {
        if (mForwardIntent != null) {
            try {
                mForwardIntent.send();
            } catch (PendingIntent.CanceledException e) {
            }
        }
        CommonNotifyActivity.this.finish();
    }

    public static void startActvity(Context context, String title, String msgTitle, String[] msgContent, String btnText) {
        Intent intent = new Intent(context, CommonNotifyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(CommonNotifyActivity.EXTRA_TITLE, title);
        intent.putExtra(CommonNotifyActivity.EXTRA_MSG_TITLE, msgTitle);
        intent.putExtra(CommonNotifyActivity.EXTRA_MSG_CONTENT, msgContent);
        intent.putExtra(CommonNotifyActivity.EXTRA_BUTTON, btnText);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
