package com.example.dllo.giftssayingapp.hotSpotPackage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basePackage.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 * http://api.liwushuo.com/v2/items?gender=1&generation=4&limit=50&oddset=0
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
        tb_hotspot = bindView(R.id.tb_hotspot, getView());
        vp_hotspot = bindView(R.id.vp_hotspot, getView());
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new RecommendFragment());
        arrayList.add(new TopFragment());
        arrayList.add(new OriginalityFragment());
        arrayList.add(new NewStarFragment());
        //适配器
        HotSpotAdapter adapter = new HotSpotAdapter(getChildFragmentManager(), arrayList);
        vp_hotspot.setAdapter(adapter);
        tb_hotspot.setupWithViewPager(vp_hotspot);

        tb_hotspot.getTabAt(0).setText("每日推荐");
        tb_hotspot.getTabAt(1).setText("TOP100");
        tb_hotspot.getTabAt(2).setText("独立原创榜");
        tb_hotspot.getTabAt(3).setText("新星榜");

    }
}
