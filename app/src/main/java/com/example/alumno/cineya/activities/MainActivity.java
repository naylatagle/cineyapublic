package com.example.alumno.cineya.activities;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alumno.cineya.R;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*CineApiCliente.init(getApplicationContext());

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Cine",
                "Obteniendo la información", true, false);
        CineApiCliente.getCines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView cineC = (ListView) findViewById(R.id.listaCines); //El listview
                //Le asigno el adapter, al cual le paso el contexto y la lista de pizzas que vino
                cineC.setAdapter(new AdaptadorCine(getBaseContext(), (List<Cine>) body));
                //Saco el Progress Dialog de la pantalla
                progressDialog.dismiss();
            }
        });*/

        Button botonCine = (Button) findViewById(R.id.button_cine);
        botonCine.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view){
            Intent buscarCineIntent = new Intent(MainActivity.this, CineBuscar.class);
            startActivity(buscarCineIntent);
        }
        });

        Button botonPelicula = (Button) findViewById(R.id.button_peliculas);
        botonPelicula.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent buscarPeliculaIntent = new Intent(MainActivity.this, PeliculaBuscar.class);
                startActivity(buscarPeliculaIntent);
            }
        });

        Button botonUbicacion = (Button) findViewById(R.id.button_ubicacion);
        botonUbicacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent buscarUbicacionIntent = new Intent(MainActivity.this, UbicacionBuscar.class);
                startActivity(buscarUbicacionIntent);
            }
        });
}
}
