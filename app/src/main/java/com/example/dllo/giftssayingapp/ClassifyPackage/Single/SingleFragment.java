package com.example.dllo.giftssayingapp.classifypackage.Single;

import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;

/**
 * Cre by dllo on 16/9/20.
 */
public class SingleFragment extends BaseFragment {

    private FrameLayout left_single;
    private FrameLayout right_single;

    @Override
    protected int setLayout() {
        return R.layout.fragment_single;
    }

    @Override
    protected void initView() {
        left_single = bindView(R.id.fl_single_left);
        right_single = bindView(R.id.fl_single_right);

    }

    @Override
    protected void initData() {
        SingleLeftFragment singleLeftFragment = new SingleLeftFragment();
        SingleRightFragment singleRightFragment = new SingleRightFragment();
        FragmentManager manager = getChildFragmentManager();
        manager.beginTransaction().add(R.id.fl_single_left, singleLeftFragment)
                .add(R.id.fl_single_right, singleRightFragment).commit();


    }
}
