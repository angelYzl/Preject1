package com.example.three.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.three.ImgBean;
import com.example.three.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdpater extends RecyclerView.Adapter<HomeAdpater.ViewHolder> {
    private List<ImgBean.ResultsBean> data;
    private Context context;

    public HomeAdpater(List<ImgBean.ResultsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View img = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(img);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getUrl()).into(holder.mImg1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg1;
        ImageView mImg2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImg1 = (ImageView) itemView.findViewById(R.id.img1);
        }
    }
}
