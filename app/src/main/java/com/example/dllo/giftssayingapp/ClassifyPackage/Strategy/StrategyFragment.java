package com.example.dllo.giftssayingapp.classifypackage.Strategy;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class StrategyFragment extends BaseFragment {
    private String strNet = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";

    private RecyclerView raiders;
    private RecyclerView strategy_kind;
    private RecyclerView rv_strategy_style;
    private RecyclerView rv_strategy_target;

    @Override
    protected int setLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    protected void initView() {
        raiders = bindView(R.id.recycler_strategy);
        strategy_kind = bindView(R.id.rv_strategy_kind);
        rv_strategy_style = bindView(R.id.rv_strategy_kind_style);
        rv_strategy_target = bindView(R.id.rv_strategy_kind_target);

    }

    @Override
    protected void initData() {
        requestData();
        requestDataKind();

    }

    //获取recyclerview横向的图片
    public void requestData() {
        final ArrayList<StrategyBean> arrayList = new ArrayList<>();
        StringRequest request = new StringRequest(strNet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StrategyBean bean = gson.fromJson(response, StrategyBean.class);
                for (int i = 0; i < 12; i++) {
                    arrayList.add(bean);
                }
                StrategyAdapter adapter = new StrategyAdapter(context);
                adapter.setArrayList(arrayList);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
                manager.setOrientation(GridLayoutManager.HORIZONTAL);
                raiders.setLayoutManager(manager);
                raiders.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    //获取品类数据
    public void requestDataKind() {

        StringRequest request = new StringRequest(URLValues.STRATEGY_COLUMN, new Response.Listener<String>() {

            private ArrayList<StrategyColumnBean.DataBean.ChannelGroupsBean.ChannelsBean> categoryList;

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StrategyColumnBean bean = gson.
                        fromJson(response, StrategyColumnBean.class);
                List<StrategyColumnBean.DataBean.ChannelGroupsBean> arrayList = bean.getData().getChannel_groups();
                if (bean.getData().getChannel_groups().get(0).getName().equals("品类")) {
                    categoryList = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        categoryList.add(bean.getData().getChannel_groups().get(0).getChannels().get(i));
                    }
                }


                StrateryColumnAdapter adapter = new StrateryColumnAdapter(context);
                adapter.setArrayList(categoryList);
                GridLayoutManager manager = new GridLayoutManager(context, 2);
                strategy_kind.setLayoutManager(manager);
                strategy_kind.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);

    }


}
