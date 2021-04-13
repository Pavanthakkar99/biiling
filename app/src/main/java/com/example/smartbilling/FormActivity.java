package com.example.smartbilling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartbilling.Model.MeterReading;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FormActivity extends AppCompatActivity {

    EditText meterId,contactNo,readingPoint;
    FirebaseAuth auth;
    Button submitBtn;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        meterId=findViewById(R.id.editTextTextPersonName);
        contactNo=findViewById(R.id.editTextTextPersonName2);
        readingPoint=findViewById(R.id.editTextTextPersonName3);
        String meterId1=localStorage.getInstance(getApplicationContext()).getMeterId();
        String contactNo1=localStorage.getInstance(getApplicationContext()).getMobileNo();
        meterId.setText(meterId1);
        contactNo.setText(contactNo1);
        submitBtn=findViewById(R.id.button);
        Date currentDate= Calendar.getInstance().getTime();

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        auth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        String userId=auth.getCurrentUser().getUid();
        databaseReference=firebaseDatabase.getReference("meter").child(userId).child(currentDate.toString());

        DatabaseReference databaseReference1=databaseReference.child("ReadingPoint");
       // databaseReference1=databaseReference.child("name");
        DatabaseReference databaseReference2=databaseReference1.child(currentDate.toString());
      //  firebaseDatabase.getReference("MXmxOcpCGSHmG2Khr7O").setValue("databaseReference");
        firebaseUser=auth.getCurrentUser();
        Log.d(localStorage.getInstance(getApplicationContext()).getUserId(),"getUid");

        DatabaseReference finalDatabaseReference = databaseReference1;
        DatabaseReference finalDatabaseReference1 = databaseReference1;
        DatabaseReference finalDatabaseReference2 = databaseReference1;

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String meterId1 = meterId.getText().toString();
                String contactNo1 = contactNo.getText().toString();
                String readingId1 = readingPoint.getText().toString();

                MeterReading meterReading = new MeterReading();
                meterReading.setMeterId(meterId1);
                meterReading.setContactNo(contactNo1);
                meterReading.setReadingPoint(readingId1);
                meterReading.setUserName(localStorage.getInstance(getApplicationContext()).getUserName().toString());
                meterReading.setUserId(auth.getUid());
                Log.d(auth.getUid(), "getUid");
                meterReading.setUserName(firebaseUser.getDisplayName());

                databaseReference.push().setValue(meterReading);


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String, Object> objectMap = (HashMap<String, Object>)
                                dataSnapshot.getValue();
                        for (Object obj : objectMap.values()) {
                            if (obj instanceof Map) {
                                Map<String, Object> mapObj = (Map<String, Object>) obj;

                                Log.d("Value is: ", " " + mapObj);

                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Query query =databaseReference.limitToLast(1);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.d("Value is: ", " " + dataSnapshot.getValue());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            };
        });
    }
}