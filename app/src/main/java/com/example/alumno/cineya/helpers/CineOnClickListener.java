package com.example.alumno.cineya.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.alumno.cineya.activities.CineBuscar;
import com.example.alumno.cineya.activities.DetailsCine;
import com.example.alumno.cineya.dto.Cine;
import com.google.gson.Gson;

/**
 * Created by Nayla on 29/11/2017.
 */

public class CineOnClickListener implements View.OnClickListener {

    private Context context;
    private Cine cine;

    public CineOnClickListener(Context context, Cine cine) {
        this.context = context;
        this.cine = cine;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, cine.getNombreCine(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailsCine.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String cineSerializado = new Gson().toJson(cine);
        intent.putExtra("cine", cineSerializado);
        context.startActivity(intent);
    }
}
