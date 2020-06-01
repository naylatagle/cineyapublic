package com.example.alumno.cineya.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.example.alumno.cineya.R;
import com.example.alumno.cineya.api.cine.CineApiCliente;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.adapters.AdaptadorCine;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


public class CineBuscar extends Activity {

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE); //para sacar el titulo*/
        setContentView(R.layout.buscar_cine);

        CineApiCliente.init(getApplicationContext());

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Cine",
                "Obteniendo la información", true, false);
        CineApiCliente.getCines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView cineC = (ListView) findViewById(R.id.listaCines);
                cineC.setAdapter(new AdaptadorCine(getBaseContext(), (List<Cine>) body));
                progressDialog.dismiss();
            }
        });

        Bundle extras = getIntent().getExtras();

      /*  String cineSerializado = extras.getString("cine");
        Cine cine = new Gson().fromJson(cineSerializado, Cine.class);



        ImageView logoC = (ImageView) findViewById(R.id.logoCine);
        logoC.setImageResource(cine.getLogoCine());
        TextView nombreC = (TextView) findViewById(R.id.nombreCine);
        nombreC.setText(cine.getNombreCine());
        TextView direccionC = (TextView) findViewById(R.id.direccionCine);
        direccionC.setText(cine.getDireccionCine());
*/
      /*  List<Cine> cineList = Arrays.asList(datosCine);
        AdaptadorCine adaptador = new AdaptadorCine(this, cineList);
        ListView listaCines = (ListView) findViewById(R.id.listaCines);
        listaCines.setAdapter(adaptador);
   */ }
}