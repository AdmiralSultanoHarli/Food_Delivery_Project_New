package com.example.fooddeliveryproject.Activities.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fooddeliveryproject.Activities.Model.Data;

import java.security.PublicKey;
import java.util.ArrayList;

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
    public static final String COLUMN_FOOD_TRANSACTION_ID = "foodtransactionid";
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

        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + " (" +
                COLUMN_FOOD_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_FOOD_NAME + " TEXT, " +
                COLUMN_FOOD_DESC + " TEXT, " +
                COLUMN_FOOD_PRICE + " TEXT, " +
                COLUMN_FOOD_PRICE_DISCOUNT + " TEXT " + " )";

        String CREATE_FOOD_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_FOOD_TRANSACTION + " (" +
                COLUMN_FOOD_TRANSACTION_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_FOOD_QUANTITY + " TEXT, " +
                COLUMN_FOOD_ALLQUANTITY + " TEXT, " +
                COLUMN_FOOD_PRICE_TOTAL + " TEXT, " +
                COLUMN_FOOD_PRICE_DISCOUNT_TOTAL + " TEXT " + " )";

        String DATA_FOOD = "INSERT INTO " + TABLE_FOOD + "(foodname, fooddesc, foodprice, foodpricediscount) " +
                "VALUES ('Veg Thali', '3 puri + 2 vegetable dish + rice + dal + sweet', 30000, 40000), " +
                "('Goan Special', '3 Goan special dish + rice + dal + sweet', 50000, 65000), " +
                "('Butter Chicken', '3 Butter roti + butter chicken + rice', 40000, 55000), " +
                "('Bhendi Masala', '3 Butter roti + bhendi masala + rice', 40000, 50000), " +
                "('Murg Musallam', '3 Butter roti + roasted chicken + rice', 100000, 1150000), " +
                "('Basmati Rice Chicken Biryani', 'Basmati rice chicken biryani', 50000, 30000), " +
                "('Jeera Alo', '3 Butter roti + Jeera alo + rice', 30000, 40000), " +
                "('Mix Veggies', '3 Butter roti + Mix veggies + rice', 40000, 45000), " +
                "('Panang Curry', '3 Butter roti + Mix veggies + rice', 20000, 35000), " +
                "('Chapati', '3 Butter roti + mix chapati + rice', 50000, 65000), " +
                "('Samosa', '1 Butter roti + vegitables + rice', 10000, 25000)";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(DATA_FOOD);
        db.execSQL(CREATE_FOOD_TABLE_TRANSACTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_TRANSACTION);
        onCreate(db);

    }

    public ArrayList<Data> listData(){

        String sql = "select * from " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Data> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int foodId = Integer.parseInt(cursor.getString(0));
                String foodName = cursor.getString(1);
                storeData.add(new Data(foodId, foodName));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public void addData(Data data){

        ContentValues values = new ContentValues();
        if (values.equals(COLUMN_FOOD_NAME)){

            return;

        }

        values.put(COLUMN_FOOD_NAME, data.getFoodName());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_FOOD, null, values);

    }

}
