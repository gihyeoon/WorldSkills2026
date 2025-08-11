package com.lgh.solofit_frontend.service;

import com.lgh.solofit_frontend.dto.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {

    @FormUrlEncoded
    @POST("auth/kakao")
    Call<TokenResponse> loginWithKakao(@Field("accessToken") String accessToken);

}
