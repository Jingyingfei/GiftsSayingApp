package com.example.dllo.giftssayingapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class StartBaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(setLayout());
        initView();
        initData();
    }
    //设置布局
    protected abstract int setLayout();

    //初始化View 执行FindViewbyId等操作
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();
}
