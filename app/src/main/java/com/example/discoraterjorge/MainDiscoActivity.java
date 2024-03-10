package com.example.discoraterjorge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainDiscoActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
    private RecyclerView recyclerViewDisco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_feed);

        posts = new ArrayList<>();
        recyclerViewDisco = findViewById(R.id.recyclerViewDisco);

        posts.add(new Post("ClubA", "Saturday Night", 202403070));
        posts.add(new Post("Jorge", "Gerona, Spain", 202403070));
        posts.add(new Post("Jaime", "Malaga, Spain", 202403060));
        posts.add(new Post("Ana", "Sevilla, Spain", 202403060));

        PostAdapter adapter = new PostAdapter(posts, this);
        recyclerViewDisco.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDisco.setAdapter(adapter);
    }
}