package com.example.alumno.cineya.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.base.BaseActivity;
import com.example.alumno.cineya.adapters.AdaptadorCine;
import com.example.alumno.cineya.api.cine.CineApiCliente;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.util.List;

public class CineBuscar extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.buscar_cine);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoading();

        new CineApiCliente(getApplicationContext()).getCines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {
                ListView cineC = (ListView) findViewById(R.id.listaCines);
                cineC.setAdapter(new AdaptadorCine(getBaseContext(), (List<Cine>) body));
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
