package com.example.windows10.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows10.newproject.Model.Member;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationMember extends AppCompatActivity {

    private Button resSubmit;
    private EditText resEmail, resPassword, confirmPassword;
    private EditText resFirstName, resLastName, resContact;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbRef;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registration);
        // Set up.
        resFirstName = (EditText) findViewById(R.id.editText_res_firstName);
        resLastName = (EditText) findViewById(R.id.editText_res_lastName);
        resContact = (EditText) findViewById(R.id.editText_res_phone);
        resEmail = (EditText) findViewById(R.id.editText_res_emailAdress);
        resPassword = (EditText) findViewById(R.id.editText_res_password);
        confirmPassword = (EditText) findViewById (R.id.editText_res_confirmPassword) ;

        firebaseAuth = FirebaseAuth.getInstance();


    }




    public void Submit_register_click(View v)
    {
        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationMember.this, "Please wait ....", "Processing...", true);

        (firebaseAuth.createUserWithEmailAndPassword(resEmail.getText().toString(), resPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            createMember();
                            Toast.makeText(RegistrationMember.this, "Registration Successful.", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegistrationMember.this, LoginActivity.class);
                            startActivity(i);

                        }
                        else
                        {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(RegistrationMember.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
    public void createMember(){

        String fullName = resFirstName.getText().toString() + resLastName.getText().toString();
        dbRef = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference ref = table_member.getReference("Member");
        Member mem = new Member(fullName, resEmail.getText().toString(), resPassword.getText().toString(), resContact.getText().toString(), 10);
        //String emailUser = resEmail.getText().toString();
        dbRef.child("Member").push().setValue(mem);
    }




}
