package com.example.discoraterjorge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.provider.MediaStore;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class DiscoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imgLastGalleryPhoto;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco);

        imgLastGalleryPhoto = findViewById(R.id.imgLastGalleryPhotoDisco);
        Button uploadButton = findViewById(R.id.btnUploadDisco);
        Button cancelButton = findViewById(R.id.btnCancelDisco);

        // This is to open the gallery and select the image to upload
        // Estaria bien mirar comom hacer para seleccionar mas de una imagen
        imgLastGalleryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        // Cancel the post and go back
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


        // Upload the post
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectDataAndReturnResult();
            }
        });
    }


    // Method to open the gallery when the button is clicked. It starts an activity which returns the URI of the image selected
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    // This is the result of the activity of the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Obtener la URI de la imagen seleccionada
            selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Setear la imagen seleccionada al ImageView
                imgLastGalleryPhoto.setImageURI(selectedImageUri);
                imgLastGalleryPhoto.setPadding(0,5,0,5);
            }
        }
    }

    private void collectDataAndReturnResult() {
        EditText etEvent = findViewById(R.id.etEvent);
        EditText etTicketPrice = findViewById(R.id.etTicketPrice);
        EditText etCocktailPrice = findViewById(R.id.etDrinkPrice);
        EditText etBeerPrice = findViewById(R.id.etBeerPrice);
        EditText etTequilaPrice = findViewById(R.id.etFoodPrice);

        String event = etEvent.getText().toString();
        String ticketPrice = etTicketPrice.getText().toString();
        String cocktailPrice = etCocktailPrice.getText().toString();
        String beerPrice = etBeerPrice.getText().toString();
        String tequilaPrice = etTequilaPrice.getText().toString();

        // Check that the experience field is not empty. The rest can be
        if (event.trim().isEmpty()) {
            // Si el campo de experiencia está vacío, muestra una alerta y no continúes
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Empty Field");
            builder.setMessage("Please, tell us about your event. Event Field cannot be empty");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return;
        }

        if (ticketPrice.trim().isEmpty()) {
            // Si el campo de experiencia está vacío, muestra una alerta y no continúes
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Empty Field");
            builder.setMessage("Please, fill Ticket Price field. Ticket Price Field cannot be empty");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return;
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra("event", event);
        returnIntent.putExtra("ticketPrice", ticketPrice);
        returnIntent.putExtra("cocktailPrice", cocktailPrice);
        returnIntent.putExtra("beerPrice", beerPrice);
        returnIntent.putExtra("tequilaPrice", tequilaPrice);
        if (selectedImageUri != null) {
            Log.d("PostActivity", "URI de imagen seleccionada: " + selectedImageUri.toString());
            returnIntent.putExtra("imageUri", selectedImageUri.toString());
        }

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}