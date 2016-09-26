package com.example.dllo.giftssayingapp.hotspotpackage.newstar;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.hotspotpackage.recommend.RecommendAdapter;
import com.example.dllo.giftssayingapp.hotspotpackage.recommend.RecommendBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class NewStarFragment extends BaseFragment {
    private RecyclerView newStar;
    private RecyclerViewHeader mHeader;
    private ImageView image;

    @Override
    protected int setLayout() {
        return R.layout.fragment_new_star;
    }

    @Override
    protected void initView() {
        newStar = bindView(R.id.rv_newstar);
        image = bindView(R.id.iv_newstar_image);
        mHeader = bindView(R.id.newstar_header);

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
                NewStarBean bean = gson.fromJson(response, NewStarBean.class);

                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    bean.getData().getItems().get(i).getCover_image_url();
                    bean.getData().getItems().get(i).getName();
                    bean.getData().getItems().get(i).getPrice();
                    arrayList.add(bean);

                }

                Picasso.with(context).load(bean.getData().getCover_image()).into(image);

                NewStarAdapter adapter = new NewStarAdapter(context);
                adapter.setArrayList(arrayList);
                GridLayoutManager manager = new GridLayoutManager(context, 2);
                newStar.setLayoutManager(manager);
                newStar.setAdapter(adapter);
                mHeader.attachTo(newStar);


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
