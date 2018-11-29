package com.example.windows10.newproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Help1 extends AppCompatActivity {

    Button faqs, deliveryIssues;

    String name1, contact1, email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        faqs = (Button) findViewById(R.id.faq);
        deliveryIssues = (Button) findViewById(R.id.reportDeliveryMan);

        name1 = getIntent().getStringExtra("Name");
        contact1 = getIntent().getStringExtra("Phone");
        email1 = getIntent().getStringExtra("Email");

        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help1.this, FAQs.class);
                startActivity(i);
            }
        });

        deliveryIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help1.this, ReportDeliverIssues.class);
                i.putExtra("Name", name1);
                i.putExtra("Phone", contact1);
                i.putExtra("Email", email1);
                startActivity(i);
            }
        });


    }
}
