package com.example.alumno.cineya.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.health.ServiceHealthStats;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.Favoritos;
import com.example.alumno.cineya.adapters.AdaptadorCine;
import com.example.alumno.cineya.adapters.base.IAdapterClickListener;
import com.example.alumno.cineya.api.cine.CineApiCliente;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.dto.User;
import com.example.alumno.cineya.fragments.base.BaseFragment;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.google.gson.Gson;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class CineBuscarFragment extends BaseFragment implements IAdapterClickListener<Cine>, AdaptadorCine.CineClickListener{

    private SharedPreferences sharedPreferences;

    public static CineBuscarFragment newInstance(){
        return new CineBuscarFragment();
    }
    private AdaptadorCine mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.buscar_cine;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        final ListView cineC = (ListView) view.findViewById(R.id.listaCines);

        showLoading();
        mAdapter = new AdaptadorCine(getContext());
        mAdapter.setClickListener(this);
        cineC.setAdapter(mAdapter);
        new CineApiCliente (getContext()).getCines(body -> {
           mAdapter.setList(body);
            hideLoading();
        });;

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

    @Override
    public void onClick(Cine object) {
        Toast.makeText(getApplicationContext(), object.getNombre(), Toast.LENGTH_SHORT).show();
        if(getActivity()!=null) {
            Intent intent = new Intent(getActivity(), Favoritos.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            String cineSerializado = new Gson().toJson(object);
            intent.putExtra("cine", cineSerializado);
            getContext().startActivity(intent);
        }
    }


    @Override
    public void addFavorito(/*User user,*/ Cine cine, int position) {
        //TODO metodo de la api para mandar al servidor el cine favorito.
        //TODO cambio el icono y actualizo la lista a contirnuacion

        sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        Long userID = sharedPreferences.getLong("userID", 0l);

        new CineApiCliente(getApplicationContext()).addFavorite(new OnSuccessCallback<Cine>() {
            @Override
            public void execute(Cine body) {
                if(body!=null && mAdapter != null){
                    cine.setEsFavorito(true);
                    mAdapter.changePosition(cine, position);
                }
            }
        }, userID,cine.getIdCine());


    }

    /*@Override
    public void removeFavorito(Cine cine, int position) {

    }*/
}