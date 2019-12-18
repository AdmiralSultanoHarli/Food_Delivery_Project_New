package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;

public class OrderScreenActivity extends AppCompatActivity {

    ImageView backButton;
    RadioButton radioButtonGrab, radioButtonGojek, radioButtonOvo, radioButtonGopay;
    Button buttonPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        backButton = findViewById(R.id.backButton);
        radioButtonGrab = findViewById(R.id.radioButtonGrab);
        radioButtonGojek = findViewById(R.id.radioButtonGojek);
        radioButtonOvo = findViewById(R.id.radioButtonOvo);
        radioButtonGopay = findViewById(R.id.radioButtonGopay);
        buttonPayment = findViewById(R.id.buttonPayment);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        radioButtonGrab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButtonGrab.isChecked()){

                    radioButtonGrab.setChecked(true);
                    radioButtonGojek.setChecked(false);

                }

            }
        });

        radioButtonGojek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButtonGojek.isChecked()){

                    radioButtonGojek.setChecked(true);
                    radioButtonGrab.setChecked(false);

                }

            }
        });

        radioButtonOvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioButtonOvo.isChecked()){

                    radioButtonOvo.setChecked(true);
                    radioButtonGopay.setChecked(false);

                }

            }
        });

        radioButtonGopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButtonGopay.isChecked()) {

                    radioButtonGopay.setChecked(true);
                    radioButtonOvo.setChecked(false);

                }
            }
        });

        buttonPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(OrderScreenActivity.this, PaymentScreenActivity.class);
                startActivity(i);

            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        OrderScreenOrderFragment orderScreenOrderFragment = new OrderScreenOrderFragment();
        fragmentTransaction.add(R.id.orderSummaryFragment, orderScreenOrderFragment, orderScreenOrderFragment.getTag());

        fragmentTransaction.commit();

    }



}
