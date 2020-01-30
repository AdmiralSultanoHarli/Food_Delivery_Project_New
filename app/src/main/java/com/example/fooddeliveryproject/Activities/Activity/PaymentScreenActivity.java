package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentScreenActivity extends AppCompatActivity {

    Button payNowButton;
    TextView cancelButton;
    public static ImageView imagePayment;
    public static TextView textViewPayUsing;
    public static TextView textViewBalance;
    public static TextView paymentTotal;
    public static TextView availableBalance;
    public static TextView minusPayment;
    public static View line;

    int ovoBalance;
    int gopayBalance;

    boolean whichPaymentMethod = false;
    boolean ovoBalanceChanged;
    boolean gopayBalanceChanged;

    int availableBalanceInt;
    boolean paymentSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);

        payNowButton = findViewById(R.id.payNowButton);
        cancelButton = findViewById(R.id.cancelButton);
        imagePayment = findViewById(R.id.imagePayment);
        textViewPayUsing = findViewById(R.id.textViewPayUsing);
        textViewBalance = findViewById(R.id.textViewBalance);
        paymentTotal = findViewById(R.id.paymentTotal);
        availableBalance = findViewById(R.id.availableBalance);
        minusPayment = findViewById(R.id.minusPayment);
        line = findViewById(R.id.line);

        imagePayment.setBackgroundResource(SaveSharedPreference.getImagePayment(this, 0));
        line.setBackgroundResource(SaveSharedPreference.getColorPayment(this, 0));
        textViewPayUsing.setText("Pay using " + SaveSharedPreference.getPaymentName(this, ""));
        textViewBalance.setText("Available " + SaveSharedPreference.getPaymentName(this, "") + " Balance");

        final int paymentTotalInt = SaveSharedPreference.getTotalPayment(this, 0);

        ovoBalance = SaveSharedPreference.getOvoBalance(this, 0);
        gopayBalance = SaveSharedPreference.getGopayBalance(this, 0);

        whichPaymentMethod = SaveSharedPreference.getPaymentMethodName(this, false);


        //Log.e("Image Resource", String.valueOf(SaveSharedPreference.getImagePayment(this, 0)));

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pattern = "dd MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "UK"));
                String date = simpleDateFormat.format(new Date());
                SaveSharedPreference.setDate(PaymentScreenActivity.this, date);

                if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), false) == true){

                    if (SaveSharedPreference.getOvoBalance(getApplicationContext(), 0) > 32000) {

                        int ovoBalanceint = ovoBalance - paymentTotalInt;
                        SaveSharedPreference.setOvoBalance(PaymentScreenActivity.this, ovoBalanceint);
                        //availableBalanceInt = SaveSharedPreference.getOvoBalance(getApplicationContext(), 0);
                        //availableBalance.setText(String.valueOf(availableBalanceInt));
                        paymentSelected = true;
                        Intent i = new Intent(PaymentScreenActivity.this, PaymentSuccessScreenActivity.class);
                        startActivity(i);

                    }else {

                        Toast.makeText(PaymentScreenActivity.this, "You're balance not enough", Toast.LENGTH_SHORT).show();

                    }

                }else if(SaveSharedPreference.getPaymentMethodName(getApplicationContext(), false) == false){

                    if (SaveSharedPreference.getGopayBalance(getApplicationContext(), 0) > 32000) {

                        int gopayBalanceInt = gopayBalance - paymentTotalInt;
                        SaveSharedPreference.setGopayBalance(PaymentScreenActivity.this, gopayBalanceInt);
                        //availableBalanceInt = SaveSharedPreference.getGopayBalance(getApplicationContext(), 0);
                        //availableBalance.setText(String.valueOf(availableBalanceInt));
                        paymentSelected = false;
                        Intent i = new Intent(PaymentScreenActivity.this, PaymentSuccessScreenActivity.class);
                        startActivity(i);

                    }else {

                        Toast.makeText(PaymentScreenActivity.this, "You're balance not enough", Toast.LENGTH_SHORT).show();

                    }

                }


            }

        });

        if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), false) == true){

            availableBalance.setText(String.valueOf(SaveSharedPreference.getOvoBalance(this, 0)));

        }else if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), false) == false){

            availableBalance.setText(String.valueOf(SaveSharedPreference.getGopayBalance(this, 0)));

        }

        Log.e("Ovo", String.valueOf(SaveSharedPreference.getOvoBalance(this, 0)));

        Log.e("Gopay", String.valueOf(SaveSharedPreference.getGopayBalance(this, 0)));

        Log.e("Which Payment", String.valueOf(whichPaymentMethod));


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


        paymentTotal.setText(String.valueOf(paymentTotalInt));
        minusPayment.setText("-" + SaveSharedPreference.getTotalPayment(this, 0));



        /*getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);*/

    }

    /*private void setWindowFlag(PaymentScreenActivity paymentScreenActivity, int bits, boolean on) {

        Window win = paymentScreenActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }*/



}
