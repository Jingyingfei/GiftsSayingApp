package com.example.dllo.giftssayingapp.homePackage;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basePackage.BaseFragment;

/**
 * Created by dllo on 16/9/19.
 */
public class HomeFragment extends BaseFragment {

    private TabLayout tb_hotspot;
    private ViewPager vp_hotspot;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tb_hotspot = bindView(R.id.tb_hotspot);
        vp_hotspot = bindView(R.id.vp_hotspot);
    }

    @Override
    protected void initData() {
//        HotSpotAdapter adapter = new HotSpotAdapter()
    }
}
