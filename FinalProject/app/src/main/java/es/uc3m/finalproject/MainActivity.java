package es.uc3m.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        View button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SecondActivity.class);
            startActivity(intent);
        });
    }

}