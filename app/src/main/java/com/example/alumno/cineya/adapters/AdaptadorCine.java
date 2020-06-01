package com.example.alumno.cineya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.helpers.CineOnClickListener;

import java.util.List;

public class AdaptadorCine extends BaseAdapter {

    private Context context;
    private List<Cine> cineList;

    public AdaptadorCine(){}

    public AdaptadorCine(Context context, List<Cine> cineList){
        this.context = context;
        this.cineList = cineList;
    }


    @Override
    public int getCount() {
            return cineList.size();
    }

    @Override
    public Object getItem(int i) {
        return cineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cineList.get(i).getNombreCine().hashCode();
    }

    //Método para asignar cada elemento del lista_cines a datosCine
    @Override
    public View getView(int i, View cineView, ViewGroup viewgroup){

        cineView = LayoutInflater.from(context).inflate(R.layout.lista_cines, viewgroup, false);
        /*LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_cines, parent, false);*/

        ImageView logoC = (ImageView) cineView.findViewById(R.id.logoCine);

        TextView nombreC = (TextView) cineView.findViewById(R.id.nombreCine);

        TextView direccionC = (TextView) cineView.findViewById(R.id.direccionCine);

        Cine cine = cineList.get(i);

        logoC.setImageResource(cineList.get(i).getLogoCine());
        nombreC.setText(cineList.get(i).getNombreCine());
        direccionC.setText(cineList.get(i).getDireccionCine());

        cineView.setOnClickListener(new CineOnClickListener(context, cine));

        return cineView;
    }



}
