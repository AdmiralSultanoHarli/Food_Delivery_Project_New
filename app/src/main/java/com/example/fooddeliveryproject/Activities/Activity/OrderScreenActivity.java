package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class OrderScreenActivity extends AppCompatActivity {

    ImageView backButton;
    RadioButton radioButtonGrab, radioButtonGojek, radioButtonOvo, radioButtonGopay;
    Button buttonPayment;
    TextView textCount,foodCategoriesPrice, deliveryPrice, totalPrice, totalPriceBar, viewPayment;

    public Button accNotes;
    public EditText editNotes;

    public SlidingUpPanelLayout slidingPanel;
    public boolean slideOpened = false;
    public boolean wishlistAdded = false;

    public int counter;

    public int gopay = 10000;
    public int ovo = 9000;
    public int driverPrice;
    public int foodPrice;
    public int tax = 4000;

    int allFoodPrice;

    int ovoImage = R.drawable.ovo2;
    int gopayImage = R.drawable.gopay2;

    int ovoColor = R.color.ovo;
    int gopayColor = R.color.gopay;

    int ovoButton = R.drawable.rounded_button_payment_ovo;
    int gopayButton = R.drawable.rounded_button_payment_gopay;

    int roundedOvo = R.drawable.rounded_view_ovo;
    int roundedGopay = R.drawable.rounded_view_gopay;

    String ovoName = "OVO";
    String gopayName = "Gopay";


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
        slidingPanel = findViewById(R.id.activity_main);
        accNotes = findViewById(R.id.accNotes);
        textCount = findViewById(R.id.textCount);
        editNotes = findViewById(R.id.editNotes);
        foodCategoriesPrice = findViewById(R.id.foodCategoriesPrice);
        deliveryPrice = findViewById(R.id.deliveryPrice);
        totalPrice = findViewById(R.id.totalPrice);
        totalPriceBar = findViewById(R.id.totalPriceBar);
        viewPayment = findViewById(R.id.viewPayment);



        //slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        editNotes.addTextChangedListener(textCounter);
        accNotes.setEnabled(false);

        /*editNotes.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/

        radioButtonGrab.setChecked(true);
        radioButtonOvo.setChecked(true);

        if (radioButtonGrab.isChecked()){

            grabRadioButton();

        }

        if (radioButtonOvo.isChecked()){

            ovoRadioButton();

        }

        foodCategoriesPrice.setText(String.valueOf(SaveSharedPreference.getFoodPriceTotal(this, 0)));

        slidingPanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (slidingPanel.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN){

                    editNotes.setEnabled(false);
                    slideOpened = false;
                    return false;

                }

                return true;
            }
        });


        accNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                editNotes.setEnabled(false);
                slideOpened = false;

            }
        });

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

                    grabRadioButton();

                }

            }
        });

        radioButtonGojek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButtonGojek.isChecked()){

                    gojekRadioButton();

                }

            }
        });

        radioButtonOvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioButtonOvo.isChecked()){

                    ovoRadioButton();

                }

            }
        });

        radioButtonGopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButtonGopay.isChecked()) {

                    gopayRadioButton();

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

    public final TextWatcher textCounter = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence.length() > 0 && charSequence.length() <= 200 ){

                textCount.setText(String.valueOf(charSequence.length()) + "/200");
                counter = charSequence.length();
                accNotes.setEnabled(true);
                accNotes.setBackgroundResource(R.drawable.rounded_button_add_active);

            }else if (charSequence.length() == 0){

                textCount.setText(String.valueOf(charSequence.length()) + "/200");
                counter = charSequence.length();
                accNotes.setEnabled(false);
                accNotes.setBackgroundResource(R.drawable.rounded_button_add_nonactive);

            }else if(charSequence.length() > 200){

                return;

            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void gopayRadioButton(){

        radioButtonGopay.setChecked(true);
        radioButtonOvo.setChecked(false);
        SaveSharedPreference.setImagePayment(OrderScreenActivity.this, gopayImage);
        SaveSharedPreference.setColorPayment(OrderScreenActivity.this, gopayColor);
        //SaveSharedPreference.setButtonColor(OrderScreenActivity.this, gopayButton);
        SaveSharedPreference.setPaymentName(OrderScreenActivity.this, gopayName);
        SaveSharedPreference.setPaymentMethodName(OrderScreenActivity.this, false);
        viewPayment.setBackgroundResource(roundedGopay);
        //buttonPayment.setBackgroundResource(gopayButton);
        viewPayment.setText(gopayName);

    }

    public void ovoRadioButton(){

        radioButtonOvo.setChecked(true);
        radioButtonGopay.setChecked(false);
        SaveSharedPreference.setImagePayment(OrderScreenActivity.this, ovoImage);
        SaveSharedPreference.setColorPayment(OrderScreenActivity.this, ovoColor);
        //SaveSharedPreference.setButtonColor(OrderScreenActivity.this, ovoButton);
        SaveSharedPreference.setPaymentName(OrderScreenActivity.this, ovoName);
        SaveSharedPreference.setPaymentMethodName(OrderScreenActivity.this, true);
        viewPayment.setBackgroundResource(roundedOvo);
        //buttonPayment.setBackgroundResource(ovoButton);
        viewPayment.setText(ovoName);

    }

    public void gojekRadioButton(){

        foodPrice = SaveSharedPreference.getFoodPriceTotal(getApplicationContext(), 0);
        driverPrice = gopay;
        deliveryPrice.setText(String.valueOf(driverPrice));
        allFoodPrice = driverPrice + tax + foodPrice;
        totalPrice.setText(String.valueOf(allFoodPrice));
        totalPriceBar.setText(String.valueOf(allFoodPrice));
        radioButtonGojek.setChecked(true);
        radioButtonGrab.setChecked(false);
        SaveSharedPreference.setTotalPayment(OrderScreenActivity.this, allFoodPrice);

    }

    public void grabRadioButton(){

        foodPrice = SaveSharedPreference.getFoodPriceTotal(getApplicationContext(), 0);
        driverPrice = ovo;
        deliveryPrice.setText(String.valueOf(driverPrice));
        allFoodPrice = driverPrice + tax + foodPrice;
        totalPrice.setText(String.valueOf(allFoodPrice));
        totalPriceBar.setText(String.valueOf(allFoodPrice));
        radioButtonGrab.setChecked(true);
        radioButtonGojek.setChecked(false);
        SaveSharedPreference.setTotalPayment(OrderScreenActivity.this, allFoodPrice);

    }

    @Override
    public void onBackPressed() {

        if (slideOpened == true){

            slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            slideOpened = false;

        }else {

            super.onBackPressed();

        }
    }
}
