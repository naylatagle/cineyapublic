package com.example.alumno.cineya.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.alumno.cineya.fragments.CineBuscarFragment;
import com.example.alumno.cineya.fragments.PeliculaBuscarFragment;
import com.example.alumno.cineya.fragments.UbicacionBuscarFragment;

public class PageFragmentsAdapter extends FragmentPagerAdapter {

    public PageFragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return CineBuscarFragment.newInstance();
            case 1:
                return PeliculaBuscarFragment.newInstance();
            case 2:
                return UbicacionBuscarFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
