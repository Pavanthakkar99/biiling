package com.example.smartbilling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartbilling.Model.MeterBill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class BillGenrateActivity extends AppCompatActivity {

    TextView SocityName, userName, userMeterId, currentUnit, previosUnit, usedUnit, unitPrice, mantense, totalBill;
    Button genrateBill;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    int meterunitPrice,maintance;
    String socityNAMEs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_genrate);
        SocityName = findViewById(R.id.socityName);
        userName = findViewById(R.id.userName);
        userMeterId = findViewById(R.id.userMeterId);
        currentUnit = findViewById(R.id.currentUnit);
        previosUnit = findViewById(R.id.previosUnit);
        usedUnit = findViewById(R.id.usedUnit);
        unitPrice = findViewById(R.id.unitPrice);
        mantense = findViewById(R.id.mantense);
        totalBill = findViewById(R.id.totalBill);
        genrateBill = findViewById(R.id.genrateBill);
        auth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        String userId=auth.getCurrentUser().getUid();
        myRef=firebaseDatabase.getReference("MetersBillList").child(userId);
        databaseReference=firebaseDatabase.getReference("Admin");

        Query query =databaseReference.limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
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
        Intent intent = getIntent();
        int usedUnitint = Integer.parseInt(intent.getStringExtra("usedUnit"));
        int unitPriceInt = Integer.parseInt(intent.getStringExtra("unitPrice"));
        int mantenseInt = Integer.parseInt(intent.getStringExtra("mantance"));
        Log.d("onCreate: ",meterunitPrice+" "+maintance);
        int TotalBillInt = usedUnitint * unitPriceInt + mantenseInt;

        String socityName = "Socity Name: " + intent.getStringExtra("societyName");
        String username = "User Name:: " + intent.getStringExtra("userName");
        String usermeterID = "Meter Id: " + intent.getStringExtra("meterId");
        String currentunit = "Current Unit: " + intent.getStringExtra("currentUnit");
        String previousunit = "Previos Unit: " + intent.getStringExtra("previousUnit");
        String usedunit = "Used Unit: " + usedUnitint;
        String unitprice = "Unit Price: " + intent.getStringExtra("unitPrice");
        String meantense = "Mantance: " + intent.getStringExtra("mantance");
        String totalbill = "Total Bill: " + TotalBillInt;

        SocityName.setText(socityName);
        userName.setText(username);
        userMeterId.setText(usermeterID);
        currentUnit.setText(currentunit);
        previosUnit.setText(previousunit);
        usedUnit.setText(usedunit);
        unitPrice.setText(unitprice);
        mantense.setText(meantense);
        totalBill.setText(totalbill);


        genrateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MeterBill meterBill=new MeterBill();
                meterBill.setBillDate(intent.getStringExtra("billDate"));
                meterBill.setUsermeterID(intent.getStringExtra("meterId"));
                meterBill.setCurrentunit(intent.getStringExtra("currentUnit"));
                meterBill.setMeantense(String.valueOf(mantenseInt));
                meterBill.setSocityName("KAranavti bunglose");
                meterBill.setPreviousunit(intent.getStringExtra("previousUnit"));
                meterBill.setTotalbill(""+TotalBillInt);
                meterBill.setUsedunit(intent.getStringExtra("usedUnit"));
                meterBill.setUsername(intent.getStringExtra("userName"));
                myRef.push().setValue(meterBill);
                Intent intent1 = new Intent(BillGenrateActivity.this, DetailsActivity.class);
                intent1.putExtra("TabIndex", "1");

                startActivity(intent1);
            }
        });
    }
}