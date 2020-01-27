package com.example.fooddeliveryproject.Activities.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "khanaval.db";

    //User Data
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_USER_PHONENUMBER = "phonenumber";
    public static final String COLUMN_USER_PASSWORD = "password";

    //Food Data
    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_FOOD_ID = "foodid";
    public static final String COLUMN_FOOD_NAME = "foodname";
    public static final String COLUMN_FOOD_DESC = "fooddesc";
    public static final String COLUMN_FOOD_PRICE = "foodprice";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT = "foodpricediscount";
    public static final String COLUMN_BUTTON = "button";
    public static final String COLUMN_FOOD_IMG = "foodimage";

    //Food Transaction Data
    public static final String TABLE_FOOD_TRANSACTION = "foodtransaction";
    public static final String COLUMN_FOOD_TRANSACTION_ID = "foodtransactionid";
    public static final String COLUMN_FOOD_QUANTITY = "foodquantity";
    public static final String COLUMN_FOOD_ALLQUANTITY = "foodallquantity";
    public static final String COLUMN_FOOD_PRICE_TOTAL = "foodpricetotal";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT_TOTAL = "foodpricediscounttotal";

    //BestCusine Fragment Data
    public static final String TABLE_BESTCUSINE_FRAGMENT = "bestcusinefragment";
    public static final String COLUMN_BESTCUSINE_ID = "foodid";
    public static final String COLUMN_BESTCUSINE_NAME = "foodname";
    public static final String COLUMN_BESTCUSINE_IMG = "foodimage";

    //TodaysSpecial Fragment Data
    public static final String TABLE_TODAYSPECIAL_FRAGMENT = "todaysspecialfragment";
    public static final String COLUMN_TODAYSPECIAL_ID = "foodid";
    public static final String COLUMN_TODAYSPECIAL_NAME = "foodname";
    public static final String COLUMN_TODAYSPECIAL_IMG = "foodimage";

    //YourFavourites Fragment Data
    public static final String TABLE_YOURFAVOURITES_FRAGMENT = "yourfavouritesfragment";
    public static final String COLUMN_YOURFAVOURITES_ID = "foodid";
    public static final String COLUMN_YOURFAVOURITES_NAME = "foodname";
    public static final String COLUMN_YOURFAVOURITES_IMG = "foodimage";

    //Custom Fragment Data
    public static final String TABLE_CUSTOM_FRAGMENT = "customfragment";
    public static final String COLUMN_CUSTOM_ID = "foodid";
    public static final String COLUMN_CUSTOM_NAME = "foodname";
    public static final String COLUMN_CUSTOM_IMG = "foodimage";

    //Image library
    public static int basmati = R.drawable.basmati_rice;
    public static int murg_musallam = R.drawable.murg_musallam;
    public static int bhindi_masala = R.drawable.bhindi_masala;
    public static int panang_curry = R.drawable.panang_curry;
    public static int beverage = R.drawable.beverage;
    public static int snacks = R.drawable.snacks;
    public static int sweets = R.drawable.sweets;
    public static int mix_veggies = R.drawable.mix_veggies;
    public static int maharashtra_thali = R.drawable.maharashtra_thali;
    public static int jeera_alo = R.drawable.jeera_alo;
    public static int goan_special = R.drawable.goan_vegetarian_thali;
    public static int dal_tadkda = R.drawable.dal_tadkda;
    public static int chow_mein = R.drawable.chow_mein;
    public static int chapati = R.drawable.chapati;
    public static int butter_chicken = R.drawable.butter_chicken;
    public static int basmati_chicken_biryani = R.drawable.basmati_rice_chicken_biryani;

    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
