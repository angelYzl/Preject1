package com.example.prejects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prejects.bean.LogBean;
import com.example.prejects.prestenter.ImpLogPrestenter;
import com.example.prejects.view.LogView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LogView {

    /**
     * 用户名
     */
    private EditText mEditUser;
    /**
     * 密码
     */
    private EditText mEditPass;
    /**
     * 登录
     */
    private Button mLogin;
    /**
     * 注册
     */
    private Button mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEditUser = (EditText) findViewById(R.id.edit_user);
        mEditPass = (EditText) findViewById(R.id.edit_pass);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mLog = (Button) findViewById(R.id.log);
        mLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login:
                initData();
                break;
            case R.id.log:
                startActivity(new Intent(this,LogActivity.class));
                break;
        }
    }

    private void initData() {
        LogBean.DataBean dataBean = new LogBean.DataBean();
        dataBean.setName(mEditUser.getText().toString());
        dataBean.setPassword(mEditPass.getText().toString());
        ImpLogPrestenter impLogPrestenter = new ImpLogPrestenter(this);
        impLogPrestenter.getData(dataBean);
    }

    @Override
    public void onSucess(String success) {
        if (success.equals("登录成功")){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    @Override
    public void onFaile(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
