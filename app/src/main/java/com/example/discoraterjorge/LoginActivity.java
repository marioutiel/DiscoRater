package com.example.discoraterjorge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.buttonLogin);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainFeedActivity.class);
            startActivity(intent);
        });
    }
}