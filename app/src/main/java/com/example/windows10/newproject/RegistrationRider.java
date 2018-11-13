package com.example.windows10.newproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.windows10.newproject.Model.Rider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class RegistrationRider extends AppCompatActivity {

    private Button btnSubmit;
    private EditText riderName, riderEmail, riderContact;
    private EditText DOB, guardianName, guardianWho, guardianContact;
    private EditText password, confirmPassword;
    private RadioButton radioGenderButton, radioButtonMale, radioButtonFemale;
    private RadioGroup radioGroupGender;
    Spinner spinCity;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbRef;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_registration);

        btnSubmit = (Button) findViewById(R.id.riderSubmitBtn);
        riderName = (EditText) findViewById(R.id.riderName);
        riderContact = (EditText) findViewById(R.id.riderContact);
        riderEmail = (EditText) findViewById(R.id.riderEmailAddress);

        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);

        spinCity = (Spinner) findViewById(R.id.spinnerCity);
        String spinItem = spinCity.getSelectedItem().toString();

        password = (EditText) findViewById(R.id.editTextPassword);
        confirmPassword = (EditText) findViewById(R.id.editTextConfirm);

        guardianName = (EditText) findViewById(R.id.riderGuardianName);
        guardianWho = (EditText) findViewById(R.id.riderGuardianWho);
        guardianContact = (EditText) findViewById(R.id.riderGuardianContact);

        DOB = (EditText) findViewById(R.id.riderDateOfBirth);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                DOB.setText(sdf.format(myCalendar.getTime()));

            }
        };

        DOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegistrationRider.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedId);






    }


    public void riderSubmitBtn_click(View v){

        String rName = riderName.getText().toString();
        String rContact = riderContact.getText().toString();
        String rEmail = riderEmail.getText().toString();
        String rDOB = DOB.getText().toString();
        String gName = guardianName.getText().toString();
        String gWho = guardianWho.getText().toString();
        String gContact = guardianContact.getText().toString();
        String pass = password.getText().toString();
        String cPass = confirmPassword.getText().toString();

        Boolean flag = true;


        if (rName.isEmpty()) {
                flag=false;
                Toast.makeText(RegistrationRider.this, "Name Invalid.", Toast.LENGTH_SHORT).show();
        }


        if ( !rContact.isEmpty()) {
            if (rContact.length() < 10 || rContact.length() > 11) {
                flag = false;
                Toast.makeText(RegistrationRider.this, "Contact too short or too long.", Toast.LENGTH_SHORT).show();
            }
        }else{
            flag = false;
            Toast.makeText(RegistrationRider.this, "Invalid Contact.", Toast.LENGTH_SHORT).show();

        }

        if (!rEmail.isEmpty()) {

             if ((!rEmail.contains("@")) || (!rEmail.contains(".com"))) {
                 flag=false;
                 Toast.makeText(RegistrationRider.this, "Email Format Invalid.", Toast.LENGTH_SHORT).show();
             }

        } else {
             flag=false;
             Toast.makeText(RegistrationRider.this, "Email Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (!pass.isEmpty() && !cPass.isEmpty()){

            if (pass.length() > 8) {

                if (!pass.equals(cPass)) {
                    flag = false;
                    Toast.makeText(RegistrationRider.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                } else{
                    flag = false;
                    Toast.makeText(RegistrationRider.this, "Password length must more than 8.", Toast.LENGTH_SHORT).show();
                }
            }

        }else
        {
            flag=false;
            Toast.makeText(RegistrationRider.this, "Password Invalid.", Toast.LENGTH_SHORT).show();
        }

        if (rDOB.isEmpty()) {
            flag=false;
            Toast.makeText(RegistrationRider.this, "Insert the birthday date.", Toast.LENGTH_SHORT).show();
        }

        if (gName.isEmpty()||gWho.isEmpty()||gContact.isEmpty()) {
            flag=false;
            Toast.makeText(RegistrationRider.this, "Emergency Information fields are required..", Toast.LENGTH_SHORT).show();
        }

        if (flag == true)
        {
            createRider();
            Toast.makeText(RegistrationRider.this, "Registration Successful.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(RegistrationRider.this, LoginActivity.class);
            startActivity(i);
        }



    }

    public void createRider(){

        dbRef = FirebaseDatabase.getInstance().getReference();
         Rider rider = new Rider(riderName.getText().toString(), radioGenderButton.getText().toString(), riderContact.getText().toString(), spinCity.getSelectedItem().toString(), riderEmail.getText().toString(), DOB.getText().toString(), password.getText().toString(), guardianName.getText().toString(), guardianWho.getText().toString(), guardianContact.getText().toString());
        dbRef.child("DeliveryPerson").push().setValue(rider);
    }








}
