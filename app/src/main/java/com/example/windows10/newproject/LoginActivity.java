package com.example.windows10.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.windows10.newproject.Common.Common.currentMember;

/**
 * A login screen that offers login via email/password.
 */
public class  LoginActivity extends AppCompatActivity {

    private EditText txtPhone;
    private EditText txtPwd;
    private Button forgetPassword, btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
        forgetPassword = (Button) findViewById(R.id.buttonForgetPassword);
        forgetPassword.setPaintFlags(forgetPassword.getPaintFlags());


        txtPhone = (EditText) findViewById(R.id.phone);
        txtPwd = (EditText) findViewById(R.id.password);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_member = database.getReference("Member");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage(("Please wait..."));
                mDialog.show();
                //Query query = table_member.child("Member").orderByChild();
                table_member.addValueEventListener(new ValueEventListener() {



                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String txtPhoneText = txtPhone.getText().toString();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                if (snapshot.child("contact").getValue().equals(txtPhoneText)) {
                                    mDialog.dismiss();
                                    //Member mem = dataSnapshot.child("member").getValue(Member.class);

                                    //Toast.makeText(LoginActivity.this, "" + mem, Toast.LENGTH_SHORT).show();
                                    //Member mem = dataSnapshot.child(Member.class).getName();
                                    //mem.setContact(txtPhone.getText().toString());
                                    if (snapshot.child("password").getValue().equals(txtPwd.getText().toString())) {
                                        Toast.makeText(LoginActivity.this, "Login successfully...", Toast.LENGTH_SHORT).show();
                                        //Common.currentMember = mem;
                                        String mem = snapshot.child("name").getValue().toString();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("Phone", txtPhoneText);
                                        intent.putExtra("name", mem);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        Toast.makeText(LoginActivity.this, "Wrong Password...", Toast.LENGTH_SHORT).show();
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


    public void email_register_button_click(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistrationMember.class);
        startActivity(intent);

    }

    //this button is my first test version
    public void email_sign_in_button_click(View v) {
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

    public void joinUsRider_click(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistrationRider.class);
        startActivity(intent);
    }

    public void joinUsRestaurantOwner_click(View v) {
        Intent intent = new Intent(LoginActivity.this, RegistrationResOwner.class);
        startActivity(intent);
    }
}

