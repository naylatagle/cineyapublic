package com.example.alumno.cineya.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.alumno.cineya.activities.PeliculaBuscar;
import com.example.alumno.cineya.dto.Pelicula;
import com.google.gson.Gson;

/**
 * Created by Nayla on 29/11/2017.
 */

public class PeliculaOnClickListener implements View.OnClickListener {

    private Context context;
    private Pelicula pelicula;

    public PeliculaOnClickListener(Context context, Pelicula pelicula) {

        this.context = context;
        this.pelicula = pelicula;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, pelicula.getNombrePelicula(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, PeliculaBuscar.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        String cineSerializado = new Gson().toJson(pelicula);
        intent.putExtra("pelicula", cineSerializado);
        context.startActivity(intent);
    }
}
