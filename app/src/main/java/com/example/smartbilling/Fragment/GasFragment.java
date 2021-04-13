package com.example.smartbilling.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartbilling.R;

public class GasFragment extends Fragment {
    View view;
    public static GasFragment getInstance() {
        GasFragment gasFragment = new GasFragment();
        return gasFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.gas_activity,container,false);
        }
        return view;
    }
}
