package com.example.windows10.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class  LoginActivity extends AppCompatActivity{

    private AutoCompleteTextView txtEmailLogin;
    private EditText txtPwd;
    private  Button forgetPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetPassword = (Button) findViewById(R.id.buttonForgetPassword);
        forgetPassword.setPaintFlags(forgetPassword.getPaintFlags()/* | Paint.UNDERLINE_TEXT_FLAG*/);


        txtEmailLogin = (AutoCompleteTextView) findViewById(R.id.email);
        txtPwd = (EditText) findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();





    }
    public void email_register_button_click(View v){
        Intent intent = new Intent(LoginActivity.this, RegistrationMember.class);
        startActivity(intent);

    }

    public void email_sign_in_button_click(View v){
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);

        firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        //i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                        startActivity(i);
                    } else {
                        Log.e("ERROR", task.getException().toString());
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        });

    }

    public void joinUsRider_click(View v){
        Intent intent = new Intent(LoginActivity.this, RegistrationRider.class);
        startActivity(intent);
    }

    public void joinUsRestaurantOwner_click(View v){
        Intent intent = new Intent(LoginActivity.this, RegistrationResOwner.class);
        startActivity(intent);
    }



}

