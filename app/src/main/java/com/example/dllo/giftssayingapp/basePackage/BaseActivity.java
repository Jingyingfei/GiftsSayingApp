package com.example.dllo.giftssayingapp.basepackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    //简化findViewById的操作
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }
}
