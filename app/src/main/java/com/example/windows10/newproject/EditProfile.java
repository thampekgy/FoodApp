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

    EditText name, email, contact;
    String no, name1, email1, pwd;
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


        String key = getIntent().getExtras().get("Phone").toString();
        database = FirebaseDatabase.getInstance();
        members = database.getReference("Member").child(key);

        name = (EditText) findViewById(R.id.editText_name);
        email = (EditText) findViewById(R.id.editText_email);
        contact = (EditText) findViewById(R.id.editText_contactNumber);
        submit = (Button) findViewById(R.id.btnSubmit);


        //Bundle result = main.getMyData();
        //Intent intent = getIntent();
        no = getIntent().getStringExtra("Phone");
        contact.setText(no);

        name1 = getIntent().getStringExtra("Name");
        name.setText(name1);

        email1 = getIntent().getStringExtra("Email");
        email.setText(email1);

        pwd = getIntent().getStringExtra("Password");




    }


    public void btnSubmitUpdate_Click(View v){
        //final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        //final DatabaseReference table_member = database.getReference("Member").child(no);



        //table_member.addValueEventListener(new ValueEventListener() {

            //@Override
            //public void onDataChange(DataSnapshot dataSnapshot) {


                //mDialog.dismiss();

                //table_member.removeValue();

                //create
                //Member member = new Member(name.getText().toString(), email.getText().toString(), pwd, contact.getText().toString(),10);
                   //String member = members.updateChildren();
                members.child("name").setValue(name.getText().toString());
                members.child("email").setValue(email.getText().toString());





                /*database = FirebaseDatabase.getInstance();
                members = database.getReference().child("Member");
                members.child("name").setValue(name.getText().toString());*/



                 //Toast.makeText(EditProfile.this, "Updated!!! ", Toast.LENGTH_SHORT).show();
                 //finish();
                //}


            /*@Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

}
