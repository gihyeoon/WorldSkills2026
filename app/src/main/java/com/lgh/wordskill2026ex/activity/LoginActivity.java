package com.lgh.wordskill2026ex.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.wordskill2026ex.R;
import com.lgh.wordskill2026ex.api.ApiService;
import com.lgh.wordskill2026ex.config.RetrofitClientConfig;
import com.lgh.wordskill2026ex.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final ApiService apiService = RetrofitClientConfig.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText pwdEditText = findViewById(R.id.passwordEditText);
        TextView findPwdText = findViewById(R.id.forgetPasswordTextView);
        TextView signUpText = findViewById(R.id.signUpTextView);
        Button loginBtn = findViewById(R.id.loginButton);
        Button googleLoginBtn = findViewById(R.id.googleLoginButton);

        String regEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String regPwd = "^(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,}$";

        // 로그인 버튼 클릭 시
        loginBtn.setOnClickListener(e -> {
            if (emailEditText.getText().toString().isEmpty() || !emailEditText.getText().toString().trim().matches(regEmail)) {
                Toast.makeText(getApplicationContext(), "이메일을 형식에 맞게 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pwdEditText.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pwdEditText.getText().toString().trim().matches(regPwd)) {
                Toast.makeText(getApplicationContext(), "비밀번호는 8자 이상, 특수문자 1개 이상 포함되어야합니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            apiService.login(emailEditText.getText().toString().trim(), pwdEditText.getText().toString().trim()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String token = response.body().getToken();
                        Long userNum = response.body().getUser().getNum();

                        // 토큰 저장
                        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
                        prefs.edit().putString("token", token).apply();
                        prefs.edit().putLong("userNum", userNum).apply();
                    } else {
                        Toast.makeText(getApplicationContext(), "해당하는 사용자가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    Log.e("API_ERROR", "통신 실패", t);
                    Toast.makeText(getApplicationContext(), "통신에 실패했습니다. 나중에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // 구글로 로그인 (아직 구현 x)
        googleLoginBtn.setOnClickListener(e -> {

        });

        // 비밀번호 찾기 버튼 클릭 시
        findPwdText.setOnClickListener(e -> {
            // 회원정보 재확인 화면으로 이동
        });

        // 회원가입 버튼 클릭 시
        signUpText.setOnClickListener(e -> {
            // 회원가입 화면으로 이동
        });
    }
}
