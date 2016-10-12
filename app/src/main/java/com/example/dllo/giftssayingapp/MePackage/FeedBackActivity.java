package com.example.dllo.giftssayingapp.mepackage;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {


    private ImageView iv_holo_dark;

    @Override
    protected int setLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView() {
        iv_holo_dark = bindView(R.id.iv_holo_dark);
        iv_holo_dark.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    //返回上一页的点击事件

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(FeedBackActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}
