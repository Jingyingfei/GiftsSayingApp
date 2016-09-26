package com.example.dllo.giftssayingapp.classifypackage.Strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class StrateryColumnAdapter extends RecyclerView.Adapter<StrateryColumnAdapter.ViewHolder> {
    private ArrayList<StrategyColumnBean> arrayList;
    private Context context;

    public void setArrayList(ArrayList<StrategyColumnBean> arrayList) {
        this.arrayList = arrayList;
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
        holder.tv_column_name.setText(arrayList.get(position).getData().getChannel_groups().get(position).getName());
//        holder.tv_column_kind.setText(arrayList.get(position).getData().getChannel_groups().get(position).getChannels().get(position).getName());
        Picasso.with(context).load(arrayList.get(position).getData().getChannel_groups().get(position).getChannels().get(position).getCover_image_url()).into(holder.iv_column_image);

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_column_kind;
        private final TextView tv_column_name;
        private final ImageView iv_column_image;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_column_kind = (TextView) itemView.findViewById(R.id.tv_column_kind);
            tv_column_name = (TextView) itemView.findViewById(R.id.tv_column_name);
            iv_column_image = (ImageView) itemView.findViewById(R.id.iv_column_image);
        }
    }
}
