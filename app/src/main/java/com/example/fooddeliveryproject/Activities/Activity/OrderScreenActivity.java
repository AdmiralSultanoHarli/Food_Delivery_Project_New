package com.example.fooddeliveryproject.Activities.Activity;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderAddOnFragment;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderPaymentDetailsFragment;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

import java.util.List;
import java.util.Locale;

public class OrderScreenActivity extends BaseActivity {

    ImageView backButton, imageViewMap;
    RadioButton radioButtonGrab, radioButtonGojek, radioButtonOvo, radioButtonGopay, radioButtonMuamalat;
    Button buttonPayment;
    TextView textCount, deliveryPrice, viewPayment, textViewAddMore, promoAppliedText,
            placeName, placeDescription, textViewChangeAddress;

    public static TextView textViewAddOn;

    public static TextView totalPriceBar, totalPrice;

    public static Button accNotes;
    public static Button couponShower;
    public static EditText editNotes;

    ProgressBar progressBar;

    DecimalHelper decimalHelper;

    public static SlidingUpPanelLayout slidingPanel;
    public static boolean slideOpened = false;
    public boolean wishlistAdded = false;

    public static int counter;

    public int gopay = 10000;
    public int ovo = 9000;
    public int driverPrice;
    public int foodPrice;
    public int tax = 4000;

    public static int allFoodPrice;

    public static String editNotesString;

    int ovoImage = R.drawable.ovo2;
    int gopayImage = R.drawable.gopay2;
    int muamalatImage = R.drawable.muamalatname;

    int ovoColor = R.color.ovo;
    int gopayColor = R.color.gopay;
    int muamalatColor = R.color.muamalat;

    int ovoButton = R.drawable.rounded_button_payment_ovo;
    int gopayButton = R.drawable.rounded_button_payment_gopay;

    int roundedOvo = R.drawable.rounded_view_ovo;
    int roundedGopay = R.drawable.rounded_view_gopay;
    int roundedMuamalat = R.drawable.rounded_view_muamalat;

    String ovoName = "OVO";
    String gopayName = "Gopay";
    String muamalatName = "Bank Muamalat";

    float latitude, longitude;


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
        deliveryPrice = findViewById(R.id.deliveryPrice);
        totalPrice = findViewById(R.id.totalPrice);
        totalPriceBar = findViewById(R.id.totalPriceBar);
        viewPayment = findViewById(R.id.viewPayment);
        radioButtonMuamalat = findViewById(R.id.radioButtonMuamalat);
        couponShower = findViewById(R.id.couponShower);
        textViewAddMore = findViewById(R.id.textViewAddMore);
        promoAppliedText = findViewById(R.id.promoAppliedText);
        placeName = findViewById(R.id.placeName);
        placeDescription = findViewById(R.id.placeDescription);
        imageViewMap = findViewById(R.id.imageViewMap);
        textViewChangeAddress = findViewById(R.id.textViewChangeAddress);
        textViewAddOn = findViewById(R.id.textViewAddOn);
        progressBar = findViewById(R.id.progressBar);

        /*editNotesString = editNotes.getText().toString();*/

        decimalHelper = new DecimalHelper();

        latitude = SaveSharedPreference.getLatitude(this, 0);
        longitude = SaveSharedPreference.getLongitude(this, 0);

        editNotes.addTextChangedListener(textCounter);
        accNotes.setEnabled(false);

        placeName.setText(SaveSharedPreference.getLocationSimpleName(this, ""));
        placeDescription.setText(SaveSharedPreference.getLocationName(this, ""));

        Log.e("Exist", String.valueOf(SaveSharedPreference.getIsCouponExist(this, true)));

        if (SaveSharedPreference.getIsCouponExist(this, true) == false){

            couponShower.setText("View All");
            promoAppliedText.setText("No promo apply");

        }else if(SaveSharedPreference.getIsCouponExist(this, true) == true) {

            couponShower.setText("Change");
            promoAppliedText.setText("Promo applied");

        }

        imageViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapShower();

            }
        });

        textViewChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapShower();

            }
        });

        couponShower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(OrderScreenActivity.this, CouponScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        textViewAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(OrderScreenActivity.this, MenuScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        radioButtonGrab.setChecked(true);
        radioButtonMuamalat.setChecked(true);

        if (radioButtonGrab.isChecked()){

            grabRadioButton();

        }

        if (radioButtonMuamalat.isChecked()){

            muamalatRadioButton();

        }

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

        /*accNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                editNotes.setEnabled(false);
                slideOpened = false;

            }
        });*/

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

        radioButtonMuamalat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButtonMuamalat.isChecked()){

                    muamalatRadioButton();

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
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.INVISIBLE);

            }
        }, 2000);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        OrderScreenOrderFragment orderScreenOrderFragment = new OrderScreenOrderFragment();
        fragmentTransaction.replace(R.id.orderSummaryFragment, orderScreenOrderFragment, orderScreenOrderFragment.getTag());

        OrderAddOnFragment orderAddOnFragment = new OrderAddOnFragment();
        fragmentTransaction.replace(R.id.menuOrderAlsoOrderFragment, orderAddOnFragment, orderAddOnFragment.getTag());

        OrderPaymentDetailsFragment orderPaymentDetailsFragment = new OrderPaymentDetailsFragment();
        fragmentTransaction.replace(R.id.paymentDetailsFragment, orderPaymentDetailsFragment, orderPaymentDetailsFragment.getTag());

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


    public void muamalatRadioButton(){

        radioButtonMuamalat.setChecked(true);
        radioButtonOvo.setChecked(false);
        radioButtonGopay.setChecked(false);
        SaveSharedPreference.setImagePayment(OrderScreenActivity.this, muamalatImage);
        SaveSharedPreference.setColorPayment(OrderScreenActivity.this, muamalatColor);
        SaveSharedPreference.setPaymentName(OrderScreenActivity.this, muamalatName);
        SaveSharedPreference.setPaymentMethodName(OrderScreenActivity.this, 1);
        viewPayment.setTextColor(ContextCompat.getColor(getApplicationContext(),muamalatColor));
        viewPayment.setBackgroundResource(roundedMuamalat);
        viewPayment.setTextSize(15);
        viewPayment.setText(muamalatName);

    }

    public void ovoRadioButton(){

        radioButtonOvo.setChecked(true);
        radioButtonGopay.setChecked(false);
        radioButtonMuamalat.setChecked(false);
        SaveSharedPreference.setImagePayment(OrderScreenActivity.this, ovoImage);
        SaveSharedPreference.setColorPayment(OrderScreenActivity.this, ovoColor);
        SaveSharedPreference.setPaymentName(OrderScreenActivity.this, ovoName);
        SaveSharedPreference.setPaymentMethodName(OrderScreenActivity.this, 2);
        viewPayment.setTextColor(ContextCompat.getColor(getApplicationContext(),ovoColor));
        viewPayment.setBackgroundResource(roundedMuamalat);
        viewPayment.setText(ovoName);

    }

    public void gopayRadioButton(){

        radioButtonGopay.setChecked(true);
        radioButtonOvo.setChecked(false);
        radioButtonMuamalat.setChecked(false);
        SaveSharedPreference.setImagePayment(OrderScreenActivity.this, gopayImage);
        SaveSharedPreference.setColorPayment(OrderScreenActivity.this, gopayColor);
        SaveSharedPreference.setPaymentName(OrderScreenActivity.this, gopayName);
        SaveSharedPreference.setPaymentMethodName(OrderScreenActivity.this, 3);
        viewPayment.setTextColor(ContextCompat.getColor(getApplicationContext(),gopayColor));
        viewPayment.setBackgroundResource(roundedMuamalat);
        viewPayment.setTextSize(15);
        viewPayment.setText(gopayName);

    }

    public void gojekRadioButton(){

        foodPrice = SaveSharedPreference.getFoodPriceTotal(getApplicationContext(), 0);
        driverPrice = gopay;
        deliveryPrice.setText(decimalHelper.formatter(driverPrice));
        allFoodPrice = driverPrice + tax + foodPrice;
        totalPrice.setText(decimalHelper.formatter(allFoodPrice));
        totalPriceBar.setText(decimalHelper.formatter(allFoodPrice));
        radioButtonGojek.setChecked(true);
        radioButtonGrab.setChecked(false);
        SaveSharedPreference.setTotalPayment(OrderScreenActivity.this, allFoodPrice);

    }

    public void grabRadioButton(){

        foodPrice = SaveSharedPreference.getFoodPriceTotal(getApplicationContext(), 0);
        driverPrice = ovo;
        deliveryPrice.setText(decimalHelper.formatter(driverPrice));
        allFoodPrice = driverPrice + tax + foodPrice;
        totalPrice.setText(decimalHelper.formatter(allFoodPrice));
        totalPriceBar.setText(decimalHelper.formatter(allFoodPrice));
        radioButtonGrab.setChecked(true);
        radioButtonGojek.setChecked(false);
        SaveSharedPreference.setTotalPayment(OrderScreenActivity.this, allFoodPrice);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                try {
                    AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);


                    SaveSharedPreference.setLocationOpened(OrderScreenActivity.this, true);
                    double latitudeDoub = addressData.getLatitude();
                    double longitudeDoub = addressData.getLongitude();

                    latitude = (float) latitudeDoub;
                    longitude = (float) longitudeDoub;

                    SaveSharedPreference.setLatitude(this, (float) latitudeDoub);
                    SaveSharedPreference.setLongitude(this, (float) longitudeDoub);

                    getCompleteAddressString(latitude, longitude);

                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void mapShower(){

        Intent intent = new PlacePicker.IntentBuilder()
                .setLatLong(latitude, longitude)
                .showLatLong(true)
                .setMapRawResourceStyle(R.raw.map_style)
                .setFabColor(R.color.colorButton)
                .setAddressRequired(true)
                .setPrimaryTextColor(R.color.colorBlack)
                .setSecondaryTextColor(R.color.colorBlack)
                .setMapType(MapType.NORMAL)
                .setMarkerDrawable(R.drawable.ic_marker)
                .setMapZoom(16f)
                .build(OrderScreenActivity.this);

        startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);

    }

    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 3);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");

                }
                strAdd = strReturnedAddress.toString();
                String[] locationParts = strAdd.split(", ");
                String locationName = "";

                if (/*locationParts.length == 8 || locationParts.length == 4 || locationParts.length == 5
                        || locationParts.length == 6 || locationParts.length == 7*/locationParts.length < 9){

                    locationName = locationParts[0];

                }else if (/*locationParts.length == 9 || locationParts.length == 10 || locationParts.length == 12*/
                        locationParts.length >= 9){

                    locationName = locationParts[1];

                }

                Log.e("Location", locationName);

                Log.e("location parts size", String.valueOf(locationParts.length));

                Log.e("My Current location ", strReturnedAddress.toString());

                placeName.setText(locationName);
                placeDescription.setText(strAdd);

                SaveSharedPreference.setLocationName(this, strAdd);
                SaveSharedPreference.setLocationSimpleName(this, locationName);

            } else {
                Log.e("My Current location ", "No Address returned!");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("My Current location ", "Cannot get Address!");
        }
        return strAdd;
    }

    @Override
    public void onBackPressed() {

        if (slideOpened == true){

            slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            slideOpened = false;

        }else {

            Intent i = new Intent(OrderScreenActivity.this, MenuScreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
    }
}
