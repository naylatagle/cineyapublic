package com.example.alumno.cineya.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.activities.base.BaseActivity;
import com.example.alumno.cineya.adapters.PageFragmentsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,  NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView mBottomNavigationView;
    ViewPager mViewPager;
    PageFragmentsAdapter mAdapterFragment;

    ActionBarDrawerToggle mBarDrawerToggle;
    DrawerLayout mDrawerLayout;
    NavigationView mMainNavigationView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.drawer_main_layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mMainNavigationView = findViewById(R.id.navigation_view);
        mBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer_toggle, R.string.close_drawer_toggle);
        mBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        mBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mBarDrawerToggle);
        mBarDrawerToggle.syncState();

        //mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.com_facebook_button_background_color_pressed));


        mMainNavigationView.setNavigationItemSelectedListener(this);

        mBottomNavigationView = findViewById(R.id.navigation_bottom);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mAdapterFragment = new PageFragmentsAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapterFragment);

    }

    private void logout (){
        //FirebaseAuth.getInstance().signOut();
        SharedPreferences preferences =getSharedPreferences("CineYa", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        //Log.d("Prueba", "editor");

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //finish();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buttom_bar_cines:
                mViewPager.setCurrentItem(0, true);
                break;
            case R.id.action_buttom_bar_peliculas:
                mViewPager.setCurrentItem(1, true);
                mDrawerLayout.closeDrawer(Gravity.LEFT, true);
                break;
            case R.id.action_buttom_bar_ubicacion:
                mViewPager.setCurrentItem(2, true);
                mDrawerLayout.closeDrawer(Gravity.LEFT, true);
                break;
            case R.id.action_buttom_bar_logout:
                logout();
                break;
            case R.id.action_buttom_bar_favoritos:
                Intent intent = new Intent(MainActivity.this, Favoritos.class);
                mDrawerLayout.closeDrawer(Gravity.LEFT, true);
                break;
        }
        changeDrawer(true);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            changeDrawer();
        }
        return super.onOptionsItemSelected(item);
    }
    private void changeDrawer(){
        changeDrawer(false);
    }

    private void changeDrawer(boolean isClose){
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT, true);
        }else if(!isClose){
            mDrawerLayout.openDrawer(Gravity.LEFT, true);
        }
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
