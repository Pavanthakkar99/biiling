package com.example.smartbilling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbilling.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView signInBtn;
    EditText userName,mobileNumber,email,password,meterId;
    FirebaseAuth auth;
    ProgressDialog progressBar;
    Button registerBtn;
    DatabaseReference firebaseDatabase;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName=findViewById(R.id.username);
        mobileNumber=findViewById(R.id.mobilenumber);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        meterId=findViewById(R.id.meterID);
        signInBtn=findViewById(R.id.signInBtn);
        auth=FirebaseAuth.getInstance();
        progressBar=new ProgressDialog(this);
        registerBtn=findViewById(R.id.registerBtn);
        if (auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(RegisterActivity.this,DashboardNavigationActivity.class);
            startActivity(intent);
            finish();
        }

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName1 = userName.getText().toString();
                String mobileNumber1 = mobileNumber.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String meterId1=meterId.getText().toString();
                if (TextUtils.isEmpty(userName1)) {
                    userName.setError("User Name is Required");

                } else if (TextUtils.isEmpty(mobileNumber1)) {
                    mobileNumber.setError("Mobile Number is Required");


                } else if (TextUtils.isEmpty(email1)) {
                    email.setError("Email is Required");


                }
                else if (TextUtils.isEmpty(password1))
                {
                    password.setError("Password is Required");

                }
                else if(TextUtils.isEmpty(meterId1))
                {
                    meterId.setError("meterId is Required");
                }
                else {
                    progressBar.setMessage("Please Wait");
                    auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           // progressBar.show();

                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                User user=new User(userName1,mobileNumber1,email1,password1,meterId1);

                                String userId =auth.getCurrentUser().getUid();
                                firebaseDatabase.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                        localStorage.getInstance(getApplicationContext()).saveUser(mobileNumber1,userName1,meterId1,userId);
                                    }

                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                    }



                                });








                                Intent intent=new Intent(RegisterActivity.this,DashboardNavigationActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}