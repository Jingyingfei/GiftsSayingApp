package com.example.dllo.giftssayingapp.hotspotpackage.newstar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.hotspotpackage.main.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class NewStarAdapter extends RecyclerView.Adapter<NewStarAdapter.ViewHolder> {
    private ArrayList<NewStarBean> arrayList;
    private Context context;
    private OnItemClickListener starOnItemClickListener;

    public void setStarOnItemClickListener(OnItemClickListener starOnItemClickListener) {
        this.starOnItemClickListener = starOnItemClickListener;
    }

    public void setArrayList(ArrayList<NewStarBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public NewStarAdapter(Context context) {

        this.context = context;
    }
    @Override
    public NewStarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_newstar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewStarAdapter.ViewHolder holder, int position) {

        holder.name.setText(arrayList.get(position).getData().getItems().get(position).getName());
        holder.price.setText(arrayList.get(position).getData().getItems().get(position).getPrice());
        Picasso.with(context).load(arrayList.get(position).getData().getItems().get(position).getCover_image_url()).into(holder.image);
        if (starOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    starOnItemClickListener.OnItemClickListener
                            (holder.itemView, holder.getLayoutPosition());
                }
            });
        }

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
            image = (ImageView) itemView.findViewById(R.id.iv_newstar_image);
            name = (TextView) itemView.findViewById(R.id.tv_newstar_name);
            price = (TextView) itemView.findViewById(R.id.tv_newstar_price);
        }
    }
}
