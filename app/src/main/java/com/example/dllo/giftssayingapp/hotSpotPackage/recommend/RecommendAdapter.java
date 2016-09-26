package com.example.dllo.giftssayingapp.hotspotpackage.recommend;

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
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    private ArrayList<RecommendBean> arrayList;
    private Context context;

    public void setArrayList(ArrayList<RecommendBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public RecommendAdapter(Context context) {

        this.context = context;
    }

    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendAdapter.ViewHolder holder, int position) {

            holder.name.setText(arrayList.get(position).getData().getItems().get(position).getName());
            holder.price.setText(arrayList.get(position).getData().getItems().get(position).getPrice());
            Picasso.with(context).load(arrayList.get(position).getData().getItems().get(position).getCover_image_url()).into(holder.image);


    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_recommend_image);
            name = (TextView) itemView.findViewById(R.id.tv_recommend_name);
            price = (TextView) itemView.findViewById(R.id.tv_recommend_price);
        }
    }

}
