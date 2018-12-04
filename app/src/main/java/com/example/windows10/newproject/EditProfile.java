package com.example.windows10.newproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.windows10.newproject.Model.Member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfile extends AppCompatActivity {

    EditText name, email;
    TextView contact;
    String no, name1, email1, pwd, key;
    Button submit;

    FirebaseDatabase database ;
    DatabaseReference members;
    MainActivity main = new MainActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setTitle("Manage Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        key = getIntent().getExtras().get("Phone").toString();
        database = FirebaseDatabase.getInstance();
        members = database.getReference("Member").child(key);

        name = (EditText) findViewById(R.id.editText_name);
        email = (EditText) findViewById(R.id.editText_email);
        contact = (TextView) findViewById(R.id.editText_contactNumber);
        submit = (Button) findViewById(R.id.btnSubmit);


        database = FirebaseDatabase.getInstance();
        DatabaseReference table_member = database.getReference("Member").child(key);


        table_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    no = dataSnapshot.child("contact").getValue().toString();
                    contact.setText(no);

                    name1 = dataSnapshot.child("name").getValue().toString();
                    name.setText(name1);

                    email1 = dataSnapshot.child("email").getValue().toString();
                    email.setText(email1);

        pwd = getIntent().getStringExtra("Password");


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void btnSubmitUpdate_Click(View v){

        database = FirebaseDatabase.getInstance();
        members = database.getReference("Member").child(key);


        members.child("name").setValue(name.getText().toString());
        members.child("email").setValue(email.getText().toString());

        Toast.makeText(EditProfile.this, "Updated !!!", Toast.LENGTH_SHORT).show();


    }

}
