package com.example.dllo.giftssayingapp.classify.Single;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.bean.SingleBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/28.
 */
public class SingleLeftAdapter extends BaseAdapter {
    private ArrayList<SingleBean> arrayList;
    private Context context;
    private int selectedPosition = -1;// 选中的位置

    public void setArrayList(ArrayList<SingleBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public SingleLeftAdapter(Context context) {

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

    public void setSelectedPosition(int position) {
        selectedPosition = position;
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
        if (selectedPosition == i){
            viewHolder.name_single.setSelected(true);
            viewHolder.name_single.setPressed(true);
        } else {
            viewHolder.name_single.setSelected(false);
            viewHolder.name_single.setPressed(false);
        }
        viewHolder.name_single.setTextColor(Color.WHITE);
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
