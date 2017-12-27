package com.example.tareq.healthcare;

import android.app.Application;

/**
 * Created by TAREQ on 12/10/2016.
 */
public class MyApplication extends Application {
    private String loginUserName;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }
}
