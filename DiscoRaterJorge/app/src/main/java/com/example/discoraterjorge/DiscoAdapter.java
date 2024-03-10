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

public class DiscoAdapter extends RecyclerView.Adapter<DiscoAdapter.DiscoViewHolder> {
    private List<Disco> discoList;
    private Context context;

    public DiscoAdapter(List<Disco> discoList, Context context) {
        this.discoList = discoList;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disco_feed, parent, false);
        return new DiscoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoViewHolder holder, int position) {
        Disco disco = discoList.get(position);
        holder.nameTextView.setText(disco.getName());
        holder.dayTextView.setText(disco.getDay());
        holder.themeTextView.setText(disco.getTheme());
        holder.eventsTextView.setText(disco.getEvents()); // Establecer el texto de la experiencia
        holder.cocktailPriceTextView.setText(disco.getCocktailPrice()); // Establecer el precio del cóctel
        holder.beerPriceTextView.setText(disco.getBeerPrice()); // Establecer el precio de la cerveza
        holder.tequilaPriceTextView.setText(disco.getTequilaPrice()); // Establecer el precio del tequila

        // Configurar la imagen si hay una URI de foto disponible
        if (disco.getImageUri() != null) {
            Log.d("FeedActivity", "URI de imagen disponible: " + disco.getImageUri());
            holder.imageView.setImageURI(Uri.parse(disco.getImageUri()));
        } else {
            Log.d("FeedActivity", "No hay URI de imagen disponible");
            // Si no hay una URI de foto, ocultar el ImageView o establecer una imagen predeterminada
            holder.imageView.setImageResource(R.drawable.landing_image);
            // o holder.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return discoList.size();
    }

    public class DiscoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView dayTextView;
        TextView themeTextView;
        TextView eventsTextView; // Nuevo TextView para la experiencia
        TextView cocktailPriceTextView; // Nuevo TextView para el precio del cóctel
        TextView beerPriceTextView; // Nuevo TextView para el precio de la cerveza
        TextView tequilaPriceTextView; // Nuevo TextView para el precio del tequila
        ImageView imageView;


        public DiscoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewNameDisco);
            dayTextView = itemView.findViewById(R.id.textViewDay);
            themeTextView = itemView.findViewById(R.id.textViewTheme);
            eventsTextView = itemView.findViewById(R.id.textViewEventDisco); // Inicialización del nuevo TextView
            cocktailPriceTextView = itemView.findViewById(R.id.textViewCocktailPriceDisco); // Inicialización del nuevo TextView
            beerPriceTextView = itemView.findViewById(R.id.textViewBeerPriceDisco); // Inicialización del nuevo TextView
            tequilaPriceTextView = itemView.findViewById(R.id.textViewTequilaPriceDisco);
            imageView = itemView.findViewById(R.id.imageView2Disco);
        }
    }
}
