package com.example.alumno.cineya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.dto.Favorito;
import com.example.alumno.cineya.helpers.FavoritoOnClickListener;

import java.util.List;

public class AdaptadorFavorito extends BaseAdapter {

    private Context context;
    private List<Favorito> favoritoList;

    public AdaptadorFavorito(){}

    public AdaptadorFavorito(Context context, List<Favorito> favoritoList){
        this.context = context;
        this.favoritoList = favoritoList;
    }


    @Override
    public int getCount() {
        return favoritoList.size();
    }

    @Override
    public Object getItem(int i) {
        return favoritoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return favoritoList.get(i).getNombre().hashCode();
    }

    //Método para asignar cada elemento del lista_cines a datosCine
    @Override
    public View getView(int i, View favoritoView, ViewGroup viewgroup){

        favoritoView = LayoutInflater.from(context).inflate(R.layout.activity_favoritos, viewgroup, false);

        ImageView logoC = (ImageView) favoritoView.findViewById(R.id.logoCine);

        TextView nombreC = (TextView) favoritoView.findViewById(R.id.nombreCine);

        TextView direccionC = (TextView) favoritoView.findViewById(R.id.direccionCine);

        Favorito favorito = favoritoList.get(i);

        logoC.setImageResource(favoritoList.get(i).getLogoCine());
        nombreC.setText(favoritoList.get(i).getNombre());
        direccionC.setText(favoritoList.get(i).getDireccion());

        favoritoView.setOnClickListener(new FavoritoOnClickListener(context, favorito));

        return favoritoView;
    }

}
