package com.example.two;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    public static Context app;

    public BaseApplication() {
        this.app = this;
    }

    public static Context getApp() {

        return app;
    }
}
