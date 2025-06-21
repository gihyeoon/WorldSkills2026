package com.lgh.wordskill2026ex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lgh.wordskill2026ex.R;
import com.lgh.wordskill2026ex.api.ApiService;
import com.lgh.wordskill2026ex.config.RetrofitClientConfig;

public class RegisterActivity extends AppCompatActivity {

    private final ApiService apiService = RetrofitClientConfig.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText emailEditText = findViewById(R.id.signUp_emailEditText);
        EditText passwordEditText = findViewById(R.id.signUp_passwordEditText);
        Button signUpBtn = findViewById(R.id.signUpButton);
        Button googleLoginBtn = findViewById(R.id.signUp_googleSignUpButton);
        TextView gotoLoginText = findViewById(R.id.signUp_gotoLoginText);
        TextView gotoMainText = findViewById(R.id.signUp_gotoMainText);

        String regEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String regPwd = "^(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,}$";

        // 계정 생성 버튼 클릭 시
        signUpBtn.setOnClickListener(e -> {
            if (nameEditText.getText().toString().trim().isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (emailEditText.getText().toString().isEmpty() || !emailEditText.getText().toString().trim().matches(regEmail)) {
                Toast.makeText(getApplicationContext(), "이메일을 형식에 맞게 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (passwordEditText.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!passwordEditText.getText().toString().trim().matches(regPwd)) {
                Toast.makeText(getApplicationContext(), "비밀번호는 8자 이상, 특수문자 1개 이상 포함되어야합니다.", Toast.LENGTH_SHORT).show();
                return;
            }
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
}
