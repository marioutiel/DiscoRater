package com.example.discoraterjorge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFeedActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        posts = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        posts.add(new Post("John", "Madrid, Spain", 202403060));
        posts.add(new Post("Jorge", "Gerona, Spain", 202403070));
        posts.add(new Post("Jaime", "Malaga, Spain", 202403060));
        posts.add(new Post("Ana", "Sevilla, Spain", 202403060));

        PostAdapter adapter = new PostAdapter(posts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}