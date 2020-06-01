package com.example.alumno.cineya.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.adapters.AdaptadorCineInfo;
import com.example.alumno.cineya.adapters.AdaptadorPeliculaInfo;
import com.example.alumno.cineya.api.cine.info.CineInfoApiCliente;
import com.example.alumno.cineya.api.pelicula.info.PeliculaInfoApiCliente;
import com.example.alumno.cineya.dto.CineInfo;
import com.example.alumno.cineya.dto.Pelicula;
import com.example.alumno.cineya.dto.PeliculaInfo;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.google.gson.Gson;

import java.util.List;

public class DetailsPelicula extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pelicula_details);

        PeliculaInfoApiCliente.init(getApplicationContext());

        final ProgressDialog progressDialog = ProgressDialog.show(this, "PeliculaInfo",
                "Obteniendo la información", true, false);
        PeliculaInfoApiCliente.getInfoPeliculas(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView peliI = (ListView) findViewById(R.id.listaPeliculaInfo);
                peliI.setAdapter(new AdaptadorPeliculaInfo(getBaseContext(), (List<PeliculaInfo>) body));
                progressDialog.dismiss();
            }
        });

        Bundle extras = getIntent().getExtras();

        //titulo peli
        String peliculaSerializada = getIntent().getExtras().getString("pelicula");
        Pelicula pelicula = new Gson().fromJson(peliculaSerializada, Pelicula.class);

//        //datos listview
//            String peliculaInfoSerializada = getIntent().getExtras().getString("peliculaInfo");
//        PeliculaInfo peliculaInfo = new Gson().fromJson(peliculaInfoSerializada, PeliculaInfo.class);
//
//        TextView titulo = (TextView) findViewById(R.id.nombre_pelicula);
//        titulo.setText(pelicula.getNombrePelicula());
//
//        TextView opcionC = (TextView) findViewById(R.id.opcionCine);
//        opcionC.setText(peliculaInfo.getOpcionCine());
//        TextView peliculaC = (TextView) findViewById(R.id.peliculaCine);
//        peliculaC.setText(peliculaInfo.getPeliculaCine());

        /*List<PeliculaInfo> peliculaInfoList = Arrays.asList(datosPeliculaInfo);
        AdaptadorPeliculaInfo adaptador = new AdaptadorPeliculaInfo(this, peliculaInfoList);
        ListView listaPeliculaInfo = (ListView) findViewById(R.id.listaPeliculaInfo);
        listaPeliculaInfo.setAdapter(adaptador);*/
    }
}
