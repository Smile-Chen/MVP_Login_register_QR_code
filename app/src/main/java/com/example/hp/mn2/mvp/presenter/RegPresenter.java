package com.example.hp.mn2.mvp.presenter;

import com.example.hp.mn2.base.BasePresenter;
import com.example.hp.mn2.mvp.model.LoginModel;
import com.example.hp.mn2.mvp.model.RegModel;
import com.example.hp.mn2.mvp.view.iview.LoginIView;

/**
 * Created by hp on 2018/6/15.
 */

public class RegPresenter extends BasePresenter<LoginIView>{
       private RegModel regModel;

    public RegPresenter(LoginIView view) {
        super(view);
    }

    @Override
    protected void OnModel() {
        regModel = new RegModel();
    }
    public void logins(String phone,String password){
       regModel.logins(phone, password, new LoginModel.IncanCallChe() {
            @Override
            public void getDataSuccess(String json) {
                if (view!=null){
                    view.getSuccessView(json);
                }
            }

            @Override
            public void getDataError(String error) {
                if (view!=null){
                    view.getErrorView(error);
                }
            }
        });

    }

}
