package com.example.dllo.giftssayingapp.hotspot.recommend;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseFragment;
import com.example.dllo.giftssayingapp.base.URLValues;
import com.example.dllo.giftssayingapp.netrequest.VolleySingleton;
import com.example.dllo.giftssayingapp.bean.RecommendBean;
import com.example.dllo.giftssayingapp.hotspot.main.ItemActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/ranks_v2/ranks/1?limit=20&offset=0
 */
public class RecommendFragment extends BaseFragment {

    private RecyclerView recommend;
    private RecommendAdapter adapter;
    private RecommendBean bean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        recommend = bindView(R.id.rv_recommend);
    }

    @Override
    protected void initData() {
        requestData();
    }

    public void requestData() {
        StringRequest request = new StringRequest(URLValues.HOTSPOT_RECOMMEND, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<RecommendBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                bean = gson.fromJson(response, RecommendBean.class);

                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    arrayList.add(bean);
                }
                adapter = new RecommendAdapter(context);
                adapter.setArrayList(arrayList);
                GridLayoutManager manager = new GridLayoutManager(context, 2);
                recommend.setLayoutManager(manager);
                recommend.setAdapter(adapter);
                setHeader(recommend);


                //实现接口
                adapter.setRecommendOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, RecommendBean data) {
                        if (bean.getData().getItems().get(position).getPurchase_url() != null) {
                            Intent intent = new Intent(context, ItemActivity.class);
                            intent.putExtra("purchase_url", bean.getData().getItems().get(position).getPurchase_url());
                            getActivity().startActivity(intent);
                        } else {
                            Toast.makeText(context, "接口未找到", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }


    public void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(context).inflate(R.layout.header, view, false);
        ImageView imageView = (ImageView) header.findViewById(R.id.iv_header);
        Picasso.with(context).load(bean.getData().getCover_image()).into(imageView);
        adapter.setHeaderView(header);

    }
}
