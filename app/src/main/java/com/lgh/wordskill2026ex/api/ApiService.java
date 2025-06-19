package com.lgh.wordskill2026ex.api;

import com.lgh.wordskill2026ex.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/login")
    Call<LoginResponse> login(@Query("email") String email, @Query("password") String password);

}
