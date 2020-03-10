package com.example.fooddeliveryproject.Activities.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import static com.example.fooddeliveryproject.Activities.Database.DatabaseHelper.TABLE_FOOD_NEW;

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

    ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);

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

                progressBar.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                String pattern = "dd MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "UK"));
                String date = simpleDateFormat.format(new Date());
                SaveSharedPreference.setDate(PaymentScreenActivity.this, date);

                final int foodPrice = 0;
                final int foodPriceTotal = 0;
                final int isCartOpened = 0;
                final int itemCount = 0;

                final String shopName = SaveSharedPreference.getFoodCategory(PaymentScreenActivity.this, "");
                final int totalPayment = SaveSharedPreference.getTotalPayment(PaymentScreenActivity.this, 0);
                final String dates = SaveSharedPreference.getDate(PaymentScreenActivity.this, "");
                final String paymentMethod = SaveSharedPreference.getPaymentName(PaymentScreenActivity.this, "");
                final String orderTracker = "Food Is Preparing";
                final String location = SaveSharedPreference.getLocationName(PaymentScreenActivity.this, "");
                final int shopImg = SaveSharedPreference.getFoodShopImg(PaymentScreenActivity.this, 0);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        progressBar.setVisibility(View.INVISIBLE);

                        if(SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 1){

                            if (SaveSharedPreference.getMubalance(getApplicationContext(), 0) > 32000 &&
                                    SaveSharedPreference.getMubalance(getApplicationContext(), 0) >= paymentTotalInt) {

                                int muamalatBalanceInt = muamalatBalance - paymentTotalInt;

                                if (muamalatBalanceInt <= 32000){

                                    Toast.makeText(PaymentScreenActivity.this, "We're really sorry the current balance must be 32.000", Toast.LENGTH_LONG).show();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                }else {

                                    SaveSharedPreference.setMuBalance(PaymentScreenActivity.this, muamalatBalanceInt);
                                    paymentSuccessReset();
                                    resetData(foodPrice, foodPriceTotal, isCartOpened, itemCount);
                                    deleteDataTrans();
                                    insertTransactionDoneData(shopName, totalPayment, dates, paymentMethod, orderTracker, location, shopImg);
                                    deleteDataNew();
                                    insertDataNew();

                                }

                            }else {

                                Toast.makeText(PaymentScreenActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            }
                        }else if (SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 2){

                            if (SaveSharedPreference.getOvoBalance(getApplicationContext(), 0) > 32000 &&
                                    SaveSharedPreference.getOvoBalance(getApplicationContext(), 0) >= paymentTotalInt) {

                                int ovoBalanceint = ovoBalance - paymentTotalInt;

                                if (ovoBalanceint <= 32000){

                                    Toast.makeText(PaymentScreenActivity.this, "We're really sorry the current balance must be 32.000", Toast.LENGTH_LONG).show();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                }else {

                                    SaveSharedPreference.setOvoBalance(PaymentScreenActivity.this, ovoBalanceint);
                                    paymentSuccessReset();
                                    resetData(foodPrice, foodPriceTotal, isCartOpened, itemCount);
                                    deleteDataTrans();
                                    insertTransactionDoneData(shopName, totalPayment, dates, paymentMethod, orderTracker, location, shopImg);
                                    deleteDataNew();
                                    insertDataNew();

                                }

                            }else {

                                Toast.makeText(PaymentScreenActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            }

                        }else if(SaveSharedPreference.getPaymentMethodName(getApplicationContext(), 0) == 3){

                            if (SaveSharedPreference.getGopayBalance(getApplicationContext(), 0) > 32000 &&
                                    SaveSharedPreference.getGopayBalance(getApplicationContext(), 0) >= paymentTotalInt) {

                                int gopayBalanceInt = gopayBalance - paymentTotalInt;

                                if (gopayBalanceInt <= 32000){

                                    Toast.makeText(PaymentScreenActivity.this, "We're really sorry the current balance must be 32.000", Toast.LENGTH_LONG).show();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                }else {

                                    SaveSharedPreference.setGopayBalance(PaymentScreenActivity.this, gopayBalanceInt);
                                    paymentSuccessReset();
                                    resetData(foodPrice, foodPriceTotal, isCartOpened, itemCount);
                                    deleteDataTrans();
                                    insertTransactionDoneData(shopName, totalPayment, dates, paymentMethod, orderTracker, location, shopImg);
                                    deleteDataNew();
                                    insertDataNew();

                                }
                            }else {

                                Toast.makeText(PaymentScreenActivity.this, "Not Enough Balance", Toast.LENGTH_SHORT).show();
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            }

                        }

                    }
                }, 3000);

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

        db.update(DatabaseHelper.TABLE_FOOD, contentValues, null, null);
        //db.update(DatabaseHelper.TABLE_FOOD, contentValues, DatabaseHelper.COLUMN_FOOD_ID +"= ?", new String[]{"2"});

    }

    public void deleteDataTrans(){

        db.delete(DatabaseHelper.TABLE_FOOD_TRANSACTION, null, null);

    }

    public void deleteDataNew(){

        db.delete(TABLE_FOOD_NEW, null, null);

    }

    public void insertTransactionDoneData(String shopName, int totalPayment, String date,
                           String paymentMethod, String orderTracker, String location ,int shopImg){

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_SHOP_NAME, shopName);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_TOTAL_PAYMENT, totalPayment);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_DATE, date);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_PAYMENT_METHOD, paymentMethod);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_ORDER_TRACKER, orderTracker);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_LOCATION, location);
        contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_SHOP_IMG, shopImg);

        db.insert(DatabaseHelper.TABLE_TRANSACTION_DONE, null, contentValues);

    }

    public void insertDataNew(){

        String DATA_FOOD_NEW = "INSERT INTO " + TABLE_FOOD_NEW + "(foodnamenew, fooddescnew, foodpricenew, foodpricediscountnew, foodpricetotalnew, foodpricediscounttotalnew, buttoncartquantityopenednew, fooditemcountnew, foodimagenew) " +
                "VALUES ('Nasi Padang', '3 nasi + 2 ayam + sayuran + sambel', 30000, 40000, 0, 0, 0, 0," + R.drawable.nasi_padang_s + ")," +
                "('Bakso', '2 Bakso besar + 5 Bakso kecil + bihun + bawang', 50000, 65000, 0, 0, 0, 0," + R.drawable.bakso + ")," +
                "('Soto', 'Suwiran Ayam + Telor + Nasi', 40000, 55000, 0, 0, 0, 0," + R.drawable.soto + ")," +
                "('Sate Ayam', '10 Tusuk sate ayam + bumbu kacang + nasi', 40000, 50000, 0, 0, 0, 0," + R.drawable.sate_ayam + ")," +
                "('Sate Padang', '20 Tusuk sate padang + bumbu sate padang + lontong', 100000, 115000, 0, 0, 0, 0," + R.drawable.sate_padang + ")," +
                "('Nasi Lemak', 'Nasi lemak + kacang + telor _ sambal', 50000, 30000, 0, 0, 0, 0," + R.drawable.nasi_lemak + ")," +
                "('Chinese Food', '1 porsi spagheti chinese + sayuran', 40000, 45000, 0, 0, 0, 0," + R.drawable.chow_mein + ")," +
                "('Indian Curry', '3 roti butter + Mix sayuran + nasi', 20000, 35000, 0, 0, 0, 0," + R.drawable.maharashtra_thali + ")," +
                "('Panang Curry', '3 Curry ayam + sayuran', 50000, 65000, 0, 0, 0, 0," + R.drawable.panang_curry + ")," +
                "('Samosa', '10 Aneka gorengan india', 30000, 45000, 0, 0, 0, 0," + R.drawable.snacks + ")";

        db.execSQL(DATA_FOOD_NEW);

    }

}
