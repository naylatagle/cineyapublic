package com.example.alumno.cineya.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alumno.cineya.control.IFragmentControl;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {

    IFragmentControl mListener;
    protected abstract int getLayout();

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            mListener = (IFragmentControl) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
//        saveOldTitle();
//        ButterKnife.bindPayMe(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void showLoading(){
        if(mListener!=null)
            mListener.showLoading();
    }

    protected void hideLoading(){
        if(mListener!=null)
            mListener.hideLoading();
    }

}
