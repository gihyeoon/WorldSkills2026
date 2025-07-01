package com.lgh.wordskill2026ex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.lgh.wordskill2026ex.R;
import com.lgh.wordskill2026ex.api.ApiService;
import com.lgh.wordskill2026ex.config.RetrofitClientConfig;
import com.lgh.wordskill2026ex.response.ValidateEmailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    Button signUpBtn;
    Button googleLoginBtn;
    TextView gotoLoginText;
    TextView gotoMainText;

    private final ApiService apiService = RetrofitClientConfig.create();
    private final String regEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String regPwd = "^(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,}$";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.signUp_emailEditText);
        passwordEditText = findViewById(R.id.signUp_passwordEditText);
        signUpBtn = findViewById(R.id.signUpButton);
        googleLoginBtn = findViewById(R.id.signUp_googleSignUpButton);
        TextView gotoLoginText = findViewById(R.id.signUp_gotoLoginText);
        TextView gotoMainText = findViewById(R.id.signUp_gotoMainText);

        // 계정 생성 버튼 클릭 시
        signUpBtn.setOnClickListener(e -> {
            if (nameEditText.getText().toString().trim().isEmpty()) {
                Snackbar.make(e, "이름을 입력해주세요", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (emailEditText.getText().toString().isEmpty() || !emailEditText.getText().toString().trim().matches(regEmail)) {
                Snackbar.make(e, "이메일을 형식에 맞게 입력해주세요.", Snackbar.LENGTH_SHORT).show();
                return;
            }

            apiService.validateEmail(emailEditText.getText().toString().trim()).enqueue(new Callback<ValidateEmailResponse>() {
                @Override
                public void onResponse(@NonNull Call<ValidateEmailResponse> call, @NonNull Response<ValidateEmailResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        boolean success = response.body().isSuccess();

                        if (!success) {
                            Snackbar.make(e, "이미 사용 중인 이메일입니다.", Snackbar.LENGTH_SHORT).show();
                            return;
                        } else {
                            signUp(e);
                        }
                    } else {
                        Snackbar.make(e, "이미 사용 중인 이메일입니다.", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ValidateEmailResponse> call, @NonNull Throwable t) {
                    Snackbar.make(e, "통신에 실패했습니다. 다시 시도해주세요.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            });
        });

        // 구글 회원가입 버튼 클릭 시
        googleLoginBtn.setOnClickListener(e -> {

        });

        // 로그인 이동 버튼 클릭 시
        gotoLoginText.setOnClickListener(e -> {
            // 로그인 페이지로 이동
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        // 메인 이동 버튼 클릭 시
        gotoMainText.setOnClickListener(e -> {

        });
    }

    private void signUp(View e) {
        if (passwordEditText.getText().toString().isEmpty()) {
            Snackbar.make(e, "비밀번호를 입력해주세요.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (!passwordEditText.getText().toString().trim().matches(regPwd)) {
            Toast.makeText(getApplicationContext(), "비밀번호는 8자 이상, 특수문자 1개 이상 포함되어야합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
