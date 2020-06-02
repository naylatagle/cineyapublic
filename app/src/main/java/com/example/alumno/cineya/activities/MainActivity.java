package com.example.alumno.cineya.activities;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.base.BaseActivity;
import com.example.alumno.cineya.adapters.PageFragmentsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView mBottomNavigationView;
    ViewPager mViewPager;
    PageFragmentsAdapter mAdapterFragment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBottomNavigationView = findViewById(R.id.navigation_bottom);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mAdapterFragment = new PageFragmentsAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapterFragment);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_buttom_bar_cines:
                mViewPager.setCurrentItem(0, true);
                break;
            case R.id.action_buttom_bar_peliculas:
                mViewPager.setCurrentItem(1, true);
                break;
            case R.id.action_buttom_bar_ubicacion:
                mViewPager.setCurrentItem(2, true);
                break;
        }
        return false;
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        /*CineApiCliente.init(getApplicationContext());
//
//        final ProgressDialog progressDialog = ProgressDialog.show(this, "Cine",
//                "Obteniendo la información", true, false);
//        CineApiCliente.getCines(new OnSuccessCallback() {
//            @Override
//            public void execute(Object body) {
//                ListView cineC = (ListView) findViewById(R.id.listaCines); //El listview
//                //Le asigno el adapter, al cual le paso el contexto y la lista de pizzas que vino
//                cineC.setAdapter(new AdaptadorCine(getBaseContext(), (List<Cine>) body));
//                //Saco el Progress Dialog de la pantalla
//                progressDialog.dismiss();
//            }
//        });*/
//
//        Button botonCine = (Button) findViewById(R.id.button_cine);
//        botonCine.setOnClickListener(new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view){
//            Intent buscarCineIntent = new Intent(MainActivity.this, CineBuscar.class);
//            startActivity(buscarCineIntent);
//        }
//        });
//
//        Button botonPelicula = (Button) findViewById(R.id.button_peliculas);
//        botonPelicula.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view){
//                Intent buscarPeliculaIntent = new Intent(MainActivity.this, PeliculaBuscar.class);
//                startActivity(buscarPeliculaIntent);
//            }
//        });
//
//        Button botonUbicacion = (Button) findViewById(R.id.button_ubicacion);
//        botonUbicacion.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view){
//                Intent buscarUbicacionIntent = new Intent(MainActivity.this, UbicacionBuscar.class);
//                startActivity(buscarUbicacionIntent);
//            }
//        });
//}
}
