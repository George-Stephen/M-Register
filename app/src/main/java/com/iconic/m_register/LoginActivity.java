package com.iconic.m_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.register_text) TextView mRegisterText;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_email) EditText mLoginEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_password) EditText mLoginPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_button) Button mLoginButton;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        createAuthProgressDialog();

        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == mLoginButton){
            //initialization
            String email = mLoginEmail.getText().toString();
            String password = mLoginPassword.getText().toString();

            // validation
            if (email.equals("")){
                mLoginEmail.setError("Please enter your email");
            }
            else if (password.equals("")){
                mLoginPassword.setError("Please enter your password");
            }
            // login
            else{
                dialog.show();
                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                dialog.dismiss();

                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
    private void createAuthProgressDialog(){
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.setMessage("Loading account");
        dialog.setCancelable(false);
    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
}