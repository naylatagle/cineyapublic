package com.example.alumno.cineya.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.base.BaseActivity;
import com.example.alumno.cineya.adapters.AdaptadorCine;
import com.example.alumno.cineya.api.cine.CineApiCliente;
import com.example.alumno.cineya.api.favorito.FavoritoApiCliente;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.util.List;

public class Favoritos extends BaseActivity implements AdaptadorCine.CineClickListener{

    private AdaptadorCine mAdapter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.buscar_cine);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long userId = getApplicationContext()
                .getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE)
                .getLong("userId", 0);

        showLoading();
        ListView favoritoF = (ListView) findViewById(R.id.listaCines);
        mAdapter = new AdaptadorCine(getApplicationContext());
        mAdapter.setClickListener(this);
        favoritoF.setAdapter(mAdapter);
//        new FavoritoApiCliente(getApplicationContext()).getFavoritos(new OnSuccessCallback() {
//            @Override
//            public void execute(Object body) {
//                mAdapter.setList((List<Cine>) body);
//                hideLoading();
//            }
//        });
//
        new CineApiCliente(getApplicationContext()).getFavorites(new OnSuccessCallback<List<Cine>>() {
            @Override
            public void execute(List<Cine> body) {
                mAdapter.setList((List<Cine>) body);
                hideLoading();
            }
        }, userId);

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
   */
    }

    @Override
    public void addFavorite(Cine cine, int position) {
        //TODO metodo de la api para mandar al servidor que lo saque como favorito.
        //TODO cambio el icono y actualizo la lista a contirnuacion

        if(cine!=null && mAdapter != null){
            cine.setEsFavorito(!cine.isEsFavorito());
            mAdapter.removeFavorite(cine, position);
        }
    }
}
