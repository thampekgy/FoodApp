package com.example.windows10.newproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.windows10.newproject.Model.RestaurantOwner;
import com.example.windows10.newproject.Model.Rider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationResOwner extends AppCompatActivity {

    private EditText ownerName, ownerContact, ownerEmail;
    private EditText resName, resAddress, resPostal;
    private EditText password, confirmPassword;
    private RadioButton radioGenderButton, radioButtonMale, radioButtonFemale;
    private RadioGroup radioGroupGender;
    Spinner spinCity;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_res_owner);

        ownerName = (EditText) findViewById(R.id.editText_OwnerName);
        ownerContact = (EditText) findViewById(R.id.editText_OwnerContact);
        ownerEmail = (EditText) findViewById(R.id.editText_OwnerEmail);

        password = (EditText) findViewById(R.id.editTextPassword);
        confirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);

        resName = (EditText) findViewById(R.id.editText_RestaurantName);
        resAddress = (EditText) findViewById(R.id.editText_RestaurantAddress);
        resPostal = (EditText) findViewById(R.id.editText_Postal);

        spinCity = (Spinner) findViewById(R.id.spinnerCity);
        String spinItem = spinCity.getSelectedItem().toString();


        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedId);


    }

    public void resOwnerSubmit_click(View v)  {

        String oName = ownerName.getText().toString();
        String oContact = ownerContact.getText().toString();
        String oEmail = ownerEmail.getText().toString();
        String rName = resName.getText().toString();
        String rAdress = resAddress.getText().toString();
        String rPostal = resPostal.getText().toString();
        String pass = password.getText().toString();
        String cPass = confirmPassword.getText().toString();

        Boolean flag = true;

        if (oName.isEmpty()) {
            flag=false;
            Toast.makeText(RegistrationResOwner.this, " Owner Name Invalid.", Toast.LENGTH_SHORT).show();
        }

        if ( !oContact.isEmpty()) {
            if (oContact.length() < 10 || oContact.length() > 11) {
                flag = false;
                Toast.makeText(RegistrationResOwner.this, "Contact too short or too long.", Toast.LENGTH_SHORT).show();
            }
        }else{
            flag = false;
            Toast.makeText(RegistrationResOwner.this, "Invalid Contact.", Toast.LENGTH_SHORT).show();

        }

        if (!oEmail.isEmpty()) {

            if ((!oEmail.contains("@")) || (!oEmail.contains(".com"))) {
                flag=false;
                Toast.makeText(RegistrationResOwner.this, "Email Format Invalid.", Toast.LENGTH_SHORT).show();
            }

        } else {
            flag=false;
            Toast.makeText(RegistrationResOwner.this, "Email Invalid.", Toast.LENGTH_SHORT).show();
        }
        if (!pass.isEmpty() && !cPass.isEmpty()){

            if (pass.length() > 8) {

                if (!pass.equals(cPass)) {
                    flag = false;
                    Toast.makeText(RegistrationResOwner.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                } else{
                    flag = false;
                    Toast.makeText(RegistrationResOwner.this, "Password length must more than 8.", Toast.LENGTH_SHORT).show();
                }
            }

        }else
        {
            flag=false;
            Toast.makeText(RegistrationResOwner.this, "Password Invalid.", Toast.LENGTH_SHORT).show();
        }


        if (rName.isEmpty()) {
            flag=false;
            Toast.makeText(RegistrationResOwner.this, "Restaurant Name Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (rAdress.isEmpty()) {
            flag=false;
            Toast.makeText(RegistrationResOwner.this, " Restaurant Address Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (!rPostal.isEmpty()) {
            if(rPostal.length() <= 4 || rPostal.length() >=6)
            flag=false;
            Toast.makeText(RegistrationResOwner.this, " Postal too long or short.", Toast.LENGTH_SHORT).show();
        }else
        {
            flag=false;
            Toast.makeText(RegistrationResOwner.this, " Postal Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (flag == true)
        {
            createRestaurantOwner();
            Toast.makeText(RegistrationResOwner.this, "Registration Successful.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(RegistrationResOwner.this, LoginActivity.class);
            startActivity(i);
        }

    }

    public void createRestaurantOwner(){

        dbRef = FirebaseDatabase.getInstance().getReference();
        RestaurantOwner restaurantOwner = new RestaurantOwner(ownerName.getText().toString(), radioGenderButton.getText().toString(), ownerContact.getText().toString(), ownerEmail.getText().toString(), password.getText().toString(), resName.getText().toString(), resAddress.getText().toString(), resPostal.getText().toString(), spinCity.getSelectedItem().toString(), 0.00);
        dbRef.child("RestaurantOwner").push().setValue(restaurantOwner);
    }
}
