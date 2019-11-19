package com.example.two.prestenter;

import com.example.two.bean.HomeBean;
import com.example.two.callback.HomeCallBack;
import com.example.two.model.ImpHomeModel;
import com.example.two.view.HomeView;

public class ImpHomePrestenter implements LogPrestenter, HomeCallBack {
    private ImpHomeModel impHomeModel;
    private HomeView logView;

    public ImpHomePrestenter(HomeView logView) {
        this.logView = logView;
        impHomeModel = new ImpHomeModel();
    }

    @Override
    public void getData() {
        if (impHomeModel!=null){
            impHomeModel.getData(this);
        }
    }

    @Override
    public void onSucess(HomeBean homeBean) {
        if (logView!=null){
            logView.onSucess(homeBean);
        }

    }

    @Override
    public void onFaile(String error) {
        if (logView!=null){
            logView.onFaile(error);
        }
    }
}
