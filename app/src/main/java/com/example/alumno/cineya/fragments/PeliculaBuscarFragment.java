package com.example.alumno.cineya.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.PeliculaBuscar;
import com.example.alumno.cineya.adapters.AdaptadorPelicula;
import com.example.alumno.cineya.adapters.base.IAdapterClickListener;
import com.example.alumno.cineya.api.pelicula.PeliculaApiCliente;
import com.example.alumno.cineya.dto.Pelicula;
import com.example.alumno.cineya.fragments.base.BaseFragment;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.google.gson.Gson;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PeliculaBuscarFragment extends BaseFragment implements IAdapterClickListener<Pelicula> {

    public static PeliculaBuscarFragment newInstance(){
        return new PeliculaBuscarFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.buscar_pelicula;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView peliP = (ListView) view.findViewById(R.id.listaPeliculas);

        new PeliculaApiCliente (getContext()).getPeliculas(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {

                peliP.setAdapter(new AdaptadorPelicula(getContext(), (List<Pelicula>) body, PeliculaBuscarFragment.this));
                hideLoading();
            }
        });

        showLoading();
    }

    @Override
    public void onClick(Pelicula object) {
        Toast.makeText(getApplicationContext(), object.getNombrePelicula(), Toast.LENGTH_SHORT).show();
        if(getActivity()!=null) {
            Intent intent = new Intent(getActivity(), PeliculaBuscar.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            String cineSerializado = new Gson().toJson(object);
            intent.putExtra("pelicula", cineSerializado);
            getContext().startActivity(intent);
        }
    }
}
