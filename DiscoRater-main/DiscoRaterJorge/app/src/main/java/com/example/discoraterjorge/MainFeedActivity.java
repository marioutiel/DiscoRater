package com.example.discoraterjorge;

import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainFeedActivity extends AppCompatActivity
        implements BottomNavigationView.OnItemSelectedListener {

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);


        loadPosts();

        adapter = new PostAdapter(posts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_publish) {
            Intent intent = new Intent(this, PostActivity.class);
            startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
        } else if ( itemId == R.id.navigation_post) {
            scrollToTop();
        } else if (itemId == R.id.navigation_clubs) {
            Intent intent = new Intent(this, DiscoFeedActivity.class);
            startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
        } else if (itemId == R.id.navigation_feed) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
        }


        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePosts(); // Save posts when the activity is paused
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String location = data.getStringExtra("location");
            String experience = data.getStringExtra("experience");
            String cocktailPrice = data.getStringExtra("cocktailPrice");
            String beerPrice = data.getStringExtra("beerPrice");
            String tequilaPrice = data.getStringExtra("tequilaPrice");
            String imageUri = data.getStringExtra("imageUri");

            // With this we create the post
            if (name != null) {
                createPost(name, location, System.currentTimeMillis(), experience, cocktailPrice, beerPrice, tequilaPrice, imageUri);
            } else {
                createPost("User", location, System.currentTimeMillis(), experience, cocktailPrice, beerPrice, tequilaPrice, imageUri);
            }

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
        savePosts(); // Save the updated list of posts
    }

    private void scrollToTop() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    private void savePosts() {
        SharedPreferences sharedPreferences = getSharedPreferences("DiscoraterPosts", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonPosts = gson.toJson(posts); // Convert the list of posts to JSON
        editor.putString("posts", jsonPosts);
        editor.apply(); // Save the JSON string to SharedPreferences
    }

    private void loadPosts() {
        SharedPreferences sharedPreferences = getSharedPreferences("DiscoraterPosts", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonPosts = sharedPreferences.getString("posts", null);
        Type type = new TypeToken<ArrayList<Post>>() {}.getType();
        posts = gson.fromJson(jsonPosts, type); // Convert the JSON string back to a list of posts

        // If no posts are found in SharedPreferences, add default posts
        if (posts == null || posts.isEmpty()) {
            posts = new ArrayList<>();
            // Default posts
            posts.add(new Post("John", "Madrid, Spain", 202403060, "Great night out!", "8€", "5€", "3€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta1));
            posts.add(new Post("Jorge", "Gerona, Spain", 202403070, "Fun with friends", "7€", "4€", "2€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta2));
            posts.add(new Post("Jaime", "Malaga, Spain", 202403060, "Lively atmosphere", "9€", "5€", "4€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta3));
            posts.add(new Post("Ana", "Sevilla, Spain", 202403060, "Awesome DJ set", "10€", "6€", "5€", "android.resource://" + getPackageName() + "/" + R.drawable.fiesta4));

            // Save the default posts for future app launches
            savePosts();
        }
    }
}