//bryan
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
                COLUMN_FOOD_PRICE_DISCOUNT + " TEXT, " +
                COLUMN_BUTTON + " INTEGER, " +
                COLUMN_FOOD_IMG + " BLOB " + " )";

        String CREATE_FOOD_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_FOOD_TRANSACTION + " (" +
                COLUMN_FOOD_TRANSACTION_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_FOOD_QUANTITY + " TEXT, " +
                COLUMN_FOOD_ALLQUANTITY + " TEXT, " +
                COLUMN_FOOD_PRICE_TOTAL + " TEXT, " +
                COLUMN_FOOD_PRICE_DISCOUNT_TOTAL + " TEXT " + " )";

        String CREATE_BESTCUSINE_TABLE = "CREATE TABLE " + TABLE_BESTCUSINE_FRAGMENT + " (" +
                COLUMN_BESTCUSINE_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_BESTCUSINE_NAME + " TEXT, " +
                COLUMN_BESTCUSINE_IMG + " BLOB " + " )";

        String CREATE_TODAYSPECIAL_TABLE = "CREATE TABLE " + TABLE_TODAYSPECIAL_FRAGMENT + " (" +
                COLUMN_TODAYSPECIAL_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TODAYSPECIAL_NAME + " TEXT, " +
                COLUMN_TODAYSPECIAL_IMG + " BLOB " + " )";

        String CREATE_YOURFAVOURITES_TABLE = "CREATE TABLE " + TABLE_YOURFAVOURITES_FRAGMENT + " (" +
                COLUMN_YOURFAVOURITES_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_YOURFAVOURITES_NAME + " TEXT, " +
                COLUMN_YOURFAVOURITES_IMG + " BLOB " + " )";

        String CREATE_CUSTOM_TABLE = "CREATE TABLE " + TABLE_CUSTOM_FRAGMENT + " (" +
                COLUMN_CUSTOM_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_CUSTOM_NAME + " TEXT, " +
                COLUMN_CUSTOM_IMG + " BLOB " + " )";

        String DATA_FOOD = "INSERT INTO " + TABLE_FOOD + "(foodname, fooddesc, foodprice, foodpricediscount, button, foodimage) " +
                "VALUES ('Veg Thali', '3 puri + 2 vegetable dish + rice + dal + sweet', 30000, 40000, 0," + R.drawable.maharashtra_thali + ")," +
                "('Goan Special', '3 Goan special dish + rice + dal + sweet', 50000, 65000, 0," + R.drawable.goan_vegetarian_thali + ")," +
                "('Butter Chicken', '3 Butter roti + butter chicken + rice', 40000, 55000, 0," + R.drawable.butter_chicken + ")," +
                "('Bhendi Masala', '3 Butter roti + bhendi masala + rice', 40000, 50000, 0," + R.drawable.bhindi_masala + ")," +
                "('Murg Musallam', '3 Butter roti + roasted chicken + rice', 100000, 1150000, 1," + R.drawable.murg_musallam + ")," +
                "('Basmati Rice Chicken Biryani', 'Basmati rice chicken biryani', 50000, 30000, 0," + R.drawable.basmati_rice_chicken_biryani + ")," +
                "('Jeera Alo', '3 Butter roti + Jeera alo + rice', 30000, 40000, 0," + R.drawable.jeera_alo + ")," +
                "('Mix Veggies', '3 Butter roti + Mix veggies + rice', 40000, 45000, 0," + R.drawable.mix_veggies + ")," +
                "('Panang Curry', '3 Butter roti + Mix veggies + rice', 20000, 35000, 0," + R.drawable.panang_curry + ")," +
                "('Chapati', '3 Butter roti + mix chapati + rice', 50000, 65000, 0," + R.drawable.chapati + ")," +
                "('Samosa', '1 Butter roti + vegitables + rice', 10000, 25000, 0," + R.drawable.snacks + ")";

        String DATA_BESTCUSINE = "INSERT INTO " + TABLE_BESTCUSINE_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Thai Special'," + panang_curry + ")," +
                "('Indian'," + dal_tadkda + ")," +
                "('Chinese'," + chow_mein + ")";

        String DATA_TODAYSPECIAL = "INSERT INTO " + TABLE_TODAYSPECIAL_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Goan Special'," + goan_special + ")," +
                "('Maha Thali'," + maharashtra_thali + ")," +
                "('Panang Curry'," + panang_curry + ")," +
                "('Chapati'," + chapati + ")";

        String DATA_YOURFAVOURITES = "INSERT INTO " + TABLE_YOURFAVOURITES_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Maha Thali'," + maharashtra_thali + ")," +
                "('Samosa'," + snacks + ")," +
                "('Murg Musallam'," + murg_musallam + ")," +
                "('Goan Special'," + goan_special + ")";

        String DATA_CUSTOM = "INSERT INTO " + TABLE_CUSTOM_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Beverage'," + beverage + ")," +
                "('Snacks'," + snacks + ")," +
                "('Sweets'," + sweets + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_FOOD_TABLE_TRANSACTION);

        db.execSQL(CREATE_BESTCUSINE_TABLE);
        db.execSQL(CREATE_TODAYSPECIAL_TABLE);
        db.execSQL(CREATE_YOURFAVOURITES_TABLE);
        db.execSQL(CREATE_CUSTOM_TABLE);

        db.execSQL(DATA_FOOD);

        db.execSQL(DATA_BESTCUSINE);
        db.execSQL(DATA_TODAYSPECIAL);
        db.execSQL(DATA_YOURFAVOURITES);
        db.execSQL(DATA_CUSTOM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BESTCUSINE_FRAGMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODAYSPECIAL_FRAGMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_YOURFAVOURITES_FRAGMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOM_FRAGMENT);
        onCreate(db);

    }

    public ArrayList<DataKhanaval> listDataMenuScreen(){

        String sql = "select * from " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int foodId = Integer.parseInt(cursor.getString(0));
                String foodName = cursor.getString(1);
                String foodDesc = cursor.getString(2);
                int foodPrice = Integer.parseInt(cursor.getString(3));
                int foodPriceDiscount = Integer.parseInt(cursor.getString(4));
                int button = cursor.getInt(5);
                int foodImg = Integer.parseInt(cursor.getString(6));
                storeData.add(new DataKhanaval(foodId, foodName, foodDesc, foodPrice, foodPriceDiscount, button, foodImg));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataBestCusine(){

        String sql = "select * from " + TABLE_BESTCUSINE_FRAGMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int foodTestId = Integer.parseInt(cursor.getString(0));
                String foodTestName = cursor.getString(1);
                int img = Integer.parseInt(cursor.getString(2));
                storeData.add(new DataKhanaval(foodTestId, foodTestName, img));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataTodaySpecial(){

        String sql = "select * from " + TABLE_TODAYSPECIAL_FRAGMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int foodTestId = Integer.parseInt(cursor.getString(0));
                String foodTestName = cursor.getString(1);
                int img = Integer.parseInt(cursor.getString(2));
                storeData.add(new DataKhanaval(foodTestId, foodTestName, img));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataYourFavourites(){

        String sql = "select * from " + TABLE_YOURFAVOURITES_FRAGMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int foodTestId = Integer.parseInt(cursor.getString(0));
                String foodTestName = cursor.getString(1);
                int img = Integer.parseInt(cursor.getString(2));
                storeData.add(new DataKhanaval(foodTestId, foodTestName, img));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataCustom(){

        String sql = "select * from " + TABLE_CUSTOM_FRAGMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int foodTestId = Integer.parseInt(cursor.getString(0));
                String foodTestName = cursor.getString(1);
                int img = Integer.parseInt(cursor.getString(2));
                storeData.add(new DataKhanaval(foodTestId, foodTestName, img));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }


    /*public void addData(Data data){

        ContentValues values = new ContentValues();
        if (values.equals(COLUMN_FOOD_NAME)){

            return;

        }

        values.put(COLUMN_FOOD_NAME, data.getFoodName());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_FOOD, null, values);

    }*/

}
