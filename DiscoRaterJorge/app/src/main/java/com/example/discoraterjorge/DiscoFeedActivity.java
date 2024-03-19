package com.example.discoraterjorge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class DiscoFeedActivity extends AppCompatActivity
        implements BottomNavigationView.OnItemSelectedListener {

    private ArrayList<Disco> discos;
    private RecyclerView recyclerViewDisco;
    private DiscoAdapter adapter;
    public static final int POST_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_feed);

        discos = new ArrayList<>();
        recyclerViewDisco = findViewById(R.id.recyclerViewDisco);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);


        discos.add(new Disco("ClubA", "Sunday", "Electro", "DJ", "20€","8€", "5€", "3€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta1));
        discos.add(new Disco("ClubB", "Saturday", "Raggaeton", "Artists On Live", "30€","7€", "4€", "2€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta2));
        discos.add(new Disco("ClubC", "Thursday", "R&R", "Classics", "20€", "9€", "5€", "4€","android.resource://" + getPackageName() + "/" + R.drawable.fiesta3));
        discos.add(new Disco("ClubD", "Tuesday", "Rap", "DJ & Live Artist", "15€","10€", "6€", "5€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta4));

        adapter = new DiscoAdapter(discos, this);
        recyclerViewDisco.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDisco.setAdapter(adapter);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_publish) {
            Intent intent = new Intent(this, PostActivity.class);
            startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
        } else if ( itemId == R.id.navigation_post) {
            Intent intent = new Intent(this, MainFeedActivity.class);
            startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
        } else if (itemId == R.id.navigation_clubs) {
            scrollToTop();
        }


        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String event = data.getStringExtra("event");
            String ticketPrice = data.getStringExtra("ticketPrice");
            String cocktailPrice = data.getStringExtra("cocktailPrice");
            String beerPrice = data.getStringExtra("beerPrice");
            String tequilaPrice = data.getStringExtra("tequilaPrice");
            String imageUri = data.getStringExtra("imageUri");

            // With this we create the post
            createDisco("User", "Location", "Theme", event, ticketPrice, cocktailPrice, beerPrice, tequilaPrice, imageUri);

            // This is to scroll up to the first image in the feed once a new image is posted
            recyclerViewDisco.smoothScrollToPosition(0);

            // And this to notify that a new image has been posted
            Toast toast = Toast.makeText(this, "Post created successfully", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();

        } else if (resultCode == RESULT_CANCELED) { // Check if the result is CANCELED
            Toast toast = Toast.makeText(this, "Post cancelled", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        }

    }

    public void createDisco(String name, String day, String theme, String event, String ticketPrice, String cocktailPrice, String beerPrice, String tequilaPrice, String imageUri) {
        Disco newDisco = new Disco(name, day, theme, event, ticketPrice, cocktailPrice, beerPrice, tequilaPrice, imageUri);
        discos.add(0, newDisco);
        adapter.notifyDataSetChanged();
    }


    private void scrollToTop() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDisco);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

}