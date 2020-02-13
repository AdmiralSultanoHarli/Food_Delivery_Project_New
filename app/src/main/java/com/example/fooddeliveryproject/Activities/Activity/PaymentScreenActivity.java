package com.example.fooddeliveryproject.Activities.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentScreenActivity extends BaseActivity {

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
    int muamalatBalance;

    DataKhanaval dataKhanaval;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);

        DecimalHelper decimalHelper = new DecimalHelper();
        openHelper = new DatabaseHelper(this);
        helper = new DatabaseHelper(this);

        db = openHelper.getWritableDatabase();
        dataKhanaval = new DataKhanaval();

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
        muamalatBalance = SaveSharedPreference.getMubalance(this, 0);

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pattern = "dd MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "UK"));
                String date = simpleDateFormat.format(new Date());
                SaveSharedPreference.setDate(PaymentScreenActivity.this, date);

                int foodPrice = 0;
                int foodPriceTotal = 0;
                int isCartOpened = 0;
                int itemCount = 0;

                String shopName = SaveSharedPreference.getFoodCategory(PaymentScreenActivity.this, "");
                int totalPayment = SaveSharedPreference.getTotalPayment(PaymentScreenActivity.this, 0);
                String dates = SaveSharedPreference.getDate(PaymentScreenActivity.this, "");
                String paymentMethod = SaveSharedPreference.getPaymentName(PaymentScreenActivity.this, "");
                String orderTracker = "Food Is Preparing";
                int shopImg = SaveSharedPreference.getFoodShopImg(PaymentScreenActivity.this, 0);


                if(SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 1){

                    if (SaveSharedPreference.getMubalance(getApplicationContext(), 0) > 32000 &&
                            SaveSharedPreference.getMubalance(getApplicationContext(), 0) >= paymentTotalInt) {

                        int muamalatBalanceInt = muamalatBalance - paymentTotalInt;

                        if (muamalatBalanceInt <= 32000){

                            Toast.makeText(PaymentScreenActivity.this, "We're really sorry the current balance must be 32.000", Toast.LENGTH_LONG).show();

                        }else {

                            SaveSharedPreference.setMuBalance(PaymentScreenActivity.this, muamalatBalanceInt);
                            paymentSuccessReset();
                            resetData(foodPrice, foodPriceTotal, isCartOpened, itemCount);
                            deleteDataTrans();
                            insertTransactionDoneData(shopName, totalPayment, dates, paymentMethod, orderTracker, shopImg);

                        }

                    }else {

                        Toast.makeText(PaymentScreenActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();

                    }
                }else if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 2){

                    if (SaveSharedPreference.getOvoBalance(getApplicationContext(), 0) > 32000 &&
                            SaveSharedPreference.getOvoBalance(getApplicationContext(), 0) >= paymentTotalInt) {

                        int ovoBalanceint = ovoBalance - paymentTotalInt;

                        if (ovoBalanceint <= 32000){

                            Toast.makeText(PaymentScreenActivity.this, "We're really sorry the current balance must be 32.000", Toast.LENGTH_LONG).show();

                        }else {

                            SaveSharedPreference.setOvoBalance(PaymentScreenActivity.this, ovoBalanceint);
                            paymentSuccessReset();
                            resetData(foodPrice, foodPriceTotal, isCartOpened, itemCount);
                            deleteDataTrans();
                            insertTransactionDoneData(shopName, totalPayment, dates, paymentMethod, orderTracker, shopImg);

                        }

                    }else {

                        Toast.makeText(PaymentScreenActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();

                    }

                }else if(SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 3){

                    if (SaveSharedPreference.getGopayBalance(getApplicationContext(), 0) > 32000 &&
                            SaveSharedPreference.getGopayBalance(getApplicationContext(), 0) >= paymentTotalInt) {

                        int gopayBalanceInt = gopayBalance - paymentTotalInt;

                        if (gopayBalanceInt <= 32000){

                            Toast.makeText(PaymentScreenActivity.this, "We're really sorry the current balance must be 32.000", Toast.LENGTH_LONG).show();

                        }else {

                            SaveSharedPreference.setGopayBalance(PaymentScreenActivity.this, gopayBalanceInt);
                            paymentSuccessReset();
                            resetData(foodPrice, foodPriceTotal, isCartOpened, itemCount);
                            deleteDataTrans();
                            insertTransactionDoneData(shopName, totalPayment, dates, paymentMethod, orderTracker, shopImg);

                        }
                    }else {

                        Toast.makeText(PaymentScreenActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();

                    }

                }


            }

        });

        if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 1){

            availableBalance.setText(decimalHelper.formatter(SaveSharedPreference.getMubalance(this, 0)));

        }else if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 2){

            availableBalance.setText(decimalHelper.formatter(SaveSharedPreference.getOvoBalance(this, 0)));

        }else{

            availableBalance.setText(decimalHelper.formatter(SaveSharedPreference.getGopayBalance(this, 0)));

        }

        Log.e("Ovo", String.valueOf(SaveSharedPreference.getOvoBalance(this, 0)));

        Log.e("Gopay", String.valueOf(SaveSharedPreference.getGopayBalance(this, 0)));

        Log.e("Bank Muamalat", String.valueOf(SaveSharedPreference.getMubalance(this, 0)));


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


        paymentTotal.setText(decimalHelper.formatter(paymentTotalInt));
        minusPayment.setText("-" + decimalHelper.formatter(SaveSharedPreference.getTotalPayment(this, 0)));



        /*getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);*/

    }

    public void paymentSuccessReset(){

        SaveSharedPreference.setIsCouponExist(PaymentScreenActivity.this, false);
        SaveSharedPreference.setAllQuantity(PaymentScreenActivity.this, 0);
        SaveSharedPreference.setFoodPriceTotal(PaymentScreenActivity.this, 0);
        SaveSharedPreference.setFoodPriceDiscountTotal(PaymentScreenActivity.this, 0);
        Intent i = new Intent(PaymentScreenActivity.this, PaymentSuccessScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }


    public void resetData(int foodPriceTotal, int foodPriceDiscountTotal, int foodCartQuantityOpened, int foodItemCount){


        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_FOOD_PRICE_TOTAL, foodPriceTotal);
        contentValues.put(DatabaseHelper.COLUMN_FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
        contentValues.put(DatabaseHelper.COLUMN_BUTTON, foodCartQuantityOpened);
        contentValues.put(DatabaseHelper.COLUMN_FOOD_ITEM_COUNT, foodItemCount);

        // Updating direct to the database
        db.update(DatabaseHelper.TABLE_FOOD, contentValues, null, null);
        //db.update(DatabaseHelper.TABLE_FOOD, contentValues, DatabaseHelper.COLUMN_FOOD_ID +"= ?", new String[]{"2"});

    }

    public void deleteDataTrans(){

        // Delete data in TABLE FOOD TRANSACTION
        db.delete(DatabaseHelper.TABLE_FOOD_TRANSACTION, null, null);

    }

    public void insertTransactionDoneData(String shopName, int totalPayment, String date,
                           String paymentMethod, String orderTracker, int shopImg){

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_SHOP_NAME, shopName);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_TOTAL_PAYMENT, totalPayment);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_DATE, date);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_PAYMENT_METHOD, paymentMethod);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_ORDER_TRACKER, orderTracker);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_SHOP_IMG, shopImg);

        db.insert(DatabaseHelper.TABLE_TRANSACTION_DONE, null, contentValues);

    }

}
