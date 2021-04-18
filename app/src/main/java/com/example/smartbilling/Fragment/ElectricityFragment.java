package com.example.smartbilling.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.Adapter.MeterAdapter;
import com.example.smartbilling.FormActivity;
import com.example.smartbilling.Model.MeterBill;
import com.example.smartbilling.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectricityFragment extends Fragment {
    View view;
    ImageView image;
    RecyclerView meterRecyclerView;
    MeterAdapter meterAdapter;
    DatabaseReference myRef;
    FirebaseDatabase database;
    List<MeterBill>meterBills;
    FirebaseAuth auth;

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
            meterRecyclerView=view.findViewById(R.id.meterRecyclerView);
            database=FirebaseDatabase.getInstance();
            auth= FirebaseAuth.getInstance();

            String userId=auth.getCurrentUser().getUid();

            final RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
            myRef = database.getReference().child("MetersBillList").child(userId);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    try{
                    Map<String, Object> objectMap = (HashMap<String, Object>)
                            dataSnapshot.getValue();
                    meterBills = new ArrayList<>();
                    for (Object obj : objectMap.values()) {
                        if (obj instanceof Map) {
                            Map<String, Object> mapObj = (Map<String, Object>) obj;
                            MeterBill meterBill=new MeterBill();
                            meterBill.setBillDate((String) mapObj.get("billDate"));
                            meterBill.setUsermeterID((String) mapObj.get("usermeterID"));
                            meterBill.setCurrentunit((String) mapObj.get("currentunit"));
                            meterBill.setMeantense((String) mapObj.get("meantense"));
                            meterBill.setSocityName((String) mapObj.get("socityName"));
                            meterBill.setPreviousunit((String) mapObj.get("previousunit"));
                            meterBill.setTotalbill((String) mapObj.get("totalbill"));
                            meterBill.setUsedunit((String) mapObj.get("usedunit"));
                            meterBill.setUsername((String) mapObj.get("userName"));

                            meterBills.add(meterBill);
                            Log.d("Value is: ", " " + mapObj);
                        }
                    }

                    meterAdapter = new MeterAdapter(meterBills,getContext());
                    meterRecyclerView.setAdapter(meterAdapter);
                    meterRecyclerView.setLayoutManager(mlayoutManager);
                    }catch (Exception e)
                    {

                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });
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
