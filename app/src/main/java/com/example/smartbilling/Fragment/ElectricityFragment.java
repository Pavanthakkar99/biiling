package com.example.smartbilling.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartbilling.FormActivity;
import com.example.smartbilling.R;

public class ElectricityFragment extends Fragment {
    View view;
    ImageView image;
    public static ElectricityFragment getInstance() {
        ElectricityFragment electricityFragment = new ElectricityFragment();
        return electricityFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.electricity_activity,container,false);
            image=view.findViewById(R.id.imageView5);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(), FormActivity.class);
                    startActivity(intent);
                    
                }
            });
        }

        return view;

    }
}
