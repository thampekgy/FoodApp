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

import com.example.windows10.newproject.Common.Common;
import com.example.windows10.newproject.Model.Member;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A login screen that offers login via email/password.
 */
public class  LoginActivity extends AppCompatActivity{

    private AutoCompleteTextView txtEmailLogin;
    private EditText txtPwd;
    private  Button forgetPassword, btnSignIn;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        forgetPassword = (Button) findViewById(R.id.buttonForgetPassword);
        forgetPassword.setPaintFlags(forgetPassword.getPaintFlags()/* | Paint.UNDERLINE_TEXT_FLAG*/);


        txtEmailLogin = (AutoCompleteTextView) findViewById(R.id.email);
        txtPwd = (EditText) findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        final DatabaseReference table_member = database.getReference("Member");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage(("Pleas wait..."));
                mDialog.show();

                table_member.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(txtEmailLogin.getText().toString()).exists()){
                            mDialog.dismiss();
                            Member member = dataSnapshot.child(txtEmailLogin.getText().toString()).getValue(Member.class);
                            if(member.getPassword().equals(txtPwd.getText().toString())){
                                {
                                    Intent intent = new Intent(LoginActivity.this, HomeFragment.class);
                                    Common.currentMember=member;
                                    startActivity(intent);
                                    finish();
                                }
                            }else {
                                Toast.makeText(LoginActivity.this, "Wrong Password...", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Kullanıcı Bilgilerini Bulamadık", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });





    }
    public void email_register_button_click(View v){
        Intent intent = new Intent(LoginActivity.this, RegistrationMember.class);
        startActivity(intent);

    }

    public void email_sign_in_button_click(View v){
        /*final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);

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

        });*/





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

