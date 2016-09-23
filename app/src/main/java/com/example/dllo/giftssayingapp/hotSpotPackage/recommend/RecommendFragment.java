package com.example.dllo.giftssayingapp.hotspotpackage.recommend;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/ranks_v2/ranks/1?limit=20&offset=0
 */
public class RecommendFragment extends BaseFragment {
    private String strNet = "http://api.liwushuo.com/v2/ranks_v2/ranks/1?limit=20&offset=0";
    private RecyclerView recommend;
    private ImageView headImage;
    private RecyclerViewHeader header;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        recommend = bindView(R.id.rv_recommend);
        headImage = bindView(R.id.iv_head);
        header = bindView(R.id.rv_headView);
    }

    @Override
    protected void initData() {
        requestData();
    }

    public void requestData() {
        StringRequest request = new StringRequest(strNet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<RecommendBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                RecommendBean bean = gson.fromJson(response, RecommendBean.class);

                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    bean.getData().getItems().get(i).getCover_image_url();
                    bean.getData().getItems().get(i).getName();
                    bean.getData().getItems().get(i).getPrice();
                    arrayList.add(bean);
                }

                Picasso.with(context).load(bean.getData().getCover_image()).into(headImage);


                RecommendAdapter adapter = new RecommendAdapter(context);
                adapter.setArrayList(arrayList);
                recommend.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(context, 2);
                recommend.setLayoutManager(manager);

                //将顶部视图与recyclerview相关联
                header.attachTo(recommend, true);
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
