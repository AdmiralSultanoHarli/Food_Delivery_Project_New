package com.example.fooddeliveryproject.Activities.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

import javax.sql.StatementEvent;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "khanaval.db";

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_USER_PHONENUMBER = "phonenumber";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_FOOD_ID = "foodid";
    public static final String COLUMN_FOOD_NAME = "foodname";
    public static final String COLUMN_FOOD_DESC = "fooddesc";
    public static final String COLUMN_FOOD_PRICE = "foodprice";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT = "foodpricediscount";

    public static final String TABLE_FOOD_TRANSACTION = "foodtransaction";
    public static final String COLUMN_FOOD_QUANTITY = "foodquantity";
    public static final String COLUMN_FOOD_ALLQUANTITY = "foodallquantity";
    public static final String COLUMN_FOOD_PRICE_TOTAL = "foodpricetotal";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT_TOTAL = "foodpricediscounttotal";

    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_USER_PHONENUMBER + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT " + " )";

        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

    }


}
