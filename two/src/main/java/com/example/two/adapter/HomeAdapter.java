package com.example.two.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.two.R;
import com.example.two.WebActivity;
import com.example.two.bean.HomeBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeBean.RecentBean> list;
    private Context context;

    public HomeAdapter(List<HomeBean.RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final HomeBean.RecentBean bean = list.get(position);
        Glide.with(context).load(bean.getThumbnail()).into(holder.mHomeImg);
        holder.mHomeTitle.setText(bean.getTitle());
        holder.mHomeText.setText(bean.getNews_id() + "");
        if (itemLongClick != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemLongClick.itemLongClick(position);
                    return true;
                }
            });

        }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", bean.getUrl());
                    context.startActivity(intent);
                }
            });
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    ItemLongClick itemLongClick;

    public void setItemLongClick(ItemLongClick itemLongClick) {
        this.itemLongClick = itemLongClick;
    }

    public interface ItemLongClick {
        void itemLongClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mHomeImg;
        TextView mHomeTitle;
        TextView mHomeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mHomeImg = (ImageView) itemView.findViewById(R.id.home_img);
            this.mHomeTitle = (TextView) itemView.findViewById(R.id.home_title);
            this.mHomeText = (TextView) itemView.findViewById(R.id.home_text);
        }
    }
}
