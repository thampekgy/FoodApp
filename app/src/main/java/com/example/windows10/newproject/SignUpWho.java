package com.example.windows10.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignUpWho extends AppCompatActivity {

    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_who);


    }

    public void btnMember_click(View v){
        Intent intent = new Intent(SignUpWho.this, RegistrationMember.class);
        startActivity(intent);
    }



    public void btnOwner_click(View v){
        Intent intent1 = new Intent(SignUpWho.this, RegistrationResOwner.class);
        startActivity(intent1);
    }
}
