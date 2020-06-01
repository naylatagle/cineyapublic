package com.example.alumno.cineya.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.adapters.AdaptadorCine;
import com.example.alumno.cineya.adapters.AdaptadorCineInfo;
import com.example.alumno.cineya.api.cine.CineApiCliente;
import com.example.alumno.cineya.api.cine.info.CineInfoApiCliente;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.dto.CineInfo;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.google.gson.Gson;

import java.util.List;


public class DetailsCine extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cine_details);

        CineInfoApiCliente.init(getApplicationContext());

        final ProgressDialog progressDialog = ProgressDialog.show(this, "CineInfo",
                "Obteniendo la información", true, false);
        CineInfoApiCliente.getInfoCines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView cineC = (ListView) findViewById(R.id.listaCineInfo);
                cineC.setAdapter(new AdaptadorCineInfo(getBaseContext(), (List<CineInfo>) body));
                progressDialog.dismiss();
            }
        });

        Bundle extras = getIntent().getExtras();

        //titulo del cine
        String cineSerializado = getIntent().getExtras().getString("cine");
        Cine cine = new Gson().fromJson(cineSerializado, Cine.class);

//        String cineInfoSerializado = getIntent().getExtras().getString("cineInfo");
//        CineInfo cineInfo = new Gson().fromJson(cineInfoSerializado, CineInfo.class);
        //datos de la listview
//        //
//        TextView titulo = (TextView) findViewById(R.id.nombre_cine);
//        titulo.setText(cine.getNombreCine());
//
//        TextView opcionP = (TextView) findViewById(R.id.opcionPelicula);
//        opcionP.setText(cineInfo.getOpcionPelicula());
//        TextView horarioP = (TextView) findViewById(R.id.horarioPelicula);
//        opcionP.setText(cineInfo.getHorarioPelicula());

    }
}
