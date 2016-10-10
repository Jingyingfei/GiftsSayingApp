package com.example.dllo.giftssayingapp.homepackage.selection;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class HomeImageThreeActivity extends BaseActivity {


    private TabLayout tb_image_three;
    private ViewPager vp_image_three;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_image_three;
    }

    @Override
    protected void initView() {
        tb_image_three = bindView(R.id.tb_image_three);
        vp_image_three = bindView(R.id.vp_image_three);
    }

    @Override
    protected void initData() {
//        tb_image_three.getTabAt(0).setText("今日礼物");
//        tb_image_three.getTabAt(1).setText("往期礼物");
    }
}
