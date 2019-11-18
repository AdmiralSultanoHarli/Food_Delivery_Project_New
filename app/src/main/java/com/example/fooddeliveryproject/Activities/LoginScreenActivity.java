package com.example.fooddeliveryproject.Activities;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginScreenActivity extends AppCompatActivity {

    Button buttonSignIn, buttonSignUp;
    ImageButton buttonClose;
    EditText phoneNumber, password;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonClose = findViewById(R.id.buttonClose);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.password, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.phoneNumber, "^(^\\+62\\s?|^0)(\\d{3,4}){2}\\d{3,4}$", R.string.mobileerror);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(LoginScreenActivity.this, "Login Succeed", Toast.LENGTH_SHORT).show();
                submitForm();

            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(LoginScreenActivity.this, SignUpScreenActivity.class);
                startActivity(signUp);

            }
        });


        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent closeLogin = new Intent(LoginScreenActivity.this, AppScreenActivity.class);
                startActivity(closeLogin);

            }
        });

    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_LONG).show();

            //process the data further
            Intent homeActivity = new Intent(LoginScreenActivity.this, HomeScreenActivity.class);
            startActivity(homeActivity);
        }
    }

}
