package com.example.dllo.giftssayingapp.hotspotpackage.newstar;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.NewStarBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class NewStarAdapter extends RecyclerView.Adapter<NewStarAdapter.ViewHolder> {
    private ArrayList<NewStarBean> arrayList;
    private Context context;
    private OnItemClickListener starOnItemClickListener;
    private View headerView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public void setStarOnItemClickListener(OnItemClickListener starOnItemClickListener) {
        this.starOnItemClickListener = starOnItemClickListener;
    }

    public void setArrayList(ArrayList<NewStarBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemChanged(0);
    }

    public NewStarAdapter(Context context) {

        this.context = context;
    }
    @Override
    public int getItemViewType(int position) {
        if (headerView == null) return TYPE_NORMAL;
        if (0 == position) return TYPE_HEADER;
        return TYPE_NORMAL;
    }
    @Override
    public NewStarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) return new ViewHolder(headerView);
        View view = LayoutInflater.from(context).inflate(R.layout.item_newstar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewStarAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        final NewStarBean data = arrayList.get(pos);
        holder.name.setText(arrayList.get(position).getData().getItems().get(position).getName());
        holder.price.setText(arrayList.get(position).getData().getItems().get(position).getPrice());
        Picasso.with(context).load(arrayList.get(position).getData().getItems().get(position).getCover_image_url()).into(holder.image);
        if (starOnItemClickListener == null) return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starOnItemClickListener.onItemClick(pos, data);
            }
        });

    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return headerView == null ? arrayList.size() : arrayList.size() + 1;
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
    interface OnItemClickListener {
        void onItemClick(int position, NewStarBean data);
    }
}
