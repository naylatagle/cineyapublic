package com.example.alumno.cineya.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.alumno.cineya.fragments.base.BaseFragment;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.google.gson.Gson;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class CineBuscarFragment extends BaseFragment implements IAdapterClickListener<Cine> {

    public static CineBuscarFragment newInstance(){
        return new CineBuscarFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.buscar_cine;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView cineC = (ListView) view.findViewById(R.id.listaCines);

        showLoading();

        new CineApiCliente (getContext()).getCines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {

                cineC.setAdapter(new AdaptadorCine(getContext(), (List<Cine>) body));
                hideLoading();
            }
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

}