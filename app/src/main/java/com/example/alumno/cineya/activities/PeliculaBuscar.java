package com.example.alumno.cineya.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.adapters.AdaptadorCine;
import com.example.alumno.cineya.adapters.AdaptadorPelicula;
import com.example.alumno.cineya.api.cine.CineApiCliente;
import com.example.alumno.cineya.api.pelicula.PeliculaApiCliente;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.dto.Pelicula;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class PeliculaBuscar extends Activity {

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //para sacar el titulo
        setContentView(R.layout.buscar_pelicula);

        PeliculaApiCliente.init(getApplicationContext());

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Pelicula",
                "Obteniendo la información", true, false);
        PeliculaApiCliente.getPeliculas(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView peliP = (ListView) findViewById(R.id.listaPeliculas);
                peliP.setAdapter(new AdaptadorPelicula(getBaseContext(), (List<Pelicula>) body));
                progressDialog.dismiss();
            }
        });

        Bundle extras = getIntent().getExtras();



    }
}
