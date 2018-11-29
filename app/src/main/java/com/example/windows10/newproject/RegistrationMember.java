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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationMember extends AppCompatActivity {

    private Button resSubmit;
    private EditText resEmail, resPassword, confirmPassword;
    private EditText resFirstName, resLastName, resContact;
    private FirebaseAuth firebaseAuth;
    //private FirebaseDatabase firebaseDatabase;
    //private DatabaseReference dbRef;
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
        resSubmit = (Button) findViewById(R.id.resSubmit);

        //firebaseAuth = FirebaseAuth.getInstance();




        resSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });

    }



    public void validate(){

        String fName = resFirstName.getText().toString();
        String lName = resLastName.getText().toString();
        String Contact = resContact.getText().toString();
        String rEmail = resEmail.getText().toString();
        String pass = resPassword.getText().toString();
        String cPass = confirmPassword.getText().toString();

        Boolean flag = true;

        if (fName.isEmpty()) {
            flag = false;
            Toast.makeText(RegistrationMember.this, "Name Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (lName.isEmpty()) {
            flag = false;
            Toast.makeText(RegistrationMember.this, "Name Invalid.", Toast.LENGTH_SHORT).show();
        }


        if (!Contact.isEmpty()) {
            if (Contact.length() < 10 || Contact.length() > 11) {
                flag = false;
                Toast.makeText(RegistrationMember.this, "Contact too short or too long.", Toast.LENGTH_SHORT).show();
            }
        } else {
            flag = false;
            Toast.makeText(RegistrationMember.this, "Invalid Contact.", Toast.LENGTH_SHORT).show();

        }

        if (!rEmail.isEmpty()) {

            if ((!rEmail.contains("@")) || (!rEmail.contains(".com"))) {
                flag = false;
                Toast.makeText(RegistrationMember.this, "Email Format Invalid.", Toast.LENGTH_SHORT).show();
            }

        } else {
            flag = false;
            Toast.makeText(RegistrationMember.this, "Email Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (!pass.isEmpty() && !cPass.isEmpty()) {

            if (pass.length() > 8) {

                if (!pass.equals(cPass)) {
                    flag = false;
                    Toast.makeText(RegistrationMember.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                }
            } else {
                flag = false;
                Toast.makeText(RegistrationMember.this, "Password length must more than 8.", Toast.LENGTH_SHORT).show();
            }

        } else {
            flag = false;
            Toast.makeText(RegistrationMember.this, "Password Invalid.", Toast.LENGTH_SHORT).show();
        }


        if (flag == true)
        {
            checkFirebase();

        }

    }

    public  void checkFirebase(){

        final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        final DatabaseReference table_member = database.getReference("Member");

        final ProgressDialog mDialog= new ProgressDialog(RegistrationMember.this);
        mDialog.setMessage("Please waiting...");
        mDialog.show();


        table_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check if already user email
                if(dataSnapshot.child(resContact.getText().toString()).exists()){
                    mDialog.dismiss();
                    Toast.makeText(RegistrationMember.this, "Email already registered...", Toast.LENGTH_SHORT).show();

                }
                else{
                    mDialog.dismiss();
                    String fullName = resFirstName.getText().toString() + resLastName.getText().toString();
                    Member member = new Member(fullName, resEmail.getText().toString(), resPassword.getText().toString(), resContact.getText().toString(), 10);
                    table_member.child(resContact.getText().toString()).setValue(member);
                    Toast.makeText(RegistrationMember.this, "Sign up successfully !!! ", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
