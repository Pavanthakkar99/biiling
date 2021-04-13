package com.example.smartbilling.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartbilling.R;

public class WaterFragment extends Fragment {
    View view;
    public static WaterFragment getInstance()
    {
        WaterFragment waterFragment=new WaterFragment();
        return waterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (view==null){
        view=inflater.inflate(R.layout.water_activity,container,false);
    }
        return view;
    }
}
