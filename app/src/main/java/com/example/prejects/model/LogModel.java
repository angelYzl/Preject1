package com.example.prejects.model;

import com.example.prejects.bean.LogBean;
import com.example.prejects.callback.LogCallBack;

public interface LogModel {
    void getData(LogCallBack logCallBack, LogBean.DataBean logBean);

}
