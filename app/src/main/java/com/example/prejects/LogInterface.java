package com.example.prejects;

import com.example.prejects.bean.LogBean;
import com.example.prejects.bean.VerifyBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LogInterface {
    String baseUrl="http://yun918.cn/study/public/index.php/";
    String verify = "http://yun918.cn/study/public/index.php/";

    @POST("login?")
    @FormUrlEncoded
    Observable<LogBean> getData(@Field("username") String username,@Field("password")String password);

    @POST("login?register")
    @FormUrlEncoded
    Observable<LogBean> getLogin(@Field("username") String username,@Field("password")String password,@Field("phone") String phone,@Field("verify") String verify);

    @GET("verify")
    Observable<VerifyBean> getData();

}
