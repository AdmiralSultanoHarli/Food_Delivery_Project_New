package com.example.fooddeliveryproject.Activities.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;


public class LoginScreenActivity extends BaseActivity {

    Button buttonSignIn, buttonSignUp;
    ImageButton buttonClose;
    EditText phoneNumber, password;
    private AwesomeValidation awesomeValidation;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
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

                submitForm();

            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(LoginScreenActivity.this, SignUpScreenActivity.class);
                signUp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                signUp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signUp);

            }
        });


        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

    }

    private void submitForm() {

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        String phoneNumberStr = phoneNumber.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();

        if (awesomeValidation.validate()) {


            cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USER + " WHERE " + DatabaseHelper.COLUMN_USER_PHONENUMBER + "=? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + "=?", new String[]{phoneNumberStr, passwordStr});
            if (cursor != null) {

                if (cursor.getCount() > 0) {
                    //process the data further
                    SaveSharedPreference.setThereIsUser(LoginScreenActivity.this, true);
                    Intent homeActivity = new Intent(LoginScreenActivity.this, HomeScreenActivity.class);
                    startActivity(homeActivity);
                    Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    finish();

                }else {

                    Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(LoginScreenActivity.this, AppScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();

    }
}
