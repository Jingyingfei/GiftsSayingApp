package com.example.dllo.giftssayingapp.hotSpotPackage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basePackage.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class HotSpotFragment extends BaseFragment {
    private TabLayout tb_hotspot;
    private ViewPager vp_hotspot;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hotspot;
    }

    @Override
    protected void initView() {
        tb_hotspot = bindView(R.id.tb_hotspot);
        vp_hotspot = bindView(R.id.vp_hotspot);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new RecommendFragment());
        arrayList.add(new TopFragment());
        arrayList.add(new OriginalityFragment());
        arrayList.add(new NewStarFragment());
        //适配器
        HotSpotAdapter adapter = new HotSpotAdapter(getActivity().getSupportFragmentManager(), arrayList);
        vp_hotspot.setAdapter(adapter);
        tb_hotspot.setupWithViewPager(vp_hotspot);
    }
}
