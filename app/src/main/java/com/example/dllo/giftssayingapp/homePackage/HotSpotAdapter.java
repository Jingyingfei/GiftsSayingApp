package com.example.dllo.giftssayingapp.homePackage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class HotSpotAdapter extends FragmentPagerAdapter {
    private ArrayList<HotSpotBean> arrayList;
    private Context context;

    public HotSpotAdapter(FragmentManager fm, ArrayList<HotSpotBean> arrayList) {
        super(fm);
        this.arrayList = arrayList;
    }

    public HotSpotAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
