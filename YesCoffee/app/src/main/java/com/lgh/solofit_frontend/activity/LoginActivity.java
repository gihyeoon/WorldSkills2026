package com.lgh.solofit_frontend.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.kakao.sdk.user.UserApiClient;
import com.lgh.solofit_frontend.R;
import com.lgh.solofit_frontend.dto.TokenResponse;
import com.lgh.solofit_frontend.service.AuthApi;

import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView kakaoBtn = findViewById(R.id.btnKakaoLogin);
        Button biometricBtn = findViewById(R.id.btnBiometric);

        kakaoBtn.setOnClickListener(e -> {
            if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, (token, error) -> {
                    if (error != null) {
                        Log.e("KakaoLogin", "KakaoTalk 로그인 실패", error);
                    } else if (token != null) {
                        Log.i("KakaoLogin", "KakaoTalk 로그인 성공 AccessToken : " + token.getAccessToken());

                        loginWithBackend(token.getAccessToken());

                        Toast.makeText(this, "소셜 로그인 성공했습니다.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    return null;
                });
            } else {
                UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, (token, error) -> {
                    if (error != null) {
                        Log.e("KakaoLogin", "KakaoAccount 로그인 실패", error);
                    } else if (token != null) {
                        Log.i("KakaoLogin", "KakaoAccount 로그인 성공 AccessToken : " + token.getAccessToken());

                        loginWithBackend(token.getAccessToken());

                        Toast.makeText(this, "소셜 로그인 성공했습니다.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    return null;
                });
            }
        });

        biometricBtn.setOnClickListener(e -> checkBiometricSupport());
    }

    private void loginWithBackend(String kakaoAccessToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.107:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthApi api = retrofit.create(AuthApi.class);

        Call<TokenResponse> call = api.loginWithKakao(kakaoAccessToken);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String jwtAccessToken = response.body().getAccessToken();
                    String jwtRefreshToken = response.body().getRefreshToken();

                    Log.i("BackendLogin", "JWT AccessToken : " + jwtAccessToken);
                } else {
                    Log.e("BackendLogin", "백엔드 로그인 실패 : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("BackendLogin", "API 호출 실패", t);
            }
        });
    }

    private void checkBiometricSupport() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                showBiometricPrompt();
                break;
            default:
                break;
        }
    }

    private void showBiometricPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(LoginActivity.this, "인증 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(LoginActivity.this, "인증 오류", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
                    }
                });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("지문 인증")
                .setSubtitle("본인 확인을 위해 지문을 스캔하세요.")
                .setNegativeButtonText("취소")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

}
