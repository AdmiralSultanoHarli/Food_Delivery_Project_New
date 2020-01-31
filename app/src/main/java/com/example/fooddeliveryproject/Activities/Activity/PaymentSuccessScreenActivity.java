package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragment;
import com.example.fooddeliveryproject.R;

import org.w3c.dom.Text;

public class PaymentSuccessScreenActivity extends AppCompatActivity {

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

        paymentMode.setText(SaveSharedPreference.getPaymentName(this, ""));

        //buttonMyOrder.setBackgroundResource(SaveSharedPreference.getButtonColor(this, 0));

        SaveSharedPreference.setNoOrderComplete(this, 1);

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


        paymentAmount.setText(String.valueOf(SaveSharedPreference.getTotalPayment(this, 0)));

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
