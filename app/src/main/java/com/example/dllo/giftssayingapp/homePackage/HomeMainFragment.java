package com.example.dllo.giftssayingapp.homepackage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class HomeMainFragment extends BaseFragment {
    private String strNet = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
    private TabLayout tb_homeTab;
    private ViewPager vp_homeTab;
    private ArrayList<String> strings;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tb_homeTab = bindView(R.id.tb_home_top);
        vp_homeTab = bindView(R.id.vp_home_top);
    }

    @Override
    protected void initData() {


        //创建网络请求
        StringRequest request = new StringRequest(strNet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //解析
                strings = new ArrayList<>();
                Gson gson = new Gson();
                HomeTabBean bean = gson.fromJson(response, HomeTabBean.class);
                for (int i = 0; i < bean.getData().getChannels().size(); i++) {
                    strings.add(bean.getData().getChannels().get(i).getName());
                }
                for (int i = 0; i < strings.size(); i++) {
                    tb_homeTab.addTab(tb_homeTab.newTab().setText(strings.get(i)));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();

            }
        });

        //将请求放到请求队列中
        VolleySingleton.getInstance().addRequest(request);

        ArrayList<Fragment> fragments = new ArrayList<>();

        HomeMainAdapter adapter = new HomeMainAdapter(getChildFragmentManager(),fragments, strings);

        vp_homeTab.setAdapter(adapter);
        tb_homeTab.setupWithViewPager(vp_homeTab);
        tb_homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);

    }
}