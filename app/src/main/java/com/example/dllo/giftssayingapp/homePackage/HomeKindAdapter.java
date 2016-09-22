package com.example.dllo.giftssayingapp.homepackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.giftssayingapp.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/22.
 */
public class HomeKindAdapter extends BaseAdapter {
    private ArrayList<HomeKindBean> arrayList;
    private Context context;

    public void setArrayList(ArrayList<HomeKindBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public HomeKindAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_kind,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    class ViewHolder{
        public ViewHolder(View view) {
        }


    }
}
