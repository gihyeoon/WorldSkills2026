package com.lgh.snooptest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class QuicktestActivity extends AppCompatActivity {

    String[] colors = { "빨강", "파랑", "초록", "노랑" };
    int[] colorValues = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
    int currentIdx = 1, correctCount = 0, wrongCount = 0;
    TextView wordText;
    TextView questionCounterText;
    Button redBtn;
    Button blueBtn;
    Button greenBtn;
    Button yellowBtn;

    public QuicktestActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quicktest);

        wordText = findViewById(R.id.wordText);
        questionCounterText = findViewById(R.id.questionCounter);
        redBtn = findViewById(R.id.btnRed);
        blueBtn = findViewById(R.id.btnBlue);
        greenBtn = findViewById(R.id.btnGreen);
        yellowBtn = findViewById(R.id.btnYellow);

        redBtn.setOnClickListener(e -> {
            if (!wordText.getText().toString().equals(redBtn.getText().toString())) {
                wrongCount++;
            } else {
                correctCount++;
            }
            currentIdx++;

            int questionCount = Integer.parseInt(questionCounterText.getText().toString().split("/")[1].trim());
            if (questionCount == currentIdx-1) {
                Toast.makeText(this, "테스트가 종료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getApplicationContext(), ResultActivity.class);
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("wrongCount", wrongCount);
                startActivity(intent);
                return;
            }

            nextQuestion();
        });

        blueBtn.setOnClickListener(e -> {
            if (!wordText.getText().toString().equals(blueBtn.getText().toString())) {
                wrongCount++;
            } else {
                correctCount++;
            }
            currentIdx++;

            int questionCount = Integer.parseInt(questionCounterText.getText().toString().split("/")[1].trim());

            if (questionCount == currentIdx-1) {
                Toast.makeText(this, "테스트가 종료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getApplicationContext(), ResultActivity.class);
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("wrongCount", wrongCount);
                startActivity(intent);
                return;
            }

            nextQuestion();
        });

        greenBtn.setOnClickListener(e -> {
            if (!wordText.getText().toString().equals(greenBtn.getText().toString())) {
                wrongCount++;
            } else {
                correctCount++;
            }
            currentIdx++;

            int questionCount = Integer.parseInt(questionCounterText.getText().toString().split("/")[1].trim());

            if (questionCount == currentIdx-1) {
                Toast.makeText(this, "테스트가 종료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getApplicationContext(), ResultActivity.class);
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("wrongCount", wrongCount);
                startActivity(intent);
                return;
            }

            nextQuestion();
        });

        yellowBtn.setOnClickListener(e -> {
            if (!wordText.getText().toString().equals(yellowBtn.getText().toString())) {
                wrongCount++;
            } else {
                correctCount++;
            }
            currentIdx++;

            int questionCount = Integer.parseInt(questionCounterText.getText().toString().split("/")[1].trim());

            if (questionCount == currentIdx-1) {
                Toast.makeText(this, "테스트가 종료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getApplicationContext(), ResultActivity.class);
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("wrongCount", wrongCount);
                startActivity(intent);
                return;
            }

            nextQuestion();
        });

        showQuestion();
    }

    private void showQuestion() {
        Random rand = new Random();
        String word = colors[rand.nextInt(colors.length)];

        wordText.setText(word);
    }

    private void nextQuestion() {
        questionCounterText.setText(currentIdx + " / 5");

        showQuestion();
    }
}
