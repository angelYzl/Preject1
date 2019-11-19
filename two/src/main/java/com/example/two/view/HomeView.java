package com.example.two.view;

import com.example.two.bean.HomeBean;

public interface HomeView {
    void onSucess(HomeBean homeBean);
    void onFaile(String error);
}
