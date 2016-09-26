package com.example.dllo.giftssayingapp.hotspotpackage.originality;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
                ll_originality.addHeaderView(view);


                ArrayList<OriginalityBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                OriginalityBean bean = gson.fromJson(response, OriginalityBean.class);

                Picasso.with(context).load(bean.getData().getCover_image()).into(image);
                arrayList.add(bean);

                Log.d("OriginalityFragment", bean.getData().getCover_image());

                OriginalityAdapter adapter = new OriginalityAdapter(context);
                adapter.setArrayList(arrayList);
                ll_originality.setAdapter(adapter);



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

