package com.example.windows10.newproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows10.newproject.Model.Complain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReportDeliverIssues extends AppCompatActivity {


    private EditText yName, yContact, yEmail, yComplain;
    private Button btnSubmit;
    String name1, email1, contact1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_deliver_issues);
        setTitle("Report Delivery Issues");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        yName = (EditText) findViewById(R.id.yName);
        yContact = (EditText) findViewById(R.id.yContact);
        yEmail = (EditText) findViewById(R.id.yEmail);
        yComplain = (EditText) findViewById(R.id.yComlain);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        final DatabaseReference table_com = database.getReference("Complain");

        name1 = getIntent().getStringExtra("Name");
        yName.setText(name1);
        contact1 = getIntent().getStringExtra("Phone");
        yContact.setText(contact1);
        email1 = getIntent().getStringExtra("Email");
        yEmail.setText(email1);

        getName();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String comp = yComplain.getText().toString();
                boolean flag = true;


                if (comp.isEmpty()) {
                    flag = false;
                    Toast.makeText(ReportDeliverIssues.this, "Complain field could not be empty.", Toast.LENGTH_SHORT).show();
                }

                if (flag == true) {
                    Complain com = new Complain(yName.getText().toString(), yContact.getText().toString(), yEmail.getText().toString(), yComplain.getText().toString());
                    table_com.child(yContact.getText().toString()).setValue(com);
                    Toast.makeText(ReportDeliverIssues.this, "Complain form delivered. ", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }

        });
    }

    public void getName(){
        String key = getIntent().getExtras().get("Phone").toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_member = database.getReference("Member").child(key);


        table_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    contact1 = dataSnapshot.child("contact").getValue().toString();
                    yContact.setText(contact1);

                    name1 = dataSnapshot.child("name").getValue().toString();
                    yName.setText(name1);

                    email1 = dataSnapshot.child("email").getValue().toString();
                    yEmail.setText(email1);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
