package com.lgh.solofit_frontend.activity;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class SoloFitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "b7dbe973586ba7e0cd6b4d8bd5dc972e");
    }
}
