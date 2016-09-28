package com.example.dllo.giftssayingapp.classifypackage.Single;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/28.
 */
public class SingleAdapter extends BaseAdapter {
    private ArrayList<SingleBean> arrayList;
    private Context context;

    public void setArrayList(ArrayList<SingleBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public SingleAdapter(Context context) {

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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_left_single, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name_single.setText(arrayList.get(i).getData().getCategories().get(i).getName());
        return view;
    }

    class ViewHolder {

        private final TextView name_single;

        public ViewHolder(View view) {
            name_single = (TextView) view.findViewById(R.id.tv_name_left_single);
        }
    }
}
