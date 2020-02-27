package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> implements Filterable {

    List<DataKhanaval> menuList;
    List<DataKhanaval> mTopList;
    Context context;
    DatabaseHelper helper;
    SQLiteDatabase db;

    FoodChartFragment foodChartFragment = new FoodChartFragment();

    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;
    int foodId;
    String foodTransName;
    String foodTransDesc;
    int foodTransPrice;
    int foodTransPriceDiscount;
    int foodTransPriceTotal;
    int foodTransPriceDiscountTotal;
    int buttonTransPosition;
    int foodTransItemCount;
    int foodTransFavourites;
    int img;

    public AdapterMenuScreen(List<DataKhanaval> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
        this.mTopList = menuList;
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }


    @NonNull
    @Override
    public AdapterMenuScreen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_menu_screen, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final DataKhanaval data = menuList.get(i);

        DecimalHelper decimalHelper = new DecimalHelper();
        MenuScreenActivity menuScreenActivity = new MenuScreenActivity();

        final Bundle bundle = new Bundle();

        final int[] quantity = {1};
        final int[] priceTotal = {1};
        final int[] priceDiscountTotal = {1};
        final int[] isChartQuantity = {1};

        final int foodPriceItem = menuList.get(i).getFoodPrice();
        final int foodPriceDiscountItem = menuList.get(i).getFoodPriceDiscount();
        final int position = viewHolder.getAdapterPosition();

        quantity[0] = data.getFoodItemCount();
        priceTotal[0] = data.getFoodPriceTotal();
        priceDiscountTotal[0] = data.getFoodPriceDiscountTotal();

        quantityTotal = SaveSharedPreference.getAllQuantity(context, 0);
        foodPriceTotal = SaveSharedPreference.getFoodPriceTotal(context, 0);
        foodPriceDiscountTotal = SaveSharedPreference.getFoodPriceDiscountTotal(context, 0);

        viewHolder.img.setImageResource(data.getImg());
        viewHolder.foodName.setText(data.getFoodName());
        viewHolder.foodDescription.setText(data.getFoodDescription());
        viewHolder.foodPrice.setText(decimalHelper.formatter(data.getFoodPrice()));
        viewHolder.foodPriceDiscount.setText(decimalHelper.formatter(data.getFoodPriceDiscount()));
        viewHolder.chartQuantity.setText(String.valueOf(data.getFoodItemCount()));

        viewHolder.buttonAddToChart.setVisibility(data.getButtonPosition() == 0 ? View.VISIBLE : View.GONE);
        viewHolder.buttonAddPlusMinusChart.setVisibility(data.getButtonPosition() == 1 ? View.VISIBLE : View.GONE);

        if (SaveSharedPreference.getAllQuantity(context, quantityTotal) >= 1 ) {

            viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            menuScreenActivity.numberCountText.setVisibility(View.VISIBLE);
            menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

        }

        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                MenuScreenActivity menuScreenActivity = (MenuScreenActivity) view.getContext();

                if (!foodChartFragment.isAdded()) {

                    if (quantity[0] == 0) {

                        viewHolder.buttonAddToChart.getTag();

                        quantity[0] = 1;
                        priceTotal[0] = foodPriceItem;
                        priceDiscountTotal[0] = foodPriceDiscountItem;

                        quantityTotal = 1;
                        foodPriceTotal = foodPriceItem;
                        foodPriceDiscountTotal = foodPriceDiscountItem;

                        viewHolder.buttonAddToChart.setVisibility(View.GONE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                        isChartQuantity[0] = 1;

                        foodId = data.getId();
                        foodTransName = data.getFoodName();
                        foodTransDesc = data.getFoodDescription();
                        foodTransPrice = data.getFoodPrice();
                        foodTransPriceDiscount = data.getFoodPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodFavourites();
                        img = data.getImg();

                        menuScreenActivity.numberCountText.setVisibility(View.VISIBLE);
                        menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

                        insertData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites,img);
                        deleteDataNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        updateDataToOne(data, view);
                        logListItem();

                        

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                    }
                }else if (foodChartFragment.isAdded()){

                    if (quantityTotal == 100){

                        Toast.makeText(context, "Sorry the item just can be 100", Toast.LENGTH_SHORT).show();
                        return;

                    }

                    quantity[0] = 1;
                    priceTotal[0] = foodPriceItem;
                    priceDiscountTotal[0] = foodPriceDiscountItem;

                    quantityTotal++;
                    foodPriceTotal+= foodPriceItem;
                    foodPriceDiscountTotal+= foodPriceDiscountItem;

                    viewHolder.buttonAddToChart.setVisibility(View.GONE);
                    viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                    isChartQuantity[0] = 1;

                    foodId = data.getId();
                    foodTransName = data.getFoodName();
                    foodTransDesc = data.getFoodDescription();
                    foodTransPrice = data.getFoodPrice();
                    foodTransPriceDiscount = data.getFoodPriceDiscount();
                    foodTransPriceTotal = priceTotal[0];
                    foodTransPriceDiscountTotal = priceDiscountTotal[0];
                    buttonTransPosition = isChartQuantity[0];
                    foodTransItemCount = quantity[0];
                    foodTransFavourites = data.getFoodFavourites();
                    img = data.getImg();

                    menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

                    insertData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                            foodTransFavourites,img);
                    deleteDataNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                            foodTransFavourites, img);

                    updateDataToOne(data, view);
                    logListItem();

                    viewHolder.buttonAddToChart.getTag();

                    viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                    viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                }
            }

            public void deleteDataNew(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                      int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                      int foodTransFavourites, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID_NEW + " FROM "+ DatabaseHelper.TABLE_FOOD_NEW +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME_NEW + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    helper.deleteDataNew(foodTransName);

                }

            }

            public void insertData(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                   int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                   int foodTransFavourites, int img){

                ContentValues contentValues = new ContentValues();

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME, foodTransName);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_DESC, foodTransDesc);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_PRICE, foodTransPrice);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_PRICE_DISCOUNT, foodTransPriceDiscount);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_PRICE_TOTAL, foodTransPriceTotal);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_PRICE_DISCOUNT_TOTAL, foodTransPriceDiscountTotal);
                contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_BUTTON, buttonTransPosition);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_ITEM_COUNT, foodTransItemCount);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_FAVOURITES, foodTransFavourites);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_IMG, img);
                db.insert(DatabaseHelper.TABLE_FOOD_TRANSACTION, null, contentValues);

            }


            public void updateDataToOne(final DataKhanaval data, View view){

                DataKhanaval dataKhanaval = new DataKhanaval(data.getId(), data.getFoodName(),
                        data.getFoodDescription(), data.getFoodPrice(), data.getFoodPriceDiscount(), priceTotal[0],
                        priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getFoodFavourites(), data.getImg());

                helper.updateData(dataKhanaval);

                //Log.e("Datakhanaval changed", String.valueOf(dataKhanaval));

            }

            public void logListItem() {

                //Log.e("Adapter Position", String.valueOf(position));

                //Log.e("Tag Position", String.valueOf(viewHolder.buttonAddToChart.getTag()));

                bundle.putString("FoodName", menuList.get(i).getFoodName());
                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);
                SaveSharedPreference.setIsAddToCartVisible(context, false);

                menuList.get(i).setFoodPriceTotal(priceTotal[0]);
                menuList.get(i).setFoodPriceDiscountTotal(priceDiscountTotal[0]);
                menuList.get(i).setButtonPosition(isChartQuantity[0]);
                menuList.get(i).setFoodItemCount(quantity[0]);

                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                Log.e("-----------------", "----------------");


            }

        });

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MenuScreenActivity menuScreenActivity = (MenuScreenActivity) view.getContext();

                if (quantityTotal == 100){

                    Toast.makeText(context, "Iam sorry only 100 item can be added", Toast.LENGTH_SHORT).show();
                    return;

                }

                //Log.e("Adapter Position", String.valueOf(position));

                // Total All Item
                quantityTotal++;
                foodPriceTotal += foodPriceItem;
                foodPriceDiscountTotal += foodPriceDiscountItem;

                // Total One Item
                quantity[0]++;
                priceTotal[0] += foodPriceItem;
                priceDiscountTotal[0] += foodPriceDiscountItem;

                isChartQuantity[0] = 1;

                foodId = data.getId();
                foodTransName = data.getFoodName();
                foodTransDesc = data.getFoodDescription();
                foodTransPrice = data.getFoodPrice();
                foodTransPriceDiscount = data.getFoodPriceDiscount();
                foodTransPriceTotal = priceTotal[0];
                foodTransPriceDiscountTotal = priceDiscountTotal[0];
                buttonTransPosition = isChartQuantity[0];
                foodTransItemCount = quantity[0];
                foodTransFavourites = data.getFoodFavourites();
                img = data.getImg();

                menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

                updateDataTrans(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                        foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);
                updateDataToOne(data, view);
                logListItem();



                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();
            }

            public void updateDataToOne(final DataKhanaval data, View view){

                DataKhanaval dataKhanaval = new DataKhanaval(data.getId(), data.getFoodName(),
                        data.getFoodDescription(), data.getFoodPrice(), data.getFoodPriceDiscount(), priceTotal[0],
                        priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getFoodFavourites(), data.getImg());

                helper.updateData(dataKhanaval);
                //Log.e("Datakhanaval changed", String.valueOf(dataKhanaval));

            }

            public void updateDataTrans(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                        int foodTransFavourites, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    //Log.e("Data", "Exists");
                    DataTransaction dataTransaction1 = new DataTransaction(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, null, img);
                    helper.updateDataTrans(dataTransaction1);

                }

            }

            public void logListItem() {

                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                menuList.get(i).setFoodPriceTotal(priceTotal[0]);
                menuList.get(i).setFoodPriceDiscountTotal(priceDiscountTotal[0]);
                menuList.get(i).setButtonPosition(isChartQuantity[0]);
                menuList.get(i).setFoodItemCount(quantity[0]);

                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                Log.e("-----------------", "----------------");


            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                MenuScreenActivity menuScreenActivity = (MenuScreenActivity) view.getContext();

                if (quantity[0] == 1) {

                    if (quantityTotal == 1) {

                        //Log.e("Adapter Position", String.valueOf(position));

                        // Total All Item
                        quantityTotal = 0;
                        foodPriceTotal = 0;
                        foodPriceDiscountTotal = 0;

                        // Total One Item
                        quantity[0] = 0;
                        priceTotal[0] = 0;
                        priceDiscountTotal[0] = 0;

                        isChartQuantity[0] = 0;

                        foodId = data.getId();
                        foodTransName = data.getFoodName();
                        foodTransDesc = data.getFoodDescription();
                        foodTransPrice = data.getFoodPrice();
                        foodTransPriceDiscount = data.getFoodPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodFavourites();
                        img = data.getImg();

                        menuScreenActivity.numberCountText.setVisibility(View.GONE);
                        menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        insertDataToNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites ,img);

                        updateDataToZero(data, view);
                        logListItem();

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().remove(foodChartFragment).commit();

                    }else if(quantityTotal > 1) {

                        //Log.e("Adapter Position", String.valueOf(position));

                        // Total All Item
                        quantityTotal--;
                        foodPriceTotal -= foodPriceItem;
                        foodPriceDiscountTotal -= foodPriceDiscountItem;

                        // Total 1 item
                        quantity[0]--;
                        priceTotal[0] -= foodPriceItem;
                        priceDiscountTotal[0] -= foodPriceDiscountItem;
                        isChartQuantity[0] = 0;

                        foodId = data.getId();
                        foodTransName = data.getFoodName();
                        foodTransDesc = data.getFoodDescription();
                        foodTransPrice = data.getFoodPrice();
                        foodTransPriceDiscount = data.getFoodPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodFavourites();
                        img = data.getImg();

                        menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        insertDataToNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        updateDataToZero(data, view);
                        logListItem();

                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();
                    }

                } else if(quantity[0] > 1){

                    if (quantityTotal > 1) {

                        //Log.e("Adapter Position", String.valueOf(position));

                        // Total All Item
                        quantityTotal--;
                        foodPriceTotal -= foodPriceItem;
                        foodPriceDiscountTotal -= foodPriceDiscountItem;

                        // Total 1 item
                        quantity[0]--;
                        priceTotal[0] -= foodPriceItem;
                        priceDiscountTotal[0] -= foodPriceDiscountItem;

                        foodId = data.getId();
                        foodTransName = data.getFoodName();
                        foodTransDesc = data.getFoodDescription();
                        foodTransPrice = data.getFoodPrice();
                        foodTransPriceDiscount = data.getFoodPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodFavourites();
                        img = data.getImg();

                        menuScreenActivity.numberCountText.setText(String.valueOf(quantityTotal));

                        updateDataTrans(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);

                        isChartQuantity[0] = 1;

                        updateDataToZero(data, view);
                        logListItem();

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                    }

                }

            }

            public void updateDataTrans(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                        int foodTransFavourites ,int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    //Log.e("Data", "Exists");
                    DataTransaction dataTransaction1 = new DataTransaction(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, null, img);
                    helper.updateDataTrans(dataTransaction1);

                }

            }

            public void deleteData(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                   int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                   int foodTransFavourites, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    helper.deleteDataTrans(foodTransName);

                }

            }

            public void insertDataToNew(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                        int foodTransFavourites, int img){

                ContentValues contentValues = new ContentValues();

                contentValues.put(DatabaseHelper.COLUMN_FOOD_NAME_NEW, foodTransName);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_DESC_NEW, foodTransDesc);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_PRICE_NEW, foodTransPrice);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_PRICE_DISCOUNT_NEW, foodTransPriceDiscount);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_PRICE_TOTAL_NEW, foodTransPriceTotal);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_PRICE_DISCOUNT_TOTAL_NEW, foodTransPriceDiscountTotal);
                contentValues.put(DatabaseHelper.COLUMN_BUTTON_NEW, buttonTransPosition);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_ITEM_COUNT_NEW, foodTransItemCount);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_NEW_FAVOURITES, foodTransFavourites);
                contentValues.put(DatabaseHelper.COLUMN_FOOD_IMG_NEW, img);
                db.insert(DatabaseHelper.TABLE_FOOD_NEW, null, contentValues);

            }

            public void updateDataToZero(final DataKhanaval data, View view){

                DataKhanaval dataKhanaval = new DataKhanaval(data.getId(), data.getFoodName(),
                        data.getFoodDescription(), data.getFoodPrice(), data.getFoodPriceDiscount(), priceTotal[0],
                        priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getFoodFavourites(), data.getImg());

                helper.updateData(dataKhanaval);
                //Log.e("Datakhanaval changed", String.valueOf(dataKhanaval));

            }

            public void logListItem() {

                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                menuList.get(i).setFoodPriceTotal(priceTotal[0]);
                menuList.get(i).setFoodPriceDiscountTotal(priceDiscountTotal[0]);
                menuList.get(i).setButtonPosition(isChartQuantity[0]);
                menuList.get(i).setFoodItemCount(quantity[0]);

                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                Log.e("-----------------", "----------------");


            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()){

                    menuList = mTopList;

                }else {

                    ArrayList<DataKhanaval> filteredList = new ArrayList<>();
                    for (DataKhanaval data : mTopList){

                        if (data.getFoodName().toLowerCase().contains(charString) || data.getFoodName().contains(charString)
                                || data.getFoodName().toUpperCase().contains(charString)){

                            filteredList.add(data);

                        }

                    }

                    menuList = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = menuList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                menuList = (ArrayList<DataKhanaval>) filterResults.values;
                notifyDataSetChanged();

                /*updateMenuList(menuList);
                menuList.clear();
                menuList.addAll((ArrayList<DataKhanaval>) filterResults.values);*/
            }
        };

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity,
        chartQuantity;
        public Button buttonAddToChart;
        public CardView buttonAddPlusMinusChart;
        public AppCompatActivity activity;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.orderFoodImage);
            foodName = itemView.findViewById(R.id.orderFoodName);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.orderFoodPrice);
            foodPriceDiscount = itemView.findViewById(R.id.orderFoodPriceDiscount);
            buttonAddToChart = itemView.findViewById(R.id.buttonAddToChart);
            buttonAddPlusMinusChart = itemView.findViewById(R.id.orderButtonAddPlusMinusChart);
            decreaseChartQuantity = itemView.findViewById(R.id.orderDecreaseCartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.orderIncreaseCartQuantity);
            chartQuantity = itemView.findViewById(R.id.orderCartQuantity);
            activity = (AppCompatActivity) itemView.getContext();

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }

}
