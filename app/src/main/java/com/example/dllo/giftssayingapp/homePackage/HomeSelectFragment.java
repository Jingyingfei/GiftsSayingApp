package com.example.dllo.giftssayingapp.homepackage;

import android.support.v4.view.ViewPager;
import android.widget.ListView;
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
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1
 */
public class HomeSelectFragment extends BaseFragment {
    private String strNet = "http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1";
    private String strNetImage = "http://api.liwushuo.com/v2/banners";
    private ListView homeSelect;
    private ViewPager homeImage;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_select;
    }

    @Override
    protected void initView() {
        homeSelect = bindView(R.id.ll_home_select);
        homeImage = bindView(R.id.vp_home_lunbo);
    }

    @Override
    protected void initData() {
        requestImageData();
        requestData();
    }

    public void requestData(){
        //创建网络请求
        StringRequest request = new StringRequest(strNet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 解析
                ArrayList<HomeSelectBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                HomeSelectBean bean = gson.fromJson(response, HomeSelectBean.class);
                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    bean.getData().getItems().get(i).getCover_image_url();
                    bean.getData().getItems().get(i).getTitle();
                    arrayList.add(bean);
                }
                HomeSelectAdapter adapter = new HomeSelectAdapter(getContext());
                adapter.setArrayList(arrayList);
                homeSelect.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);

    }
    public void requestImageData(){
        //创建轮播图的网络请求
        StringRequest request = new StringRequest(strNetImage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Gson gson = new Gson();
                HomeImageBean bean = gson.fromJson(response, HomeImageBean.class);
                for (int i = 0; i < bean.getData().getBanners().get(i).getImage_url().length(); i++) {
                    bean.getData().getBanners().get(i).getImage_url();
                }
                HomeImageAdapter adapter = new HomeImageAdapter(getContext());
                adapter.setBean(bean);
                homeImage.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }
}
