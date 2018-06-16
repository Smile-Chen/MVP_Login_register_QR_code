package com.example.hp.mn2.mvp.model;


import com.example.hp.mn2.http.HttpConfig;
import com.example.hp.mn2.http.HttpUtils;
import com.google.gson.Gson;

/**
 * Created by hp on 2018/6/15.
 */

public class LoginModel {

    public void logins(String phone, String password, final IncanCallChe incanCallChe){
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
         String url= HttpConfig.login_url+"?mobile="+phone+"&password="+password;
          httpUtils.get(url, new HttpUtils.OKHttpCallChe() {
              @Override
              public void getSuccess(String json) {
                  Gson gson = new Gson();
                  LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                  String code = loginBean.getCode();
                  String msg = loginBean.getMsg();
                  if ("0".equalsIgnoreCase(code)){
                      incanCallChe.getDataSuccess(msg);
                  }else {
                      incanCallChe.getDataError(msg);
                  }
              }
              @Override
              public void getError(Exception error) {

              }
          });

    }

    //创建一个接口
    public interface IncanCallChe{
        void getDataSuccess(String json);
        void getDataError(String error);
    }

}
