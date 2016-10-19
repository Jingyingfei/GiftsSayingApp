package com.example.dllo.giftssayingapp.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseActivity;
import com.example.dllo.giftssayingapp.classify.main.ClassifyMainFragment;
import com.example.dllo.giftssayingapp.home.main.HomeMainFragment;
import com.example.dllo.giftssayingapp.hotspot.main.HotSpotMainFragment;
import com.example.dllo.giftssayingapp.me.MeMainFragment;

public class StartMainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton home;
    private RadioButton hotspot;
    private RadioButton classify;
    private RadioButton me;


    @Override
    protected int setLayout() {

        return R.layout.activity_start_main;

    }

    @Override
    protected void initView() {
        home = bindView(R.id.rBtn_home);
        hotspot = bindView(R.id.rBtn_hotspot);
        classify = bindView(R.id.rBtn_classify);
        me = bindView(R.id.rBtn_me);



        home.setOnClickListener(this);
        hotspot.setOnClickListener(this);
        classify.setOnClickListener(this);
        me.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, new HomeMainFragment());
        home.setChecked(true);
        transaction.commit();



    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.rBtn_home:
                transaction.replace(R.id.frameLayout, new HomeMainFragment());
                break;
            case R.id.rBtn_hotspot:
                transaction.replace(R.id.frameLayout, new HotSpotMainFragment());
                break;
            case R.id.rBtn_classify:
                transaction.replace(R.id.frameLayout, new ClassifyMainFragment());
                break;
            case R.id.rBtn_me:
                transaction.replace(R.id.frameLayout, new MeMainFragment());
                break;
        }
        transaction.commit();

    }
}
