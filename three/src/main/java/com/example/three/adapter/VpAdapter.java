package com.example.three.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.three.ImgActivity;
import com.example.three.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VpAdapter extends PagerAdapter {
    private ArrayList<String> list;
    private Context context;
    private String mString;
    private int mPostion;

    public VpAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setText(int pos, String string) {
        mString = string;
        mPostion = pos;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        View img = LayoutInflater.from(context).inflate(R.layout.item_img, container, false);
        ImageView image = img.findViewById(R.id.img);
        final String url = list.get(position);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImgActivity.class);
                intent.putExtra("url", url);
                context.startActivity(intent);
            }
        });
        TextView viewById = img.findViewById(R.id.text_pos);
        viewById.setText(mPostion+"");
        TextView viewById1 = img.findViewById(R.id.text_desc);
        viewById1.setText(mString);

        Log.e(TAG, "instantiateItem: " + url);
        Glide.with(context).load(url).into(image);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
