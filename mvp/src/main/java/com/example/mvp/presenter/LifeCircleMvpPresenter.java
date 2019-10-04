package com.example.mvp.presenter;

import com.example.mvp.ILifeCircle;
import com.example.mvp.IMvpView;
import com.example.mvp.MvpController;

import java.lang.ref.WeakReference;

public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    protected WeakReference<T> weakReference;

    protected LifeCircleMvpPresenter(){
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView){
        super();
        attachView(iMvpView);
        MvpController mvpController = iMvpView.getMvpController();
        mvpController.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if(weakReference == null){
            weakReference = new WeakReference(iMvpView);
        }else{
            T view = weakReference.get();
            if(view != iMvpView){
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        weakReference = null;
    }

    protected T getView(){
        T view = weakReference != null ? weakReference.get() : null;
        if(view == null){
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
