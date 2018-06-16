package com.example.hp.mn2.mvp.presenter;

import com.example.hp.mn2.base.BasePresenter;
import com.example.hp.mn2.mvp.model.LoginModel;
import com.example.hp.mn2.mvp.view.activity.MainActivity;
import com.example.hp.mn2.mvp.view.iview.LoginIView;

/**
 * Created by hp on 2018/6/15.
 */

public class LoginPresenter extends BasePresenter<LoginIView> {
    private LoginModel loginModel;

    public LoginPresenter(LoginIView iview) {
      super(iview);
    }
    public void logins(String phone,String password){
        loginModel.logins(phone, password, new LoginModel.IncanCallChe() {
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


    @Override
    protected void OnModel() {
        loginModel=new LoginModel();
    }
}
