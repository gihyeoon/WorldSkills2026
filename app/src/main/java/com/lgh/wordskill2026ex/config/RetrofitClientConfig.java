package com.lgh.wordskill2026ex.config;

import com.lgh.wordskill2026ex.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientConfig {

    private static final String BASE_URL = "http://10.0.2.2:8080/";

    public static ApiService create() {
        // Retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
