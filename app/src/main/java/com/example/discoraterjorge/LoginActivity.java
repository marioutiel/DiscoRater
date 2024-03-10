package com.example.discoraterjorge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private Button buttonDisco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.buttonLogin);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainFeedActivity.class);
            startActivity(intent);
        });

        buttonDisco = findViewById(R.id.buttonLoginDisco);

        buttonDisco.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainDiscoActivity.class);
            startActivity(intent);
        });
    }
}