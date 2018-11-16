package com.example.windows10.newproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help1 extends Activity {

    Button faqs, deliveryIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help1);

        faqs = (Button) findViewById(R.id.faq);
        deliveryIssues = (Button) findViewById(R.id.reportDeliveryMan);

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
                startActivity(i);
            }
        });
    }
}
