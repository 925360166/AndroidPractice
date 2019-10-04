package com.example.songwei.androidpractice.todayinformation.base;

import com.example.mvp.IMvpView;
import com.example.mvp.base.BaseMvpPresenter;

/**
 * Author: SongWei
 * Date:   2019/10/4
 * Usage:
 */
public class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {

    public BasePresenter(T view){
        super(view);
    }

//    public void submitTask(){
//
//    }



    @Override
    protected T getEmptyView() {
        return null;
    }
}
