package com.example.songwei.androidpractice.AIDL.ContentProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.songwei.androidpractice.AIDL.Messenger.MessengerActivity;
import com.example.songwei.androidpractice.R;

public class ProviderActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Uri uri = Uri.parse("content://com.example.songwei.aidl.provider");
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ProviderActivity.class));
    }
}
