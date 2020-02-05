package com.example.fooddeliveryproject.Activities.Database;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.security.PublicKey;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "khanaval.db";

    //User Data
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_USER_PHONENUMBER = "phonenumber";
    public static final String COLUMN_USER_PASSWORD = "password";

    //Restaurant Data
    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String COLUMN_REST_ID = "restaurantid";
    public static final String COLUMN_REST_NAME = "restaurantname";
    public static final String COLUMN_REST_ADDRESS = "restaurantaddress";
    public static final String COLUMN_REST_IMG = "restaurantimage";

    //Food Data
    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_FOOD_ID = "foodid";
    public static final String COLUMN_FOOD_NAME = "foodname";
    public static final String COLUMN_FOOD_DESC = "fooddesc";
    public static final String COLUMN_FOOD_PRICE = "foodprice";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT = "foodpricediscount";
    public static final String COLUMN_FOOD_PRICE_TOTAL = "foodpricetotal";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT_TOTAL = "foodpricediscounttotal";
    public static final String COLUMN_BUTTON = "buttoncartquantityopened";
    public static final String COLUMN_FOOD_ITEM_COUNT = "fooditemcount";
    public static final String COLUMN_FOOD_IMG = "foodimage";

    //Coupon Data
    public static final String TABLE_COUPON = "coupon";
    public static final String COLUMN_COUPON_ID = "couponid";
    public static final String COLUMN_COUPON_NAME = "couponname";
    public static final String COLUMN_COUPON_VALUE = "couponvalue";
    public static final String COLUMN_COUPON_VALID = "couponvalid";
    public static final String COLUMN_COUPON_IMG = "couponimage";

    //Food Transaction Data
    public static final String TABLE_FOOD_TRANSACTION = "foodtransaction";
    public static final String COLUMN_FOOD_TRANSACTION_ID = "foodtransactionid";
    public static final String COLUMN_FOOD_QUANTITY = "foodquantity";
    public static final String COLUMN_FOOD_ALLQUANTITY = "foodallquantity";
    public static final String COLUMN_FOOD_PRICE_TOTALS = "foodpricetotal";
    public static final String COLUMN_FOOD_PRICE_DISCOUNT_TOTALS = "foodpricediscounttotal";

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
    public static int murg_musallam = R.drawable.murg_musallam;
    public static int beverage = R.drawable.beverage;
    public static int snacks = R.drawable.snacks;
    public static int sweets = R.drawable.sweets;
    public static int maharashtra_thali = R.drawable.maharashtra_thali;
    public static int chapati = R.drawable.chapati;
    public static int butter_chicken = R.drawable.butter_chicken;
    public static int chow_mein = R.drawable.chow_mein;
    public static int panang_curry = R.drawable.panang_curry;


    // Unused
    /*public static int basmati = R.drawable.basmati_rice;
    public static int mix_veggies = R.drawable.mix_veggies;
    public static int basmati_chicken_biryani = R.drawable.basmati_rice_chicken_biryani;
    public static int jeera_alo = R.drawable.jeera_alo;
    public static int goan_special = R.drawable.goan_vegetarian_thali;
    public static int dal_tadkda = R.drawable.dal_tadkda;
    public static int bhindi_masala = R.drawable.bhindi_masala;*/

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
                COLUMN_FOOD_PRICE + " INTEGER, " +
                COLUMN_FOOD_PRICE_DISCOUNT + " INTEGER, " +
                COLUMN_FOOD_PRICE_TOTAL + " INTEGER, " +
                COLUMN_FOOD_PRICE_DISCOUNT_TOTAL + " INTEGER, " +
                COLUMN_BUTTON + " INTEGER, " +
                COLUMN_FOOD_ITEM_COUNT + " INTEGER, " +
                COLUMN_FOOD_IMG + " BLOB " + " )";

        String CREATE_COUPON_TABLE = "CREATE TABLE " + TABLE_COUPON + " (" +
                COLUMN_COUPON_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_COUPON_NAME + " TEXT, " +
                COLUMN_COUPON_VALUE + " INTEGER, " +
                COLUMN_COUPON_VALID + " TEXT, " +
                COLUMN_COUPON_IMG + " BLOB " + " )";

        String CREATE_FOOD_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_FOOD_TRANSACTION + " (" +
                COLUMN_FOOD_TRANSACTION_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_FOOD_QUANTITY + " TEXT, " +
                COLUMN_FOOD_ALLQUANTITY + " TEXT, " +
                COLUMN_FOOD_PRICE_TOTALS + " TEXT, " +
                COLUMN_FOOD_PRICE_DISCOUNT_TOTALS + " TEXT " + " )";

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

        String CREATE_RESTAURANT_TABLE = "CREATE TABLE " + TABLE_RESTAURANT + " (" +
                COLUMN_REST_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_REST_NAME + " TEXT, " +
                COLUMN_REST_ADDRESS + " TEXT, " +
                COLUMN_REST_IMG + " BLOB " + " )";

        String DATA_RESTAURANT = "INSERT INTO " + TABLE_RESTAURANT + "(restaurantname, restaurantaddress, restaurantimage) " +
                "VALUES ('Ayam Gepuk Pak Gembus', 'Jl. Guru Mughni No.7, RT.1/RW.3, Karet Kuningan',"  + R.drawable.ayam_gepuk_pakgembus + ")," +
                "('Bakaro Grill Express', 'Jl. Ir H. Juanda No.164, Pisangan, Kec. Ciputat Tim.'," + R.drawable.bakaro_grill_express + ")," +
                "('Chat Time', 'Kuningan City Lt. LG Blok FE No.72, Jl. Prof. Dr. Satrio Kav. 18'," + R.drawable.chat_time + ")," +
                "('Dcrepes', 'Itc Ambassador, Jl. Prof. DR. Satrio No.Lt. Ub, RT.11/RW.4, Kuningan'," + R.drawable.dcrepes + ")," +
                "('Eatlah', 'Jl. M.H. Thamrin No.28-30, RT.9/RW.5, Gondangdia, Kec. Menteng'," + R.drawable.eatlah + ")," +
                "('Geprek Bensu', 'Jl. M.H. Thamrin No.28-30, RT.9/RW.5, Gondangdia, Kec. Menteng'," + R.drawable.geprek_bensu + ")," +
                "('Hokben', 'Jl. Prof. Dr.Satrio, Lantai 4 Mal Ambasador, RT.11/RW.4, Kuningan'," + R.drawable.hokben + ")," +
                "('KFC', 'Mall Ambasador, Jl. Prof. DR. Satrio, RT.11/RW.4, Kuningan'," + R.drawable.kfc + ")," +
                "('Koppi', 'Oakwood, Jl. Mega Kuningan Barat, RT.5/RW.2, Kuningan'," + R.drawable.kopi_koppi_item + ")," +
                "('Lotteria', 'Jl. Prof. DR. Satrio No.3-5, RT.18/RW.4, Kuningan, Karet Kuningan'," + R.drawable.lotteria + ")," +
                "('Martabak Orins', 'Jl. Prof. DR. Satrio No.39A, RT.17/RW.4, Kuningan'," + R.drawable.martabak_orins + ")," +
                "('McDonalds', 'Plaza Festival, Jl. H. R. Rasuna Said No.9, RW.5, Karet Kuningan'," + R.drawable.mcdonald + ")," +
                "('Mie Aceh Ayah', 'Jl. Dr. Saharjo No.RT. 9, RT.9/RW.5, Menteng Dalam'," + R.drawable.mie_aceh_ayah + ")," +
                "('Restoran Sederhana', 'Rifa, Kav. 8, Jl. Prof. DR. Satrio No.7, RT.7/RW.2, Kuningan'," + R.drawable.padang_sederhana + ")," +
                "('Pizza Hut', 'ITC Kuningan Ground Floor, Jl. Prof. DR. Satrio, RT.11/RW.4'," + R.drawable.pizza_hut + ")," +
                "('Raffles Pattiserie', ' Ciputra World 1, Jl. Prof. DR. Satrio No.Kav 3-5, RT.18/RW.4, Kuningan'," + R.drawable.raffles_pattiserie + ")," +
                "('Seblak Edan Bunda', 'Jl. Perbanas No.1, RT.15/RW.7, Kuningan, Karet Kuningan'," + R.drawable.seblak_edan_bunda + ")," +
                "('Solaria', 'Jl. Prof. DR. Satrio No.18, RT.14/RW.4, Kuningan'," + R.drawable.solaria + ")," +
                "('Tahu Kriuk Yes', 'Jl. Muara Karang Raya No.125, RW.12, Pluit, Kec. Penjaringan'," + R.drawable.tahu_kriuk_yes + ")," +
                "('Wingstop', 'Generali Tower (next to MMC Hospital), Gran Rubina Business Park'," + R.drawable.wingstop + ")";


        String DATA_FOOD = "INSERT INTO " + TABLE_FOOD + "(foodname, fooddesc, foodprice, foodpricediscount, foodpricetotal, foodpricediscounttotal, buttoncartquantityopened, fooditemcount, foodimage) " +
                "VALUES ('Nasi Padang', '3 nasi + 2 ayam + sayuran + sambel', 30000, 40000, 0, 0, 0, 0," + R.drawable.nasi_padang_s + ")," +
                "('Bakso', '2 Bakso besar + 5 Bakso kecil + bihun + bawang', 50000, 65000, 0, 0, 0, 0," + R.drawable.bakso + ")," +
                "('Soto', 'Suwiran Ayam + Telor + Nasi', 40000, 55000, 0, 0, 0, 0," + R.drawable.soto + ")," +
                "('Sate Ayam', '10 Tusuk sate ayam + bumbu kacang + nasi', 40000, 50000, 0, 0, 0, 0," + R.drawable.sate_ayam + ")," +
                "('Sate Padang', '20 Tusuk sate padang + bumbu sate padang + lontong', 100000, 115000, 0, 0, 0, 0," + R.drawable.sate_padang + ")," +
                "('Nasi Lemak', 'Nasi lemak + kacang + telor _ sambal', 50000, 30000, 0, 0, 0, 0," + R.drawable.nasi_lemak + ")," +
               // "('Dal Tadkda', '3 Roti butter + kentang india', 30000, 40000, 0," + R.drawable.dal_tadkda + ")," +
                "('Chinese Food', '1 porsi spagheti chinese + sayuran', 40000, 45000, 0, 0, 0, 0," + R.drawable.chow_mein + ")," +
                "('Indian Curry', '3 roti butter + Mix sayuran + nasi', 20000, 35000, 0, 0, 0, 0," + R.drawable.maharashtra_thali + ")," +
                "('Panang Curry', '3 Curry ayam + sayuran', 50000, 65000, 0, 0, 0, 0," + R.drawable.panang_curry + ")," +
                "('Samosa', '10 Aneka gorengan india', 30000, 45000, 0, 0, 0, 0," + R.drawable.snacks + ")";

        String DATA_COUPON = "INSERT INTO " + TABLE_COUPON + "(couponname, couponvalue, couponvalid, couponimage) " +
                "VALUES ('Chat Time', 5, '23 December 2022'," + R.drawable.chat_time_coupon + ")," +
                "('KFC', 15, '15 January 2021'," + R.drawable.kfc_coupon + ")," +
                "('MCD', 20, '21 February 2020'," + R.drawable.mcd_coupon + ")," +
                "('Dcrepes', 10, '6 June 2020'," + R.drawable.dcrepes + ")," +
                "('Pizza Hut', 15, '16 March 2020'," + R.drawable.pizza_hut_coupon + ")," +
                "('Wing Stop', 35, '8 December 2020'," + R.drawable.wingstop_coupon + ")," +
                "('Dominos Pizza', 50, '14 October 2020'," + R.drawable.domino_pizza_coupon + ")";

        String DATA_BESTCUSINE = "INSERT INTO " + TABLE_BESTCUSINE_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Thai Special'," + panang_curry + ")," +
                "('Indian'," + butter_chicken + ")," +
                "('Indonesian'," + R.drawable.nasi_padang_s + ")," +
                "('Malysian'," + R.drawable.nasi_lemak + ")," +
                "('Chinese'," + chow_mein + ")";

        String DATA_TODAYSPECIAL = "INSERT INTO " + TABLE_TODAYSPECIAL_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Goan Special'," + butter_chicken + ")," +
                "('Indian Curry'," + maharashtra_thali + ")," +
                "('Panang Curry'," + panang_curry + ")," +
                "('Chapati'," + chapati + ")";

        String DATA_YOURFAVOURITES = "INSERT INTO " + TABLE_YOURFAVOURITES_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Nasi Padang'," + R.drawable.nasi_padang_s + ")," +
                "('Soto'," + R.drawable.soto + ")," +
                "('Bakso'," + R.drawable.bakso + ")," +
                "('Sate Ayam'," + R.drawable.sate_ayam + ")," +
                "('Sate Padang'," + R.drawable.sate_padang + ")," +
                "('Murg Musallam'," + murg_musallam + ")," +
                "('Goan Special'," + butter_chicken + ")";

        String DATA_CUSTOM = "INSERT INTO " + TABLE_CUSTOM_FRAGMENT + "(foodname, foodimage)" +
                "VALUES ('Beverage'," + beverage + ")," +
                "('Snacks'," + snacks + ")," +
                "('Sweets'," + sweets + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_COUPON_TABLE);
        db.execSQL(CREATE_FOOD_TABLE_TRANSACTION);

        db.execSQL(CREATE_BESTCUSINE_TABLE);
        db.execSQL(CREATE_TODAYSPECIAL_TABLE);
        db.execSQL(CREATE_YOURFAVOURITES_TABLE);
        db.execSQL(CREATE_CUSTOM_TABLE);
        db.execSQL(CREATE_RESTAURANT_TABLE);

        db.execSQL(DATA_FOOD);
        db.execSQL(DATA_COUPON);
        db.execSQL(DATA_RESTAURANT);

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

    public void updateData(DataKhanaval data){

        ContentValues values = new ContentValues();
        values.put(COLUMN_BUTTON, data.getButtonPosition());
        values.put(COLUMN_FOOD_ITEM_COUNT, data.getFoodItemCount());
        values.put(COLUMN_FOOD_PRICE_TOTAL, data.getFoodPriceTotal());
        values.put(COLUMN_FOOD_PRICE_DISCOUNT_TOTAL, data.getFoodPriceDiscountTotal());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_FOOD, values, COLUMN_FOOD_ID	+ "	= ?", new String[] { String.valueOf(data.getId())});

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
                int foodPrice = cursor.getInt(3);
                int foodPriceDiscount = cursor.getInt(4);
                int foodPriceTotal = cursor.getInt(5);
                int foodPriceDiscountTotal = cursor.getInt(6);
                int button = cursor.getInt(7);
                int foodItemCount = cursor.getInt(8);
                int foodImg = Integer.parseInt(cursor.getString(9));
                storeData.add(new DataKhanaval(foodId, foodName, foodDesc, foodPrice, foodPriceDiscount, foodPriceTotal, foodPriceDiscountTotal, button, foodItemCount, foodImg));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataCoupon() {

        String sql = "select * from " + TABLE_COUPON;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int coupId = Integer.parseInt(cursor.getString(0));
                String coupName = cursor.getString(1);
                int coupValue = cursor.getInt(2);
                String coupValid = cursor.getString(3);
                int img = Integer.parseInt(cursor.getString(4));
                storeData.add(new DataKhanaval(coupId, coupName, coupValue, coupValid, img));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataRestaurant() {

        String sql = "select * from " + TABLE_RESTAURANT + " limit 10";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int restId = Integer.parseInt(cursor.getString(0));
                String restName = cursor.getString(1);
                String restAddress = cursor.getString(2);
                int img = Integer.parseInt(cursor.getString(3));
                storeData.add(new DataKhanaval(restId, restName, restAddress, img));
            }while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

    public ArrayList<DataKhanaval> listDataRestaurantMax() {

        String sql = "select * from " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataKhanaval> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {

                int restId = Integer.parseInt(cursor.getString(0));
                String restName = cursor.getString(1);
                String restAddress = cursor.getString(2);
                int img = Integer.parseInt(cursor.getString(3));
                storeData.add(new DataKhanaval(restId, restName, restAddress, img));
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

    public DataKhanaval findDataFood(String name){

        String query = "Select * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOOD_NAME + " = " + "foodname";
        SQLiteDatabase db = this.getWritableDatabase();
        DataKhanaval data = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){

            int id = Integer.parseInt(cursor.getString(0));
            String foodName = cursor.getString(1);
            String foodDesc = cursor.getString(2);
            int img = Integer.parseInt(cursor.getString(3));
            data = new DataKhanaval(id, foodName, foodDesc, img);

        }
        cursor.close();
        return data;

    }

    public DataKhanaval findDataRestaurant(String name){

        String query = "Select * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOOD_NAME + " = " + "foodname";
        SQLiteDatabase db = this.getWritableDatabase();
        DataKhanaval data = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){

            int id = Integer.parseInt(cursor.getString(0));
            String restName = cursor.getString(1);
            String restAddress = cursor.getString(2);
            int img = Integer.parseInt(cursor.getString(2));
            data = new DataKhanaval(id, restName, restAddress,img);

        }
        cursor.close();
        return data;

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
