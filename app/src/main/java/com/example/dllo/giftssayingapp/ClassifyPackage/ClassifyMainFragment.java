package com.example.dllo.giftssayingapp.classifypackage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class ClassifyMainFragment extends BaseFragment {

    private TabLayout tb_calssify;
    private ViewPager vp_classify;

    @Override
    protected int setLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        tb_calssify = bindView(R.id.tb_classify);
        vp_classify = bindView(R.id.vp_classify);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RaidersFragment());
        fragments.add(new SingleFragment());

        ClassifyMainAdapter adapter = new ClassifyMainAdapter(getChildFragmentManager(), fragments);
        vp_classify.setAdapter(adapter);
        tb_calssify.setupWithViewPager(vp_classify);

        tb_calssify.getTabAt(0).setText("攻略");
        tb_calssify.getTabAt(1).setText("单品");
    }
}
