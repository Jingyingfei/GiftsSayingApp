package com.example.dllo.giftssayingapp.mepackage;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

/**
 * Created by dllo on 16/10/8.
 */
public class SettingActivity extends BaseActivity {

    private ImageView iv_holo_dark;
    private RelativeLayout feedback;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        iv_holo_dark = bindView(R.id.iv_holo_dark);
        feedback = bindView(R.id.ic_more_feedback);
    }

    @Override
    protected void initData() {
        jumpBackData();
        jumpFeedBackData();
    }

    //返回上一页
    public void jumpBackData() {
        iv_holo_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MeMainFragment.class);
                startActivity(intent);
            }
        });
    }

    public void jumpFeedBackData() {
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, FeedBackActivity.class);
                startActivity(intent);
            }
        });
    }


}
