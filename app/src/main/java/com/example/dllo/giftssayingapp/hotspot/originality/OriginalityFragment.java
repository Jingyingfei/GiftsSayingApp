package com.example.dllo.giftssayingapp.hotspot.originality;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseFragment;
import com.example.dllo.giftssayingapp.base.URLValues;
import com.example.dllo.giftssayingapp.netrequest.VolleySingleton;
import com.example.dllo.giftssayingapp.bean.OriginalityBean;
import com.example.dllo.giftssayingapp.hotspot.main.ItemActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class OriginalityFragment extends BaseFragment {

    private ListView ll_originality;

    @Override
    protected int setLayout() {
        return R.layout.fragment_originality;
    }

    @Override
    protected void initView() {
        ll_originality = bindView(R.id.ll_originality);
    }

    @Override
    protected void initData() {
        requestData();
    }

    public void requestData() {
        StringRequest request = new StringRequest(URLValues.HOTSPOT_ORIDINALITY, new Response.Listener<String>() {

            private ImageView image;

            @Override
            public void onResponse(String response) {

                View view = LayoutInflater.from(context).inflate(R.layout.item_originality_image, null);
                image = (ImageView) view.findViewById(R.id.iv_image_origin);

                ArrayList<OriginalityBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                final OriginalityBean bean = gson.fromJson(response, OriginalityBean.class);

                Picasso.with(context).load(bean.getData().getCover_image()).into(image);
                arrayList.add(bean);

                OriginalityAdapter adapter = new OriginalityAdapter(context);
                adapter.setArrayList(arrayList);
                ll_originality.setAdapter(adapter);
                ll_originality.addHeaderView(view);

                adapter.setOnListViewItemClick(new OnListViewItemClick() {
                    @Override
                    public void click(int itemPositon) {
                        Intent intent = new Intent(context, ItemActivity.class);
                        intent.putExtra("url", bean.getData().getItems().get(itemPositon).getPurchase_url());
                        startActivity(intent);
                    }
                });

                adapter.setOnListViewItemRightClick(new OnListViewItemRightClick() {
                    @Override
                    public void rightClick(int rightPosition) {
                        Intent intent = new Intent(context, ItemActivity.class);
                        intent.putExtra("url", bean.getData().getItems().get(rightPosition).getPurchase_url());
                        startActivity(intent);
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
}

