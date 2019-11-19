package com.example.two.model;

import com.example.two.HomeInterface;
import com.example.two.bean.HomeBean;
import com.example.two.callback.HomeCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpHomeModel implements HomeModel {

    @Override
    public void getData(final HomeCallBack homeCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HomeInterface homeInterface = retrofit.create(HomeInterface.class);
        Observable<HomeBean> observable = homeInterface.getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        homeCallBack.onSucess(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                    homeCallBack.onFaile("网络解析失败："+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
