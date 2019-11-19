package com.example.prejects.model;

import android.util.Log;

import com.example.prejects.LogInterface;
import com.example.prejects.bean.LogBean;
import com.example.prejects.callback.LogCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ImpLogModel implements LogModel {

    @Override
    public void getData(final LogCallBack logCallBack, LogBean.DataBean logBean) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LogInterface.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        LogInterface anInterface = retrofit.create(LogInterface.class);

        Observable<LogBean> observable = anInterface.getData(logBean.getName(), logBean.getPassword());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogBean logBean) {
                        logCallBack.onSucess(logBean.getRet());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage());
                        logCallBack.onFaile("登录失败，请重新登录！"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
