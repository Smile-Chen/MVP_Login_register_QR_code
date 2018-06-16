package com.example.hp.mn2.mvp.view.iview;

import com.example.hp.mn2.base.IView;

/**
 * Created by hp on 2018/6/15.
 */

public interface LoginIView extends IView{

    void getSuccessView(String data);
    void getErrorView(String error);

}
