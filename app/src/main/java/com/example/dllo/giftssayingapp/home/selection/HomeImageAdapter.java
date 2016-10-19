package com.example.dllo.giftssayingapp.home.selection;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.bean.HomeImageBean;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/9/21.
 */
public class HomeImageAdapter extends PagerAdapter {
    private HomeImageBean bean;

    public void setBean(HomeImageBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    private Context context;

    public HomeImageAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return bean == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_image,null);
        ImageView image = (ImageView) view.findViewById(R.id.iv_home_image);
        Picasso.with(context).load(bean.getData().getBanners().get
                (position% bean.getData().getBanners().size()).getImage_url()).into(image);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeAllViews();
        }
        container.addView(view);
        return view;

    }
}
