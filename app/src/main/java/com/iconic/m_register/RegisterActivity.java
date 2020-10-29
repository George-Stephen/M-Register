package com.iconic.m_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iconic.services.RegisterClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_text) TextView mLoginText;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.register_name) EditText mRegisterName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.register_email) EditText mRegisterEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.register_phone) EditText mRegisterPhone;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.register_password) EditText mRegisterPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.register_button) Button mRegisterButton;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        createAuthProgressDialog();

        mRegisterButton.setOnClickListener(this);
        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == mRegisterButton){
            // initialization
            String full_name = mRegisterName.getText().toString();
            String email = mRegisterEmail.getText().toString();
            String phone = mRegisterPhone.getText().toString();
            String password = mRegisterPassword.getText().toString();

            // validation
            if(full_name.equals("")){
                mRegisterName.setError("Please enter your full name");
            }
            else if (email.equals("")){
                mRegisterEmail.setError("Please enter your email address");
            }
            else if (phone.equals("")){
                mRegisterPhone.setError("Please enter your phone number");
            }
            else if (password.equals("")){
                mRegisterPassword.setError("Please enter the password");
            }
            else {
                dialog.show();

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();

                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Welcome " + full_name +"to M-Register" , Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        }
    }
    private void createAuthProgressDialog(){
        dialog  = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.setMessage("Creating your account....");
        dialog.setCancelable(false);
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
}