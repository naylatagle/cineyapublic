package com.example.alumno.cineya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.dto.CineInfo;
import com.example.alumno.cineya.helpers.CineInfoOnClickListener;

import java.util.List;

public class AdaptadorCineInfo extends BaseAdapter {

    //Constructor de la clase AdaptadorCine
    private Context context;
    private List<CineInfo> cineInfoList;

    public AdaptadorCineInfo(){}

    public AdaptadorCineInfo(Context context, List<CineInfo> cineInfoList){
        this.context = context;
        this.cineInfoList = cineInfoList;
    }


    @Override
    public int getCount() {
        return cineInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return cineInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cineInfoList.get(i).getOpcionPelicula().hashCode();
    }

    @Override
    public View getView(final int i, View cineInfoView, ViewGroup viewgroup){
        cineInfoView = LayoutInflater.from(context).inflate(R.layout.cine_info, viewgroup, false);
        //LayoutInflater inflater = context.getLayoutInflater();
        //View item = inflater.inflate(R.layout.cine_info, viewgroup, false);

        TextView OpcionPelicula = (TextView) cineInfoView.findViewById(R.id.opcionPelicula);
        TextView HorarioPelicula = (TextView) cineInfoView.findViewById(R.id.horarioPelicula);

        CineInfo cineInfo = cineInfoList.get(i);

        OpcionPelicula.setText(cineInfoList.get(i).getOpcionPelicula());
        HorarioPelicula.setText(cineInfoList.get(i).getHorarioPelicula());

        cineInfoView.setOnClickListener(new CineInfoOnClickListener(context, cineInfo));

        return cineInfoView;
    }



}
