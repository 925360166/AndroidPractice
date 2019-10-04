package com.example.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mvp.IMvpView;
import com.example.mvp.MvpController;

/**
 * Author: SongWei
 * Date:   2019/10/4
 * Usage:
 */
public class LifeCircleMvpActivity extends AppCompatActivity implements IMvpView {

    private MvpController mvpController;

    @Override
    public MvpController getMvpController() {
        if(this.mvpController == null){
            this.mvpController = new MvpController();
        }
        return this.mvpController;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        if(intent == null){
            intent = new Intent();
        }
        MvpController mvpController = this.getMvpController();
        if(mvpController != null){
            mvpController.onCreate(savedInstanceState, intent, null);
            mvpController.onActivityCreated(savedInstanceState, intent, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onNewIntent(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onActivityResult(requestCode,resultCode,data);
        }
    }
}
