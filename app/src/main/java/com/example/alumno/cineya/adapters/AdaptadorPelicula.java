package com.example.alumno.cineya.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.adapters.base.IAdapterClickListener;
import com.example.alumno.cineya.dto.Pelicula;
import com.example.alumno.cineya.helpers.PeliculaOnClickListener;

import java.util.List;

public class AdaptadorPelicula extends BaseAdapter{

    //Constructor de la clase AdaptadorCine
    Context context;
    List<Pelicula> peliculaList;
    IAdapterClickListener<Pelicula> mListener;

    public AdaptadorPelicula(Context context, List<Pelicula> peliculaList, IAdapterClickListener<Pelicula> listener){
        this.context = context;
        this.peliculaList = peliculaList;
        this.mListener = listener;
    }

    public AdaptadorPelicula(Context context, List<Pelicula> peliculaList){
        this.context = context;
        this.peliculaList = peliculaList;
    }


    @Override
    public int getCount() {
        return peliculaList.size();
    }

    @Override
    public Object getItem(int i) {
        return peliculaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return peliculaList.get(i).getNombrePelicula().hashCode();
    }

    //Método para asignar cada elemento del lista_cines a datosCine
    @Override
    public View getView(final int i, View peliculaView, ViewGroup viewgroup){
        peliculaView = LayoutInflater.from(context).inflate(R.layout.lista_peliculas, viewgroup, false);
//        LayoutInflater inflater = context.getLayoutInflater();
//        View item = inflater.inflate(R.layout.lista_peliculas, parent, false);

        ImageView LogoPelicula = (ImageView) peliculaView.findViewById(R.id.logoPelicula);
        TextView NombrePelicula = (TextView) peliculaView.findViewById(R.id.nombrePelicula);
        TextView GeneroPelicula = (TextView) peliculaView.findViewById(R.id.generoPelicula);

        final Pelicula pelicula = peliculaList.get(i);
        try {
            Drawable draw = ResourcesCompat.getDrawable(context.getResources(), peliculaList.get(i).getLogoPelicula(), context.getTheme());
            LogoPelicula.setImageDrawable(draw);
        } catch (Resources.NotFoundException e){

        }
        NombrePelicula.setText(peliculaList.get(i).getNombrePelicula());

        GeneroPelicula.setText(peliculaList.get(i).getGeneroPelicula());

        if(mListener!=null) {

            peliculaView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null)
                        mListener.onClick(pelicula);
                }
            });
        } else {
            peliculaView.setOnClickListener(new PeliculaOnClickListener(context, pelicula));
        }
        /*item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsPelicula.class);
                intent.putExtra("pelicula", new Gson().toJson(peliculaList.get(position)));
                context.startActivity(intent);
            }
        });*/
        return peliculaView;
    }



}
