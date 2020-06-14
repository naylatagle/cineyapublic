package com.example.alumno.cineya.activities;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.base.BaseActivity;
import com.example.alumno.cineya.adapters.AdaptadorFavorito;
import com.example.alumno.cineya.api.favorito.FavoritoApiCliente;
import com.example.alumno.cineya.dto.Favorito;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.util.List;

public class Favoritos extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_favoritos);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoading();

        new FavoritoApiCliente(getApplicationContext()).getFavoritos(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView favoritoF = (ListView) findViewById(R.id.listaCines);
                favoritoF.setAdapter((ListAdapter) new AdaptadorFavorito(getBaseContext(), (List<Favorito>) body));
                hideLoading();
            }
        });;

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

}
