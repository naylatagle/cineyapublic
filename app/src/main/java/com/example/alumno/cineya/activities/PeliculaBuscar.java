package com.example.alumno.cineya.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.base.BaseActivity;
import com.example.alumno.cineya.adapters.AdaptadorPelicula;
import com.example.alumno.cineya.api.pelicula.PeliculaApiCliente;
import com.example.alumno.cineya.dto.Pelicula;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.util.List;

public class PeliculaBuscar extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.buscar_pelicula);
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //para sacar el titulo
        //setContentView(R.layout.buscar_pelicula);

        showLoading();


        new PeliculaApiCliente(getApplicationContext()).getPeliculas(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView peliP = (ListView) findViewById(R.id.listaPeliculas);
                peliP.setAdapter(new AdaptadorPelicula(getBaseContext(), (List<Pelicula>) body));
                hideLoading();
            };
        });

        /*PeliculaApiCliente.getPeliculas(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView peliP = (ListView) findViewById(R.id.listaPeliculas);
                peliP.setAdapter(new AdaptadorPelicula(getBaseContext(), (List<Pelicula>) body));
                hideLoading();
            }
        });*/

        Bundle extras = getIntent().getExtras();



    }
}
