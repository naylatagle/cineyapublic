package com.example.alumno.cineya.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.fragments.base.BaseFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionBuscarFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

    public static UbicacionBuscarFragment newInstance() {
        return new UbicacionBuscarFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.buscar_ubicacion;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);

        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                        .position(coordenadas)
                        .title("Estás acá")
                /*.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))*/);

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.603245, -58.410653))
                .title("Hoyts Abasto")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.588912, -58.384112))
                .title("Atlas Patio Bullrich")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.589016, -58.393860))
                .title("Village Recoleta")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.556660, -58.461653))
                .title("Cinema City General Paz")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.553887, -58.453211))
                .title("Showcase Belgrano")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.560472, -58.456301))
                .title("Multiplex Belgrano")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.555937, -58.461768))
                .title("Arte Multiplex Belgrano")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.615742, -58.428898))
                .title("Cinemark Caballito")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.618243, -58.437239))
                .title("Village Caballito")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.603958, -58.388911))
                .title("Premier")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.604147, -58.386936))
                .title("Lorca")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.602423, -58.377796))
                .title("Monumental Lavalle")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.608956, -58.389628))
                .title("Espacio INCAA Km 0 Gaumont")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.602397, -58.377806))
                .title("Monumental Lavalle 4D")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.629300, -58.462361))
                .title("Atlas Flores")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.586414, -58.410146))
                .title("Cinemark Palermo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.575244, -58.404319))
                .title("Atlas Alcorta")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.621116, -58.364928))
                .title("Cinemark Puerto Madero")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.611690, -58.517026))
                .title("Cinema Devoto")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.546169, -58.488341))
                .title("Hoyts DOT")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.547000, -58.488287))
                .title("Hoyts Dot Premium")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        marcador = mMap.addMarker (new MarkerOptions()
                .position(new LatLng(-34.601810, -58.494763))
                .title("CPM Cinemas Del Parque Shopping")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.claqueta)));

        mMap.animateCamera(miUbicacion);

    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }

    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(getContext(), "NO TENES PERMISOS PARA EL GPS", Toast.LENGTH_SHORT).show();
            return;
        }
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
