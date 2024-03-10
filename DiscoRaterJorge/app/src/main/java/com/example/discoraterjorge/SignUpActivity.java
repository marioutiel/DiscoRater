package com.example.discoraterjorge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText userEmailEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.editTextUsername);
        userEmailEditText = findViewById(R.id.editTextUserEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        passwordConfirmEditText = findViewById(R.id.editTextPassword2);
        signUpButton = findViewById(R.id.buttonSignUp);

        signUpButton.setOnClickListener(v -> attemptSignUp());
    }

    private void attemptSignUp() {
        String username = usernameEditText.getText().toString();
        String userEmail = userEmailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordConfirmEditText.getText().toString();

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userEmail.isEmpty()) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // If validation is successful, start the MainFeedActivity
        Intent intent = new Intent(SignUpActivity.this, MainFeedActivity.class);
        Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
