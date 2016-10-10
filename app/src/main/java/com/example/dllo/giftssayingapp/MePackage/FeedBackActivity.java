package com.example.dllo.giftssayingapp.mepackage;

import android.content.Intent;
import android.widget.ImageView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class FeedBackActivity extends BaseActivity {


    private ImageView iv_holo_dark;

    @Override
    protected int setLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView() {
        iv_holo_dark = bindView(R.id.iv_holo_dark);
    }

    @Override
    protected void initData() {
        initJump();
    }

    //返回上一页的点击事件
    public void initJump() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
