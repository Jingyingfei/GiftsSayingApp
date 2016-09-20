package com.example.dllo.giftssayingapp;

import android.content.Intent;
import android.os.Handler;

import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class WelcomActivity extends BaseActivity {
    private int time = 3;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                time--;
                if (0 == time){
                    Intent intent = new Intent(WelcomActivity.this, StartMainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        handler.postDelayed(runnable,1000);
    }
}
