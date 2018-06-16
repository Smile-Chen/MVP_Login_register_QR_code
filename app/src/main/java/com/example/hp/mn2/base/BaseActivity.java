package com.example.hp.mn2.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hp on 2018/6/15.
 * 抽象类
 *
 */

public abstract class BaseActivity<P extends BasePresenter>extends AppCompatActivity {
              protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(probilId());//获取布局ID

               //创建方法
               initView();//布局
               initListenter();//点击
               initData();//数据
               presenter=probilPresenter();//内容提供者

    }

    protected abstract P probilPresenter();
    protected abstract int probilId();
    protected abstract void initData();
    protected abstract void initListenter();
    protected abstract void initView();

    //解决内存泄漏
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();

    }
}
