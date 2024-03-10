package com.example.discoraterjorge;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postList;
    private Context context;

    public PostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_feed, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.nameTextView.setText(post.getName());
        holder.locationTextView.setText(post.getLocation());
        holder.timestampTextView.setText(formatTimestamp(post.getTimestamp()));
        holder.experienceTextView.setText(post.getExperience()); // Establecer el texto de la experiencia
        holder.cocktailPriceTextView.setText(post.getCocktailPrice()); // Establecer el precio del cóctel
        holder.beerPriceTextView.setText(post.getBeerPrice()); // Establecer el precio de la cerveza
        holder.tequilaPriceTextView.setText(post.getTequilaPrice()); // Establecer el precio del tequila

        // Configurar la imagen si hay una URI de foto disponible
        if (post.getImageUri() != null) {
            Log.d("FeedActivity", "URI de imagen disponible: " + post.getImageUri());
            holder.imageView.setImageURI(Uri.parse(post.getImageUri()));
        } else {
            Log.d("FeedActivity", "No hay URI de imagen disponible");
            // Si no hay una URI de foto, ocultar el ImageView o establecer una imagen predeterminada
            holder.imageView.setImageResource(R.drawable.landing_image);
            // o holder.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;
        TextView timestampTextView;
        TextView experienceTextView; // Nuevo TextView para la experiencia
        TextView cocktailPriceTextView; // Nuevo TextView para el precio del cóctel
        TextView beerPriceTextView; // Nuevo TextView para el precio de la cerveza
        TextView tequilaPriceTextView; // Nuevo TextView para el precio del tequila
        ImageView imageView;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            locationTextView = itemView.findViewById(R.id.textViewLocation);
            timestampTextView = itemView.findViewById(R.id.textViewTimeAgo);
            experienceTextView = itemView.findViewById(R.id.textViewExperience); // Inicialización del nuevo TextView
            cocktailPriceTextView = itemView.findViewById(R.id.textViewCocktailPrice); // Inicialización del nuevo TextView
            beerPriceTextView = itemView.findViewById(R.id.textViewBeerPrice); // Inicialización del nuevo TextView
            tequilaPriceTextView = itemView.findViewById(R.id.textViewTequilaPrice);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }

    private String formatTimestamp(long timestamp) {
        long now = System.currentTimeMillis();
        CharSequence relativeTimeSpan;
        relativeTimeSpan = DateUtils.getRelativeTimeSpanString(timestamp, now, DateUtils.MINUTE_IN_MILLIS);
        return relativeTimeSpan.toString();
    }
}

