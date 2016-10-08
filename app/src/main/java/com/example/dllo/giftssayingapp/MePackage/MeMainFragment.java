package com.example.dllo.giftssayingapp.mepackage;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;

/**
 * Created by dllo on 16/9/19.
 */
public class MeMainFragment extends BaseFragment implements View.OnClickListener {


    private ImageView setting;
    private ImageView message;
    private ImageView scan;

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        setting = bindView(R.id.iv_me_setting);
        message = bindView(R.id.iv_me_message);
        scan = bindView(R.id.iv_me_scan);
        setting.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_me_setting:
                Intent intent = new Intent(context, SettingActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
