package com.example.quizmobileappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Details extends AppCompatActivity {

    private Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        goBackBtn = findViewById(R.id.btnBack);

        goBackBtn.setOnClickListener(view -> {
            finish();
        });
    }
}