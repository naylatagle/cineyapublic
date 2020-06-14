package com.example.alumno.cineya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.helpers.CineOnClickListener;

import java.util.List;

public class AdaptadorCine extends BaseAdapter {

    private Context context;
    private List<Cine> cineList;
    private CineClickListener mClickListener;
    public AdaptadorCine(Context context){
        this.context = context;
    }

    public AdaptadorCine(Context context, List<Cine> cineList){
        this.context = context;
        this.cineList = cineList;
    }

    public void setClickListener(CineClickListener listener){
        this.mClickListener = listener;
    }

    public void setList(List<Cine> cineList){
        this.cineList = cineList;
        notifyDataSetChanged();
    }

    public void changePosition(Cine cine, int position){
        if(cineList!=null && cine !=null && position >=0 && position < cineList.size()){
            cineList.set(position, cine);
            this.notifyDataSetChanged();
        }
    }

    public void removeFavorite(Cine cine, int position){
        if(cineList!=null && cine !=null && position >=0 && position < cineList.size()){
            cineList.remove(position);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
            return cineList == null ? 0 : cineList.size();
    }

    @Override
    public Object getItem(int i) {
        return cineList.get(i);
    }

    @Override
    public long getItemId(int i) { return cineList.get(i).getNombre().hashCode(); }

    //Método para asignar cada elemento del lista_cines a datosCine
    @Override
    public View getView(int i, View cineView, ViewGroup viewgroup){

        cineView = LayoutInflater.from(context).inflate(R.layout.lista_cines, viewgroup, false);
        /*LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_cines, parent, false);*/

        ImageView logoC = (ImageView) cineView.findViewById(R.id.logoCine);

        TextView nombreC = (TextView) cineView.findViewById(R.id.nombreCine);

        TextView direccionC = (TextView) cineView.findViewById(R.id.direccionCine);

        ImageButton isFavorite = (ImageButton) cineView.findViewById(R.id.is_favorite);

        Cine cine = cineList.get(i);

        logoC.setImageResource(cine.getLogoCine());
        nombreC.setText(cine.getNombre());
        direccionC.setText(cine.getDireccion());
        isFavorite.setImageResource(cine.isEsFavorito() ? R.drawable.ic_favorite_ok : R.drawable.ic_favorite_no);

        isFavorite.setOnClickListener(getClickFavorite(cine, i));

        cineView.setOnClickListener(new CineOnClickListener(context, cine));

        return cineView;
    }

    private View.OnClickListener getClickFavorite(final Cine cine, final int position) {
        return v -> {
            if(mClickListener!=null){
                mClickListener.addFavorite(cine, position);
            }
        };
    }

    public interface CineClickListener{
        void addFavorite(Cine cine, final int position);
    }

}
