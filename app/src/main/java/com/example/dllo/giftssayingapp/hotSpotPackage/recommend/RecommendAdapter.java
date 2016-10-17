package com.example.dllo.giftssayingapp.hotspotpackage.recommend;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.RecommendBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by dllo on 16/9/23.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    private ArrayList<RecommendBean> arrayList;
    private Context context;
    private OnItemClickListener recommendOnItemClickListener;
    private ViewHolder viewHolder;
    private View headerView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public void setRecommendOnItemClickListener(OnItemClickListener recommendOnItemClickListener) {
        this.recommendOnItemClickListener = recommendOnItemClickListener;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);

    }

    public View getHeaderView() {
        return headerView;
    }

    public void setArrayList(ArrayList<RecommendBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null) return TYPE_NORMAL;
        if (0 == position) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) return new ViewHolder(headerView);
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecommendAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        final RecommendBean data = arrayList.get(pos);

        holder.name.setText(data.getData().getItems().get(position - 1).getName());
        holder.price.setText(data.getData().getItems().get(position - 1).getPrice());
        Picasso.with(context).load(data.getData().getItems().get(position - 1).getCover_image_url()).into(holder.image);

        if (recommendOnItemClickListener == null) return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommendOnItemClickListener.onItemClick(pos, data);
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
            image = (ImageView) itemView.findViewById(R.id.iv_recommend_image);
            name = (TextView) itemView.findViewById(R.id.tv_recommend_name);
            price = (TextView) itemView.findViewById(R.id.tv_recommend_price);
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, RecommendBean data);
    }

}
