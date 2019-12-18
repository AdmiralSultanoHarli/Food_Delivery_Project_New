package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;

import com.example.fooddeliveryproject.R;

public class PaymentScreenActivity extends AppCompatActivity {

    Button payNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);

        payNowButton = findViewById(R.id.payNowButton);

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(PaymentScreenActivity.this, PaymentSuccessScreenActivity.class);
                startActivity(i);

            }
        });

    }



}
