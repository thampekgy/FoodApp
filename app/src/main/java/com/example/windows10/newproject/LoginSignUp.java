package com.example.windows10.newproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
    }

    public void signIn_click(View v){
        Intent intent = new Intent(LoginSignUp.this, LoginMember.class);
        startActivity(intent);

    }


    public void signUp_click(View v){
        Intent intent1 = new Intent(LoginSignUp.this, RegistrationMember.class);
        startActivity(intent1);

    }
}
