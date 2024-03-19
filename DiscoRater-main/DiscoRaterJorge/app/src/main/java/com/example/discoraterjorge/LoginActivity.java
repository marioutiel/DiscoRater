package com.example.discoraterjorge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private TextView textViewSignUp;
    private TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSign);
        textViewPassword = findViewById(R.id.textViewForgotPassword);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainFeedActivity.class);
            startActivity(intent);
        });

        textViewSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        textViewPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
            startActivity(intent);
        });
    }
}