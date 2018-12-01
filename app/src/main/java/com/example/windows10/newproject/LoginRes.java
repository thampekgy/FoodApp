package com.example.windows10.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A login screen that offers login via email/password.
 */
public class  LoginRes extends AppCompatActivity {

    private EditText txtPhone;
    private EditText txtPwd;
    private Button forgetPassword, btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_res);

        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        forgetPassword = (Button) findViewById(R.id.buttonForgetPassword);
        forgetPassword.setPaintFlags(forgetPassword.getPaintFlags());


        txtPhone = (EditText) findViewById(R.id.phone);
        txtPwd = (EditText) findViewById(R.id.password);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_owner = database.getReference("RestaurantOwner");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginRes.this);
                mDialog.setMessage(("Please wait..."));
                mDialog.show();
                //Query query = table_member.child("Member").orderByChild();

                table_owner.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String txtPhoneText = txtPhone.getText().toString();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                if (snapshot.child("ownerContact").getValue().equals(txtPhoneText)) {
                                    mDialog.dismiss();


                                    if (snapshot.child("password").getValue().equals(txtPwd.getText().toString())) {
                                        Toast.makeText(LoginRes.this, "Login successfully...", Toast.LENGTH_SHORT).show();
                                        //Common.currentMember = mem;
                                        String name = snapshot.child("ownerName").getValue().toString();
                                        String email = snapshot.child("ownerEmail").getValue().toString();
                                        Intent intent = new Intent(LoginRes.this, MainActivity.class);
                                        intent.putExtra("Email", email);
                                        intent.putExtra("Phone", txtPhoneText);
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        Toast.makeText(LoginRes.this, "Wrong Password...", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void joinUsRestaurantOwner_click(View v) {
        Intent intent = new Intent(LoginRes.this, RegistrationResOwner.class);
        startActivity(intent);
    }
}

