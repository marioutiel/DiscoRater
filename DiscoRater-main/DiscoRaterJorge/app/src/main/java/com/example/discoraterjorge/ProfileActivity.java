package com.example.discoraterjorge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.app.DatePickerDialog;
import android.widget.EditText;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity
        implements BottomNavigationView.OnItemSelectedListener {

    private EditText addTextBirthday;
    public static final int POST_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);

        // Add Birthday
        addTextBirthday = findViewById(R.id.addTextBirthday);
        addTextBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Spinner for Location
        // String[] cities = {"A Coruña", "Albacete", "Alcalá de Henares", "Alcobendas", "Alcorcón", "Algeciras", "Alicante", "Almería", "Alzira", "Badajoz", "Badalona", "Barakaldo", "Barcelona", "Benalmádena", "Benidorm", "Bilbao", "Burgos", "Cáceres", "Cádiz", "Cartagena", "Castelldefels", "Castellón de la Plana", "Ceuta", "Chiclana de la Frontera", "Ciudad Real", "Córdoba", "Cornellà de Llobregat", "Coslada", "Cuenca", "Dos Hermanas", "El Puerto de Santa María", "Elche", "Ferrol", "Figueres", "Fuenlabrada", "Fuengirola", "Gandía", "Getafe", "Gijón", "Girona", "Granada", "Granollers", "Guadalajara", "Hospitalet de Llobregat", "Huelva", "Huesca", "Ibiza", "Irun", "Jaén", "Jerez de la Frontera", "La Línea de la Concepción", "Las Palmas de Gran Canaria", "Leganés", "León", "Lleida", "Lloret de Mar", "Logroño", "Lorca", "Los Alcázares", "Madrid", "Málaga", "Manresa", "Marbella", "Mataró", "Melilla", "Mérida", "Mijas", "Móstoles", "Murcia", "Ourense", "Oviedo", "Palencia", "Palma", "Parla", "Paterna", "Plasencia", "Pontevedra", "Puerto de Sagunto", "Reus", "Rivas-Vaciamadrid", "Roquetas de Mar", "Rubí", "Salamanca", "San Bartolomé de Tirajana", "San Cristóbal de La Laguna", "San Fernando", "San Fernando de Henares", "San Sebastián", "Santa Coloma de Gramenet", "Santa Cruz de Tenerife", "Santander", "Santiago de Compostela", "Segovia", "Sevilla", "Soria", "Tarragona", "Telde", "Terrassa", "Toledo", "Torrejón de Ardoz", "Torrelavega", "Torremolinos", "Torrent", "Torrevieja", "Valdemoro", "Valencia", "Valladolid", "Vélez-Málaga", "Vic", "Vigo", "Vilanova i la Geltrú", "Vitoria-Gasteiz", "Zamora", "Zaragoza"};
        String[] cities = {"Alicante", "Albacete", "Alcalá de Henares", "Alcorcón", "Algeciras", "Badalona", "Barcelona", "Bilbao", "Burgos", "Cádiz", "Cartagena", "Castellón de la Plana", "Córdoba", "Elche", "Fuenlabrada", "Getafe", "Gijón", "Granada", "Huelva", "Jerez de la Frontera", "La Coruña", "Las Palmas de Gran Canaria", "Leganés", "León", "Lleida", "Logroño", "Madrid", "Málaga", "Marbella", "Mataró", "Melilla", "Móstoles", "Murcia", "Ourense", "Oviedo", "Palma", "Pamplona", "Salamanca", "San Sebastián", "Santa Cruz de Tenerife", "Santander", "Sabadell", "Sevilla", "Tarragona", "Terrassa", "Torrejón de Ardoz", "Valencia", "Valladolid", "Vigo", "Vilanova i la Geltrú", "Vitoria-Gasteiz", "Zaragoza"};

        Spinner spinner = findViewById(R.id.addLocation);

        // Adapter for the Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
            Intent intent = new Intent(this, DiscoFeedActivity.class);
            startActivityForResult(intent, POST_ACTIVITY_REQUEST_CODE);
        }


        return true;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Actualizar el texto en el EditText con la fecha seleccionada
                        String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                        addTextBirthday.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}