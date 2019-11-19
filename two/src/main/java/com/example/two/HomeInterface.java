package com.example.two;

import com.example.two.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HomeInterface {
    String baseUrl = "https://news-at.zhihu.com/api/4/news/";

    @GET("hot")
    Observable<HomeBean> getData();
}
