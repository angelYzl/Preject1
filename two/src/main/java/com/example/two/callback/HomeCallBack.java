package com.example.two.callback;

import com.example.two.bean.HomeBean;

public interface HomeCallBack {
    void onSucess(HomeBean homeBean);
    void onFaile(String error);
}
