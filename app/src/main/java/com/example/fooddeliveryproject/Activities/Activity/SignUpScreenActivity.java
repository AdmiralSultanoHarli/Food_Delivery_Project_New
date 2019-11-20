package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.fooddeliveryproject.R;

public class SignUpScreenActivity extends AppCompatActivity {

    Button buttonSignUp;
    ImageButton buttonClose;
    EditText name, phoneNumber, password;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonClose = findViewById(R.id.buttonClose);
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.password, "^[A-Za-z\\s]{1,}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.phoneNumber, "^(^\\+62\\s?|^0)(\\d{3,4}){2}\\d{3,4}$", R.string.mobileerror);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();

            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent closeSignUp = new Intent(SignUpScreenActivity.this, LoginScreenActivity.class);
                startActivity(closeSignUp);

            }
        });

    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_LONG).show();

            

            //process the data further
        }
    }

}
