package com.example.smartbilling.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smartbilling.Fragment.ElectricityFragment;
import com.example.smartbilling.Fragment.GasFragment;
import com.example.smartbilling.Fragment.WaterFragment;

public class DetailsAdapter extends FragmentPagerAdapter {
    int tabCount;
    public DetailsAdapter(@NonNull FragmentManager fm,int behavior) {

        super(fm,behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:return new WaterFragment();
            case 1:return new ElectricityFragment();
            case 2:return new GasFragment();
            default:return null;
        }


    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
