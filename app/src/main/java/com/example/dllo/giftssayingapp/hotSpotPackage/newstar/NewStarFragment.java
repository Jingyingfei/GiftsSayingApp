package com.example.dllo.giftssayingapp.hotspotpackage.newstar;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.beanpackage.NewStarBean;
import com.example.dllo.giftssayingapp.hotspotpackage.main.ItemActivity;
import com.example.dllo.giftssayingapp.hotspotpackage.main.OnItemClickListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class NewStarFragment extends BaseFragment {
    private RecyclerView newStar;

    private ImageView image;
    private FrameLayout fl_newstar;
    private NewStarBean bean;
    private RecyclerView header;
    private NewStarAdapter adapter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_new_star;
    }

    @Override
    protected void initView() {
        newStar = bindView(R.id.rv_newstar);
        fl_newstar = bindView(R.id.fl_newstar);

    }

    @Override
    protected void initData() {

        requestData();
    }

    public void requestData() {
        StringRequest request = new StringRequest(URLValues.HOTSPOT_NEWSTAR, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                ArrayList<NewStarBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                bean = gson.fromJson(response, NewStarBean.class);

                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    arrayList.add(bean);
                }

                Picasso.with(context).load(bean.getData().getCover_image()).into(image);

                adapter = new NewStarAdapter(context);
                adapter.setArrayList(arrayList);
                GridLayoutManager manager = new GridLayoutManager(context, 2);
                newStar.setLayoutManager(manager);
                newStar.setAdapter(adapter);
                setHeader(newStar);


                //接口实现
                adapter.setStarOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(View view, int position) {
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
