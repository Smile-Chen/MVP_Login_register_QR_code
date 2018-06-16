package com.example.hp.mn2.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.mn2.R;
import com.example.hp.mn2.base.BaseActivity;
import com.example.hp.mn2.mvp.presenter.LoginPresenter;
import com.example.hp.mn2.mvp.view.iview.LoginIView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.util.Map;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

public class MainActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener,LoginIView {


    private EditText etPhone,etPwd;
    private Button btnLogin,btnReg;
    private TextView tvThere;
    private ImageView ivImage;
    private CheckBox ckCheck;

    @Override
    protected LoginPresenter probilPresenter() {
        return new LoginPresenter(this);
    }
    @Override
    protected int probilId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void initView() {
        etPhone = findViewById(R.id.et_phone);
        etPwd = findViewById(R.id.et_pwd);
        ivImage = findViewById(R.id.iv_image);
        ckCheck = findViewById(R.id.cb_check);
        btnLogin = findViewById(R.id.btn_login);
        btnReg = findViewById(R.id.btn_reg);
        tvThere = findViewById(R.id.tv_there);

    }
    @Override
    protected void initListenter() {
        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
tvThere.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        UMShareAPI umShareAPI= UMShareAPI.get(MainActivity.this);
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
    }
});

    }

    @Override
    public void onClick(View v) {
        presenter.logins(etPhone.getText().toString(),etPwd.getText().toString());
        Log.e("手机号："+etPhone.getText().toString(),"密码："+etPwd.getText().toString());
        if (!TextUtils.isEmpty(etPhone.getText().toString())){
            // 根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            Bitmap qrCodeBitmap = EncodingUtils.createQRCode(etPhone.getText().toString(), 200, 200, ckCheck.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) : null);
                 ivImage.setImageBitmap(qrCodeBitmap);
        }else {
            Toast.makeText(MainActivity.this, "无内容，生成失败！！！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void getSuccessView(String data) {
        Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getErrorView(String error) {
        Toast.makeText(MainActivity.this,"登陆失败！！"+error,Toast.LENGTH_SHORT).show();
    }

    //第三方登录
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(mContext, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
