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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import maes.tech.intentanim.CustomIntent;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    TextView notRegisterBtn;
    EditText email,password;
    FirebaseAuth auth;
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notRegisterBtn=findViewById(R.id.notRegister);
        email=findViewById(R.id.emailLogin);
        password=findViewById(R.id.passwordLogin);
        auth= FirebaseAuth.getInstance();
        progressBar=new ProgressDialog(this);
        if (auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(LoginActivity.this,DashboardNavigationActivity.class);
            startActivity(intent);
            finish();
        }

        notRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                CustomIntent.customType(LoginActivity.this,"left-to-right");
            }

        });
        loginBtn= findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                if (TextUtils.isEmpty(email1)) {
                    email.setError("Email is Required");


                } else if (TextUtils.isEmpty(password1)) {
                    password.setError("Password is Required");

                } else {
                    progressBar.setMessage("Please Wait");
                    progressBar.show();
                    auth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DashboardNavigationActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.dismiss();
                            }

                        }
                    });

                }
            }
        });

    }

    }

