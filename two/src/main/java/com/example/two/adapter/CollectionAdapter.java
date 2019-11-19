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
import com.example.two.bean.StudentBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
    private List<StudentBean> list;
    private Context context;

    public CollectionAdapter(List<StudentBean> list, Context context) {
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
        final StudentBean studentBean = list.get(position);
        Glide.with(context).load(studentBean.getUrl()).into(holder.mHomeImg);
        holder.mHomeTitle.setText(studentBean.getTitle());
        holder.mHomeText.setText(studentBean.getText());
        }

    @Override
    public int getItemCount() {
        return list.size();
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
