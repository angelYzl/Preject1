package com.example.prejects;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prejects.bean.LogBean;
import com.example.prejects.bean.VerifyBean;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 用户名
     */
    private EditText mLogUser;
    /**
     * 密码
     */
    private EditText mLogPass;
    /**
     * 确认密码
     */
    private EditText mLogPasswrod;
    /**
     * 手机号
     */
    private EditText mLogPhone;
    /**
     * 验证码
     */
    private EditText mLogVerCode;
    /**
     * 注册
     */
    private Button mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initView();
    }

    private void initView() {
        mLogUser = (EditText) findViewById(R.id.log_user);
        mLogPass = (EditText) findViewById(R.id.log_pass);
        mLogPasswrod = (EditText) findViewById(R.id.log_passwrod);
        mLogPhone = (EditText) findViewById(R.id.log_phone);
        mLogVerCode = (EditText) findViewById(R.id.log_ver_code);
        mLog = (Button) findViewById(R.id.log);
        mLog.setOnClickListener(this);
        verify();




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.log:
                login();
                finish();
                break;
        }
    }

    private void verify() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LogInterface.verify)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<VerifyBean> observable = retrofit.create(LogInterface.class).getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerifyBean verifyBean) {
                        String data = verifyBean.getData();
                        mLogVerCode.setText(data);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void login() {

        LogBean.DataBean bean = new LogBean.DataBean();
        bean.setName(mLogUser.getText().toString());
        bean.setPassword(mLogPass.getText().toString());
        bean.setPhone(mLogPhone.getText().toString());
        bean.setHeader(mLogVerCode.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LogInterface.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LogInterface anInterface = retrofit.create(LogInterface.class);
        Observable<LogBean> observable = anInterface.getLogin(mLogUser.getText().toString(), mLogPass.getText().toString(), mLogPhone.getText().toString(), mLogVerCode.getText().toString());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogBean logBean) {
                        Toast.makeText(LogActivity.this, logBean.getRet(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LogActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
