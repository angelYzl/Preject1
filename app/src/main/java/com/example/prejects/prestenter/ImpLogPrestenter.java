package com.example.prejects.prestenter;

import com.example.prejects.bean.LogBean;
import com.example.prejects.callback.LogCallBack;
import com.example.prejects.model.ImpLogModel;
import com.example.prejects.view.LogView;

public class ImpLogPrestenter implements LogPrestenter, LogCallBack {
    private ImpLogModel impLogModel;
    private LogView logView;

    public ImpLogPrestenter(LogView logView) {
        this.logView = logView;
        impLogModel  =new ImpLogModel();
    }

    @Override
    public void getData(LogBean.DataBean logBean) {
        if (impLogModel!=null){
            impLogModel.getData(this,logBean);
        }
    }

    @Override
    public void onSucess(String success) {
        if (logView!=null){
            logView.onSucess(success);
        }

    }

    @Override
    public void onFaile(String error) {
        if (logView!=null){
            logView.onFaile(error);
        }
    }
}
