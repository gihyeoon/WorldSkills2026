package com.lgh.snooptest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView cardQuickTest = findViewById(R.id.cardQuickTest);
        CardView cardFocusTest = findViewById(R.id.cardFocusTest);
        CardView cardRecords = findViewById(R.id.cardRecords);

        cardQuickTest.setOnClickListener(e -> {
            Intent intent = new Intent(MainActivity.this, QuicktestActivity.class);
            startActivity(intent);
        });

        cardFocusTest.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, FocustestActivity.class));
        });

        cardRecords.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, RecordsActivity.class));
        });
    }
}
