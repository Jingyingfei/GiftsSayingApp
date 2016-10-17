package com.example.dllo.giftssayingapp.mainpackage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private ImageButton iBtn_wrong;
    private Button btn_code;
    private boolean isVisible = false;
    private LinearLayout login_linear_Verification_code;
    private LinearLayout login_linear_The_login;
    private EditText et_hint;
    private ImageView sina_weibo;
    private ImageView wechat;
    private ImageView tencent_qq;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        iBtn_wrong = bindView(R.id.iBtn_wrong);
        btn_code = bindView(R.id.btn_code);
        login_linear_Verification_code = bindView(R.id.login_linear_Verification_code);
        login_linear_The_login = bindView(R.id.login_linear_The_login);
        login_linear_Verification_code.setVisibility(View.GONE);
        et_hint = bindView(R.id.et_hint);
        sina_weibo = bindView(R.id.iv_sina_weibo);
        wechat = bindView(R.id.iv_wechat);
        tencent_qq = bindView(R.id.iv_tencent_qq);

        sina_weibo.setOnClickListener(this);
        wechat.setOnClickListener(this);
        tencent_qq.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        iBtn_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,StartMainActivity.class);
                startActivity(intent);

            }
        });
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isVisible) {
                    isVisible = false;
                    login_linear_Verification_code.setVisibility(View.VISIBLE);
                    login_linear_The_login.setVisibility(View.GONE);
//                    et_hint .setText("使用密码登录");
                } else {
                    isVisible = true;
                    login_linear_The_login.setVisibility(View.VISIBLE);
                    login_linear_Verification_code.setVisibility(View.GONE);
//                    btn_code.setText("使用验证码登录");
                }

            }
        });


    }

    @Override
    public void onClick(View view) {
        PlatformActionListener paListener = null;
        switch (view.getId()){
            case R.id.iv_sina_weibo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener);
                //authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_tencent_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(paListener);
                //authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                break;
        }
    }
}
