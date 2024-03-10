package com.example.discoraterjorge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

public class MainFeedActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    public static final int POST_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        posts = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        posts.add(new Post("John", "Madrid, Spain", 202403060, "Great night out!", "8€", "5€", "3€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta1));
        posts.add(new Post("Jorge", "Gerona, Spain", 202403070, "Fun with friends", "7€", "4€", "2€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta2));
        posts.add(new Post("Jaime", "Malaga, Spain", 202403060, "Lively atmosphere", "9€", "5€", "4€","android.resource://" + getPackageName() + "/" + R.drawable.fiesta3));
        posts.add(new Post("Ana", "Sevilla, Spain", 202403060, "Awesome DJ set", "10€", "6€", "5€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta4));

        adapter = new PostAdapter(posts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        Button btnCreatePost = findViewById(R.id.btnCreatePost);

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFeedActivity.this, PostActivity.class);
                startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
            }
        });

        Button btnClubs = findViewById(R.id.btnClubs);

        btnClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFeedActivity.this, DiscoFeedActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String experience = data.getStringExtra("experience");
            String cocktailPrice = data.getStringExtra("cocktailPrice");
            String beerPrice = data.getStringExtra("beerPrice");
            String tequilaPrice = data.getStringExtra("tequilaPrice");
            String imageUri = data.getStringExtra("imageUri");

            // With this we create the post
            createPost("User", "Location", System.currentTimeMillis(), experience, cocktailPrice, beerPrice, tequilaPrice, imageUri);

            // This is to scroll up to the first image in the feed once a new image is posted
            recyclerView.smoothScrollToPosition(0);

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

    public void createPost(String name, String location, long timestamp, String experience, String cocktailPrice, String beerPrice, String tequilaPrice, String imageUri) {
        Post newPost = new Post(name, location, timestamp, experience, cocktailPrice, beerPrice, tequilaPrice, imageUri);
        posts.add(0, newPost);
        adapter.notifyDataSetChanged();
    }

}