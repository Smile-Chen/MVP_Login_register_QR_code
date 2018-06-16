package com.example.hp.mn2.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.mn2.R;
import com.example.hp.mn2.base.BaseActivity;
import com.example.hp.mn2.mvp.presenter.RegPresenter;
import com.example.hp.mn2.mvp.view.iview.LoginIView;

/**
 * Created by hp on 2018/6/15.
 */

public class RegActivity extends BaseActivity<RegPresenter> implements View.OnClickListener,LoginIView {
    private EditText etRegPhone,etRegPwd;
    private Button btnRegReg;

    @Override
    protected RegPresenter probilPresenter() {
        return new RegPresenter(this);
    }

    @Override
    protected int probilId() {
        return R.layout.reg_main;
    }
    @Override
    protected void initData() {
    }

    @Override
    protected void initListenter() {
         btnRegReg.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        etRegPhone = findViewById(R.id.et_reg_phone);
        etRegPwd = findViewById(R.id.et_reg_pwd);
        btnRegReg = findViewById(R.id.btn_reg_reg);

    }


    @Override
    public void onClick(View v) {
        presenter.logins(etRegPhone.getText().toString(),etRegPwd.getText().toString());
    }


    @Override
    public Context context() {
        return context();
    }
    @Override
    public void getSuccessView(String data) {
        Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void getErrorView(String error) {
        Toast.makeText(RegActivity.this,"注册失败"+error,Toast.LENGTH_SHORT).show();
    }


}
