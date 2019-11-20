package com.example.three;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HomeInface {
    String baseUrl = "http://gank.io/api/data/福利/20/";

    @GET("1")
    Observable<ImgBean> getData();
}
