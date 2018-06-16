package com.example.hp.mn2.base;

/**
 * Created by hp on 2018/6/15.
 */

public abstract class BasePresenter<V extends IView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        OnModel();
    }

    protected abstract void OnModel();
   public void onDestroy(){
        view=null;
    }
}
