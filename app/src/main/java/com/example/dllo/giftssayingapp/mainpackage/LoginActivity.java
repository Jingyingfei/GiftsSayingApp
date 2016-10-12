package com.example.dllo.giftssayingapp.mainpackage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class LoginActivity extends BaseActivity {


    private ImageButton iBtn_wrong;
    private Button btn_code;
    private boolean isVisible = false;
    private LinearLayout login_linear_Verification_code;
    private LinearLayout login_linear_The_login;
    private EditText et_hint;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        iBtn_wrong = bindView(R.id.iBtn_wrong);
        btn_code = bindView(R.id.btn_code);
        login_linear_Verification_code = bindView(R.id.login_linear_Verification_code);
        login_linear_The_login = bindView(R.id.login_linear_The_login);
        et_hint = bindView(R.id.et_hint);
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
                    et_hint .setText("使用密码登录");
                } else {
                    login_linear_The_login.setVisibility(View.VISIBLE);
                    login_linear_Verification_code.setVisibility(View.GONE);
                    btn_code.setText("使用验证码登录");
                    isVisible = true;
                }

            }
        });


    }
}
