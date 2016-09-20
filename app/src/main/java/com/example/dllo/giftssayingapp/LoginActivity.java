package com.example.dllo.giftssayingapp;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class LoginActivity extends BaseActivity {


    private ImageButton iBtn_wrong;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        iBtn_wrong = bindView(R.id.iBtn_wrong);
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

    }
}
