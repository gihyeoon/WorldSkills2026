package com.lgh.wordskill2026ex.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.lgh.wordskill2026ex.api.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientConfig {

    private static final String BASE_URL = "http://10.0.2.2:8080/";

    private static Retrofit retrofit = null;

    public static Retrofit getInstance(final Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    SharedPreferences prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
                    String token = prefs.getString("jwt_token", null);

                    Request.Builder builder = original.newBuilder();
                    if (token != null) {
                        // 토큰이 존재할 경우 요청 헤더에 Authorization: Bearer "토큰" 형식으로 추가합니다.
                        // 이는 이전에 백엔드 쪽에서 받을 때 적었던 형식과 동일합니다.
                        builder.header("Authorization", "Bearer " + token);
                    }
                    Request request = builder.build();
                    return chain.proceed(request);
                })
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 데이터를 자동으로 객체 변환
                    .build();
        }
        return retrofit;
    }

    public static ApiService create() {
        // Retrofit 객체 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
