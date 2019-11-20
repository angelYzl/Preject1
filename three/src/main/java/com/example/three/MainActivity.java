package com.example.three;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.three.adapter.HomeAdpater;
import com.example.three.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private ViewPager mVp;
    private TextView mTvTitle;
    private TextView mText1;
    private TextView mText2;
    private RecyclerView mRecycler;
    private ArrayList<String> list;
    private VpAdapter adapter;
    public static List<ImgBean.ResultsBean> data = new ArrayList<>();
    private HomeAdpater homeAdpater;

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HomeInface.baseUrl)
                .build();
        Observable<ImgBean> observable = retrofit.create(HomeInface.class).getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImgBean imgBean) {
                        List<ImgBean.ResultsBean> results = imgBean.getResults();
                        data.addAll(results);
                        homeAdpater.notifyDataSetChanged();
                        getString(results);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void getString(List<ImgBean.ResultsBean> results) {
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i).getUrl());
            mTvTitle.setText(results.get(i).getType());
            mText1.setText(results.get(i).getSource());
            mText2.setText(results.get(i).getWho());
            Log.e(TAG, "getString: " + results.get(i).getUrl());
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mText1 = (TextView) findViewById(R.id.text1);
        mText2 = (TextView) findViewById(R.id.text2);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        homeAdpater = new HomeAdpater(data, this);

         final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(homeAdpater);


        list = new ArrayList<>();
        adapter = new VpAdapter(list, this);
        mVp.setAdapter(adapter);
        mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                int i = manager.findFirstVisibleItemPosition();
                mVp.setCurrentItem(i);
            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                adapter.setText(position, data.get(position).getDesc());
                mRecycler.scrollToPosition(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
