package com.example.fooddeliveryproject.Activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;

public class PaymentSuccessScreenActivity extends BaseActivity {

    ImageView homeButton;
    Button buttonMyOrder;
    TextView paymentAmount, paymentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success_screen);

        homeButton = findViewById(R.id.homeButton);
        buttonMyOrder = findViewById(R.id.buttonMyOrder);
        paymentAmount = findViewById(R.id.paymentAmount);
        paymentMode = findViewById(R.id.paymentMode);

        DecimalHelper decimalHelper = new DecimalHelper();

        paymentMode.setText(SaveSharedPreference.getPaymentName(this, ""));

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentMoving();

            }
        });

        buttonMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentMoving();
                SaveSharedPreference.setFragmentOrderOpened(PaymentSuccessScreenActivity.this, true);


            }
        });

        paymentAmount.setText(decimalHelper.formatter(SaveSharedPreference.getTotalPayment(this, 0)));

    }

    public void intentMoving(){

        Intent i = new Intent(PaymentSuccessScreenActivity.this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(PaymentSuccessScreenActivity.this, HomeScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

    }

}
