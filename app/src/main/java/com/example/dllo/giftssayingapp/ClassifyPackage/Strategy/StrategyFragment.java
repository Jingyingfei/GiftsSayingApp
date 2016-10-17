package com.example.dllo.giftssayingapp.classifypackage.Strategy;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.beanpackage.RaiderHeaderBean;
import com.example.dllo.giftssayingapp.beanpackage.StrategyBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class StrategyFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private List<String> group_list = new ArrayList<String>();
    private List<List<String>> item_list = new ArrayList<List<String>>();

    private List<List<String>> item_list2 = new ArrayList<List<String>>();
    private List<String> group_list2 = new ArrayList<>();

    private RecyclerView raiderRv;
    ArrayList<RaiderHeaderBean> beanArrayList = new ArrayList<>();


    @Override
    protected int setLayout() {
        return R.layout.select_raider_layout;
    }

    @Override
    protected void initView() {
        expandableListView = bindView(R.id.expendlist);
        View viewHeader = LayoutInflater.from(context).inflate(R.layout.raider_header_layout, null);
        raiderRv = (RecyclerView) viewHeader.findViewById(R.id.rv_raider_header);
        expandableListView.addHeaderView(viewHeader);
    }

    @Override
    protected void initData() {

        requestHeader();
        RaiderGet();
    }


    public void requestHeader() {

        StringRequest stringRequest = new StringRequest(URLValues.STRATEGY_PART, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RaiderHeaderBean bean = gson.fromJson(response, RaiderHeaderBean.class);
                beanArrayList.add(bean);

                RaiderHeaderAdpter adpter = new RaiderHeaderAdpter(context);
                adpter.setRaiderHeaderBean(beanArrayList);
                raiderRv.setAdapter(adpter);

                GridLayoutManager manager = new GridLayoutManager(context, 3, LinearLayoutManager.HORIZONTAL, false);
                raiderRv.setLayoutManager(manager);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(stringRequest);
    }

    public void RaiderGet() {
        StringRequest stringRequest = new StringRequest(URLValues.STRATEGY_COLUMN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StrategyBean bean = gson.fromJson(response, StrategyBean.class);

                for (int i = 0; i < bean.getData().getChannel_groups().size(); i++) {
                    group_list.add(bean.getData().getChannel_groups().get(i).getName());
                    item_list.add(group_list);
                }
                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < 6; i++) {
                        group_list2.add(bean.getData().getChannel_groups().get(j).getChannels().get(i).getCover_image_url());
                        item_list2.add(group_list2);

                    }
                }

                adapterData();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(stringRequest);
    }

    public void adapterData() {

        StrategyAdapter adapter = new StrategyAdapter(context);
        adapter.setGroup_list(group_list);
        adapter.setItem_list(item_list);
        adapter.setItem_list2(item_list2);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);

        for (int i = 0; i < group_list.size(); i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }
}
