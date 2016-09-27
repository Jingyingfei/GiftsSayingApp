package com.example.dllo.giftssayingapp.classifypackage.Strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.giftssayingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class StrateryColumnAdapter extends RecyclerView.Adapter<StrateryColumnAdapter.ViewHolder> {
    private ArrayList<StrategyColumnBean.DataBean.ChannelGroupsBean.ChannelsBean> categoryList;
    private Context context;

    public void setArrayList(ArrayList<StrategyColumnBean.DataBean.ChannelGroupsBean.ChannelsBean> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public StrateryColumnAdapter(Context context) {
        this.context = context;
    }

    @Override
    public StrateryColumnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_strategy_column, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StrateryColumnAdapter.ViewHolder holder, int position) {

        Picasso.with(context).load(categoryList.get(position).getCover_image_url()).into(holder.iv_column_image);
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_column_image;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_column_image = (ImageView) itemView.findViewById(R.id.iv_column_image);

        }
    }
}
