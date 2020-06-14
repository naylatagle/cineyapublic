package com.example.alumno.cineya.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.alumno.cineya.activities.DetailsCine;
import com.example.alumno.cineya.dto.Favorito;
import com.google.gson.Gson;

public class FavoritoOnClickListener implements View.OnClickListener {
    private Context context;
    private Favorito favorito;

    public FavoritoOnClickListener(Context context, Favorito favorito) {
        this.context = context;
        this.favorito = favorito;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, favorito.getNombre(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailsCine.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String cineSerializado = new Gson().toJson(favorito);
        intent.putExtra("cine", cineSerializado);
        context.startActivity(intent);
    }

}
