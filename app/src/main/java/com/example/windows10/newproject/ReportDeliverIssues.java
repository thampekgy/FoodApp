package com.example.windows10.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows10.newproject.Database.Database;
import com.example.windows10.newproject.Model.Complain;
import com.example.windows10.newproject.Model.Member;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReportDeliverIssues extends AppCompatActivity {


    private EditText yName, yContact, yEmail, yComplain;
    private Button btnSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_deliver_issues);
        setTitle("Report Delivery Issues");

        yName = (EditText) findViewById(R.id.yName);
        yContact = (EditText) findViewById(R.id.yContact);
        yEmail = (EditText) findViewById(R.id.yEmail);
        yComplain = (EditText) findViewById(R.id.yComlain);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        final DatabaseReference table_com = database.getReference("Complain");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = yName.getText().toString();
                String phone = yContact.getText().toString();
                String email = yEmail.getText().toString();
                String comp = yComplain.getText().toString();
                boolean flag = true;

                if (name.isEmpty()) {
                    flag = false;
                    Toast.makeText(ReportDeliverIssues.this, "Name Invalid.", Toast.LENGTH_SHORT).show();
                }

                if (!phone.isEmpty()) {
                    if (phone.length() < 10 || phone.length() > 11) {
                        flag = false;
                        Toast.makeText(ReportDeliverIssues.this, "Contact too short or too long.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    flag = false;
                    Toast.makeText(ReportDeliverIssues.this, "Invalid Contact.", Toast.LENGTH_SHORT).show();

                }

                if (!email.isEmpty()) {

                    if ((!email.contains("@")) || (!email.contains(".com"))) {
                        flag = false;
                        Toast.makeText(ReportDeliverIssues.this, "Email Format Invalid.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    flag = false;
                    Toast.makeText(ReportDeliverIssues.this, "Email Invalid.", Toast.LENGTH_SHORT).show();
                }

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
}
