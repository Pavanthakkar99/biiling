package com.example.smartbilling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    DatabaseReference databaseReference,databaseReference11;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseDatabase database;
    String previosMeterReadingStr;
    int previosMeterReadingInt;
    int meterunitPrice,maintance;
    String socityNAMEs;
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
        databaseReference=firebaseDatabase.getReference("meter").child(userId);

        DatabaseReference databaseReference1=databaseReference.child("ReadingPoint");
       // databaseReference1=databaseReference.child("name");
        DatabaseReference databaseReference2=databaseReference1.child(currentDate.toString());
      //  firebaseDatabase.getReference("MXmxOcpCGSHmG2Khr7O").setValue("databaseReference");
        firebaseUser=auth.getCurrentUser();
        Log.d(localStorage.getInstance(getApplicationContext()).getUserId(),"getUid");

        DatabaseReference finalDatabaseReference = databaseReference1;
        DatabaseReference finalDatabaseReference1 = databaseReference1;
        DatabaseReference finalDatabaseReference2 = databaseReference1;

//        DatabaseReference databaseReference3=databaseReference.child("meter");
        Query query =databaseReference.limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int orgnal=0;
                int dataa=0;
                String vala;
                try {


                Map<String, Object> objectMap = (HashMap<String, Object>)
                        dataSnapshot.getValue();
                for (Object obj : objectMap.values()){
                    if (obj instanceof Map) {
                        Map<String, Object> mapObj = (Map<String, Object>) obj;
                        Log.d("Value is from first : ", " " +mapObj.get("readingPoint"));
                        previosMeterReadingStr=mapObj.get("readingPoint").toString();
                        previosMeterReadingInt=Integer.parseInt(previosMeterReadingStr);
//                        vala=mapObj.get("readingPoint").toString();
//                        int valuees=Integer.parseInt(vala);
//                        dataa-=-valuees;
                    }
                } }catch (Exception e)
                {

                }
//                        Log.d("Value is from first : ", " "+dataa);

//                        Log.d("orignal : ", " "+ vala);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference11=firebaseDatabase.getReference("Admin");
        Query query1 =databaseReference11.limitToLast(1);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Map<String, Object> objectMap = (HashMap<String, Object>)
                            dataSnapshot.getValue();
                    for (Object obj : objectMap.values()){
                        if (obj instanceof Map) {
                            Map<String, Object> mapObj = (Map<String, Object>) obj;
                            Log.d("Value is from meter :",mapObj.get("meter").toString()+" "+mapObj.get("mantaines").toString());
                            String meterStr=mapObj.get("meter").toString();
                            String mainStr=mapObj.get("mantaines").toString();
                            socityNAMEs=mapObj.get("societyName").toString();
                            meterunitPrice=Integer.parseInt(meterStr);
                            maintance=Integer.parseInt(mainStr);
                            System.out.print(meterunitPrice);
                            System.out.print(maintance);

                        }
                    } }catch (Exception e)
                {

                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String meterId1 = meterId.getText().toString();
                String contactNo1 = contactNo.getText().toString();
                String readingId1 = readingPoint.getText().toString();
                String userNAme=localStorage.getInstance(getApplicationContext()).getUserName();

                MeterReading meterReading = new MeterReading();
                meterReading.setMeterId(meterId1);
                meterReading.setContactNo(contactNo1);
                meterReading.setReadingPoint(readingId1);
                meterReading.setDate(currentDate.toString());
                meterReading.setUserName(localStorage.getInstance(getApplicationContext()).getUserName().toString());
                meterReading.setUserId(auth.getUid());
                databaseReference.push().setValue(meterReading);

                Log.d(auth.getUid(), "getUid");
                meterReading.setUserName(userNAme);
                int currenReadingPonint=Integer.parseInt(readingId1);
                int finalReadingPoint= currenReadingPonint- previosMeterReadingInt;
                Log.d("finalPoint:",""+finalReadingPoint);

                Intent intent=new Intent(FormActivity.this,BillGenrateActivity.class);

                String meterIds=localStorage.getInstance(getApplicationContext()).getMeterId();
                intent.putExtra("userName",userNAme);
                intent.putExtra("meterId",meterIds);
                intent.putExtra("currentUnit",readingId1);
                intent.putExtra("previousUnit",String.valueOf(previosMeterReadingInt));
                intent.putExtra("usedUnit",String.valueOf(finalReadingPoint));
                intent.putExtra("billDate",currentDate.toString());
                intent.putExtra("societyName",socityNAMEs);
                intent.putExtra("unitPrice",String.valueOf(meterunitPrice));
                intent.putExtra("mantance",String.valueOf(maintance));
                   startActivity(intent);



            };
        });
    }
}