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
public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.StrategyViewHolder> {
    Context mContext;
    ArrayList<StrategyBean> arrayList = new ArrayList<>();

    public StrategyAdapter(Context context) {
        mContext = context;
    }

    public void setArrayList(ArrayList<StrategyBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public StrategyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_strategy,parent,false);
        StrategyViewHolder viewHolder = new StrategyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StrategyViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(arrayList.get(position).getData().getColumns().get(position).getBanner_image_url())
                .into(holder.mBanner_image_url);
        holder.mTitle_strategy.setText(arrayList.get(position).getData().getColumns().get(position).getTitle());
        holder.mSubtitle_strategy.setText(arrayList.get(position).getData().getColumns().get(position).getSubtitle());
        holder.mAuthor_strategy.setText(arrayList.get(position).getData().getColumns().get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class StrategyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mBanner_image_url;
        private final TextView mTitle_strategy;
        private final TextView mSubtitle_strategy;
        private final TextView mAuthor_strategy;

        public StrategyViewHolder(View itemView) {
            super(itemView);
            mBanner_image_url = (ImageView) itemView.findViewById(R.id.banner_image_url_strategy);
            mTitle_strategy = (TextView) itemView.findViewById(R.id.title_strategy);
            mSubtitle_strategy = (TextView) itemView.findViewById(R.id.subtitle_strategy);
            mAuthor_strategy = (TextView) itemView.findViewById(R.id.author_strategy);
        }
    }
}

