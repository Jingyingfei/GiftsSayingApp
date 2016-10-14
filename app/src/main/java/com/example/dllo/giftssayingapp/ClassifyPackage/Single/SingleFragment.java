package com.example.dllo.giftssayingapp.classifypackage.Single;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
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
 * Cre by dllo on 16/9/20.
 */
public class SingleFragment extends BaseFragment {

    private ExpandableListView expandableListView;
    private ListView lv_left_single;
    private List<SingleBean> singleArrayList = new ArrayList<>();;

    @Override
    protected int setLayout() {
        return R.layout.fragment_single;
    }

    @Override
    protected void initView() {
        expandableListView = bindView(R.id.expandList_single);
        lv_left_single = bindView(R.id.lv_left_single);

    }

    @Override
    protected void initData() {
        //设置listView的滚动条不显示
        lv_left_single.setVerticalScrollBarEnabled(false);
        expandableListView.setVerticalScrollBarEnabled(false);
        requestRightData();
        requestLeftData();

    }
    public void requestRightData(){
        StringRequest request = new StringRequest(URLValues.STRATEGY_SKU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();


                SingleBean bean = gson.fromJson(response, SingleBean.class);
                for (int i = 0; i < bean.getData().getCategories().get(i).getSubcategories().size(); i++) {
                    singleArrayList.add(bean);
                }
                SingleRightAdapter adapter = new SingleRightAdapter(context);
                adapter.setSingleArrayList(singleArrayList);
                expandableListView.setAdapter(adapter);
                //去掉ExpandableListView的箭头
                expandableListView.setGroupIndicator(null);
                expandData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    //ExpandableListView一直展开
    public void expandData(){
        for (int i = 0; i < singleArrayList.get(0).getData().getCategories().size(); i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }
    public void requestLeftData(){
        StringRequest request = new StringRequest(URLValues.STRATEGY_SKU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SingleBean bean = gson.fromJson(response, SingleBean.class);
                ArrayList<SingleBean> arrayList = new ArrayList<>();
                for (int i = 0; i < bean.getData().getCategories().size(); i++) {
                    arrayList.add(bean);
                }
                SingleLeftAdapter adapter = new SingleLeftAdapter(context);
                adapter.setArrayList(arrayList);
                lv_left_single.setAdapter(adapter);

                linkageData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }
    //listView联动
    public void linkageData() {
        //点击左边listView实现右边的功能
        lv_left_single.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                expandableListView.setSelectedGroup(i);
            }
        });
        //滑动右边  右侧对左侧的联动
//        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//                switch (i){
//                    //当不滚动时
//                    case SCROLL_STATE_IDLE:
//                        // 判断滚动到底部
//                        if (absListView.getLastVisiblePosition() == (absListView.getCount() - 1)) {
//
//                        }
//                        break;
//                }
//
//            }
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//            }
//        });
    }
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                switch (scrollState) {
//                    // 当不滚动时
//                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//                        // 判断滚动到底部
//                        if (expandableListView.getLastVisiblePosition() == (expandableListView.getCount() - 1)) {
//                            expandableListView.setSelection(ListView.FOCUS_DOWN);
//                        }
//
//                        // 判断滚动到顶部
//                        if (listViewRight.getFirstVisiblePosition() == 0) {
//                            listViewRight.setSelection(0);
//                        }
//
//                        break;
//                }
//            }


}
