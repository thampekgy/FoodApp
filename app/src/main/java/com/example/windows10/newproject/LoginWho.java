package com.example.windows10.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginWho extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwho);


    }

    public void btnMember_click(View v){
        Intent intent = new Intent(LoginWho.this, LoginMember.class);
        startActivity(intent);

    }


    public void btnOwner_click(View v){
        Intent intent = new Intent(LoginWho.this, LoginRes.class);
        startActivity(intent);
    }
}
