package com.example.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.mvp.IMvpView;
import com.example.mvp.MvpController;

/**
 * Author: SongWei
 * Date:   2019/10/4
 * Usage:
 */
public class LifeCircleMvpFragment extends Fragment implements IMvpView {
    
    private MvpController MvpController;

    @Override
    public MvpController getMvpController() {
        if (this.MvpController == null) {
            this.MvpController = new MvpController();
        }
        return this.MvpController;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onCreate(savedInstanceState,null,bundle);
            MvpController.onActivityCreated(savedInstanceState,null,bundle);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onActivityCreated(savedInstanceState,null,bundle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onViewDestroy();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onStop();
        }
    }

    @Override
    public void onDestroy() {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpController MvpController = this.getMvpController();
        if (MvpController != null) {
            MvpController.onActivityResult(requestCode,resultCode,data);
        }
    }
}
