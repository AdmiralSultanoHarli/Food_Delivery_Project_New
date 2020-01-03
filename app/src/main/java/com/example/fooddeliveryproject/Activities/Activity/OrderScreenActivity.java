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

import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class OrderScreenActivity extends AppCompatActivity {

    ImageView backButton;
    RadioButton radioButtonGrab, radioButtonGojek, radioButtonOvo, radioButtonGopay;
    Button buttonPayment;
    TextView textCount;

    public Button accNotes;
    public EditText editNotes;

    public SlidingUpPanelLayout slidingPanel;
    public boolean slideOpened = false;
    public boolean wishlistAdded = false;

    public int counter;

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

        //slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        editNotes.addTextChangedListener(textCounter);
        accNotes.setEnabled(false);

        /*editNotes.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/



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
