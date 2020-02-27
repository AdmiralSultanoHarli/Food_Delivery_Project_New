package com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.HomeScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.MenuDetailsScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragment;
import com.example.fooddeliveryproject.Activities.Model.DataAlsoOrderThis;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderAddOnFragment;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderPaymentDetailsFragment;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import static com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity.editNotes;

public class AdapterOrderScreenOrder extends RecyclerView.Adapter<AdapterOrderScreenOrder.ViewHolder> {

    List<DataTransaction> topList;
    Context context;
    DatabaseHelper helper;
    SQLiteDatabase db;

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

    OrderScreenActivity orderScreenActivity = new OrderScreenActivity();

    public AdapterOrderScreenOrder(List<DataTransaction> topList, Context context) {
        this.topList = topList;
        this.context = context;
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();

    }

    @NonNull
    @Override
    public AdapterOrderScreenOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_order_summary, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterOrderScreenOrder.ViewHolder viewHolder, final int i) {

        final DataTransaction data = topList.get(i);
        final DecimalHelper decimalHelper = new DecimalHelper();

        final Bundle bundle = new Bundle();

        final int quantity[] = {1};
        final int[] priceTotal = {1};
        final int[] priceDiscountTotal = {1};
        final int[] isChartQuantity = {1};

        final int foodPriceItem = topList.get(i).getFoodTransPrice();
        final int foodPriceDiscountItem = topList.get(i).getFoodTransPriceDiscount();
        final int position = viewHolder.getAdapterPosition();

        quantity[0] = data.getFoodTransItemCount();
        priceTotal[0] = data.getFoodTransPriceTotal();
        priceDiscountTotal[0] = data.getFoodTransPriceDiscountTotal();

        quantityTotal = SaveSharedPreference.getAllQuantity(context, 0);
        foodPriceTotal = SaveSharedPreference.getFoodPriceTotal(context, 0);
        foodPriceDiscountTotal = SaveSharedPreference.getFoodPriceDiscountTotal(context, 0);

        viewHolder.foodName.setText(topList.get(i).getFoodTransName());
        viewHolder.foodPrice.setText(decimalHelper.formatter(topList.get(i).getFoodTransPrice()));
        viewHolder.foodPriceDiscount.setText(decimalHelper.formatter(topList.get(i).getFoodTransPriceDiscount()));
        viewHolder.chartQuantity.setText(String.valueOf(topList.get(i).getFoodTransItemCount()));
        viewHolder.img.setImageResource(topList.get(i).getFoodImg());

        viewHolder.favouriteFood.setColorFilter(data.getFoodTransFavourites() == 0 ?
                ContextCompat.getColor(context, R.color.grayButton) : ContextCompat.getColor(context, R.color.circleRed));

        //Log.e("Food name",viewHolder.foodName.getText().toString());

        viewHolder.orderSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MenuDetailsScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                SaveSharedPreference.setFoodDetailName(context, data.getFoodTransName());
                SaveSharedPreference.setFoodDetailPrice(context, data.getFoodTransPriceTotal());
                SaveSharedPreference.setFoodDetailDiscountPrice(context, data.getFoodTransPriceDiscountTotal());
                SaveSharedPreference.setFoodDetailImg(context, data.getFoodImg());

                context.startActivity(i);

            }
        });

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                foodId = data.getFoodId();
                foodTransName = data.getFoodTransName();
                foodTransDesc = data.getFoodTransDesc();
                foodTransPrice = data.getFoodTransPrice();
                foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                foodTransPriceTotal = priceTotal[0];
                foodTransPriceDiscountTotal = priceDiscountTotal[0];
                buttonTransPosition = isChartQuantity[0];
                foodTransItemCount = quantity[0];
                foodTransFavourites = data.getFoodTransFavourites();
                img = data.getFoodImg();

                updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                        foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                        foodTransFavourites, img);
                updateDataToOne(data);
                logListItem();

                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                //orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();
                orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.paymentDetailsFragment, new OrderPaymentDetailsFragment()).commit();
                orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.menuOrderAlsoOrderFragment, new OrderAddOnFragment()).commit();


            }

            public void updateDataToOne(final DataTransaction data){

                DataTransaction dataTransaction = new DataTransaction(data.getFoodId(), data.getFoodTransName(),
                        data.getFoodTransDesc(), data.getFoodTransPrice(), data.getFoodTransPriceDiscount(), priceTotal[0],
                        priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getFoodTransFavourites(), data.getFoodTransNotes(),
                        data.getFoodImg());

                helper.updateDataTransWithNotes(dataTransaction);

            }

            public void updateDataFood(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                       int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                       int foodTransFavourites, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID + " FROM "+ DatabaseHelper.TABLE_FOOD +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    //Log.e("Data", "Exists");
                    DataKhanaval dataKhanaval = new DataKhanaval(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);
                    helper.updateData(dataKhanaval);

                }

            }

            public void logListItem() {

                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                // Save to SharedPreference
                //SaveSharedPreference.setFoodName(context, topList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                topList.get(i).setFoodTransPriceTotal(priceTotal[0]);
                topList.get(i).setFoodTransPriceDiscountTotal(priceDiscountTotal[0]);
                topList.get(i).setButtonTransPosition(isChartQuantity[0]);
                topList.get(i).setFoodTransItemCount(quantity[0]);

                Log.e("FoodCount", topList.get(i).getFoodTransName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                Log.e("-----------------", "----------------");


                int totalPayment = SaveSharedPreference.getTotalPayment(context, 0) + topList.get(i).getFoodTransPrice();

                SaveSharedPreference.setTotalPayment(context, totalPayment);
                orderScreenActivity.totalPriceBar.setText(decimalHelper.formatter(SaveSharedPreference.getTotalPayment(context, 0)));
                orderScreenActivity.totalPrice.setText(decimalHelper.formatter(SaveSharedPreference.getTotalPayment(context, 0)));

                // If i want just to show the 1 item total quantity i can use like the comment bellow
                /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                //foodChartFragment.setArguments(bundle);

            }

        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

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

                        foodId = data.getFoodId();
                        foodTransName = data.getFoodTransName();
                        foodTransDesc = data.getFoodTransDesc();
                        foodTransPrice = data.getFoodTransPrice();
                        foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodTransFavourites();
                        img = data.getFoodImg();
                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        insertDataToNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);

                        updateDataToZero(data);
                        logListItem();

                        Intent i = new Intent(context, MenuScreenActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(i);

                        //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));

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

                        foodId = data.getFoodId();
                        foodTransName = data.getFoodTransName();
                        foodTransDesc = data.getFoodTransDesc();
                        foodTransPrice = data.getFoodTransPrice();
                        foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodTransFavourites();
                        img = data.getFoodImg();
                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        insertDataToNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);

                        updateDataToZero(data);
                        logListItem();

                        OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                        orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();
                        orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.paymentDetailsFragment, new OrderPaymentDetailsFragment()).commit();
                        orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.menuOrderAlsoOrderFragment, new OrderAddOnFragment()).commit();

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

                        isChartQuantity[0] = 1;

                        // Showing the text that Counting number in the middle button Decrement and Increment
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        foodId = data.getFoodId();
                        foodTransName = data.getFoodTransName();
                        foodTransDesc = data.getFoodTransDesc();
                        foodTransPrice = data.getFoodTransPrice();
                        foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                        foodTransPriceTotal = priceTotal[0];
                        foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        buttonTransPosition = isChartQuantity[0];
                        foodTransItemCount = quantity[0];
                        foodTransFavourites = data.getFoodTransFavourites();
                        img = data.getFoodImg();

                        updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                                foodTransFavourites, img);

                        updateDataToZero(data);
                        logListItem();

                        OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                        orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.paymentDetailsFragment, new OrderPaymentDetailsFragment()).commit();
                        orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.menuOrderAlsoOrderFragment, new OrderAddOnFragment()).commit();

                    }

                }

            }

            public void updateDataFood(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                       int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                       int foodTransFavourites, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID + " FROM "+ DatabaseHelper.TABLE_FOOD +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    //Log.e("Data", "Exists");
                    DataKhanaval dataKhanaval = new DataKhanaval(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);
                    helper.updateData(dataKhanaval);

                }

            }


            public void insertDataToNew(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int foodTransFavourites, int img){

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

            public void deleteData(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                   int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    helper.deleteDataTrans(foodTransName);

                }

            }

            public void updateDataToZero(final DataTransaction data){

                DataTransaction dataTransaction = new DataTransaction(data.getFoodId(), data.getFoodTransName(),
                        data.getFoodTransDesc(), data.getFoodTransPrice(), data.getFoodTransPriceDiscount(),
                        priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0],
                        data.getFoodTransFavourites(), data.getFoodTransNotes(), data.getFoodImg());

                helper.updateDataTransWithNotes(dataTransaction);

            }

            public void logListItem() {

                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                // Save to SharedPreference
                //SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                topList.get(i).setFoodTransPriceTotal(priceTotal[0]);
                topList.get(i).setFoodTransPriceDiscountTotal(priceDiscountTotal[0]);
                topList.get(i).setButtonTransPosition(isChartQuantity[0]);
                topList.get(i).setFoodTransItemCount(quantity[0]);

                Log.e("FoodCount", topList.get(i).getFoodTransName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                Log.e("-----------------", "----------------");

                int totalPayment = SaveSharedPreference.getTotalPayment(context, 0) - topList.get(i).getFoodTransPrice();

                SaveSharedPreference.setTotalPayment(context, totalPayment);
                orderScreenActivity.totalPriceBar.setText(decimalHelper.formatter(SaveSharedPreference.getTotalPayment(context, 0)));
                orderScreenActivity.totalPrice.setText(decimalHelper.formatter(SaveSharedPreference.getTotalPayment(context, 0)));

            }
        });

        viewHolder.favouriteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                //HomeScreenActivity homeScreenActivity = (HomeScreenActivity) view.getContext();

                if (data.getFoodTransFavourites() == 0){

                    foodId = data.getFoodId();
                    foodTransName = data.getFoodTransName();
                    foodTransDesc = data.getFoodTransDesc();
                    foodTransPrice = data.getFoodTransPrice();
                    foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                    foodTransPriceTotal = priceTotal[0];
                    foodTransPriceDiscountTotal = priceDiscountTotal[0];
                    buttonTransPosition = isChartQuantity[0];
                    foodTransItemCount = quantity[0];
                    foodTransFavourites = 1;
                    img = data.getFoodImg();

                    updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                            foodTransFavourites, img);

                    updateDataToZero(data);

                    insertDataFavourites(foodTransName, img);

                    data.setFoodTransFavourites(foodTransFavourites);

                    viewHolder.favouriteFood.setColorFilter(ContextCompat.getColor(context, R.color.circleRed));

                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.paymentDetailsFragment, new OrderPaymentDetailsFragment()).commit();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.menuOrderAlsoOrderFragment, new OrderAddOnFragment()).commit();
                    //homeScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new HomeFragment()).commit();

                }else if(data.getFoodTransFavourites() == 1){

                    foodId = data.getFoodId();
                    foodTransName = data.getFoodTransName();
                    foodTransDesc = data.getFoodTransDesc();
                    foodTransPrice = data.getFoodTransPrice();
                    foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                    foodTransPriceTotal = priceTotal[0];
                    foodTransPriceDiscountTotal = priceDiscountTotal[0];
                    buttonTransPosition = isChartQuantity[0];
                    foodTransItemCount = quantity[0];
                    foodTransFavourites = 0;
                    img = data.getFoodImg();

                    updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount,
                            foodTransFavourites, img);

                    updateDataToZero(data);

                    deleteDataFavourites(foodTransName, img);

                    data.setFoodTransFavourites(foodTransFavourites);

                    viewHolder.favouriteFood.setColorFilter(ContextCompat.getColor(context, R.color.grayButton));

                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.paymentDetailsFragment, new OrderPaymentDetailsFragment()).commit();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.menuOrderAlsoOrderFragment, new OrderAddOnFragment()).commit();
                    //homeScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new HomeFragment()).commit();

                }

            }

            public void insertDataFavourites(String foodTransName, int foodImg){

                ContentValues contentValues = new ContentValues();

                contentValues.put(DatabaseHelper.COLUMN_YOURFAVOURITES_NAME, foodTransName);
                contentValues.put(DatabaseHelper.COLUMN_YOURFAVOURITES_IMG, foodImg);

                db.insert(DatabaseHelper.TABLE_YOURFAVOURITES_FRAGMENT, null, contentValues);

            }

            public void deleteDataFavourites(String foodTransName, int foodImg){

                String query = "SELECT "+ DatabaseHelper.COLUMN_YOURFAVOURITES_ID + " FROM "+ DatabaseHelper.TABLE_YOURFAVOURITES_FRAGMENT +" WHERE "+
                        DatabaseHelper.COLUMN_YOURFAVOURITES_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    helper.deleteDataFavourites(foodTransName);

                }

            }

            public void updateDataFood(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                       int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount,
                                       int foodTransFavourites, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID + " FROM "+ DatabaseHelper.TABLE_FOOD +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    //Log.e("Data", "Exists");
                    DataKhanaval dataKhanaval = new DataKhanaval(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, foodTransFavourites, img);
                    helper.updateData(dataKhanaval);

                }

            }

            public void updateDataToZero(final DataTransaction data){

                DataTransaction dataTransaction = new DataTransaction(data.getFoodId(), data.getFoodTransName(),
                        data.getFoodTransDesc(), data.getFoodTransPrice(), data.getFoodTransPriceDiscount(),
                        priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0],
                        foodTransFavourites, data.getFoodTransNotes(), data.getFoodImg());

                helper.updateDataTransWithNotes(dataTransaction);

            }

        });

        viewHolder.openNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slideNoteData(data);


            }

        });

    }

    private void slideNoteData(final DataTransaction dataTransaction){

        final OrderScreenActivity orderScreenActivity = new OrderScreenActivity();
        orderScreenActivity.slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        orderScreenActivity.slideOpened = true;
        orderScreenActivity.editNotes.requestFocus();
        orderScreenActivity.editNotes.setEnabled(true);

        orderScreenActivity.editNotes.setText(dataTransaction.getFoodTransNotes());

        if (orderScreenActivity.counter == 0) {

            orderScreenActivity.accNotes.setEnabled(false);
            orderScreenActivity.accNotes.setBackgroundResource(R.drawable.rounded_button_add_nonactive);

        }else{

            orderScreenActivity.accNotes.setEnabled(true);
            orderScreenActivity.accNotes.setBackgroundResource(R.drawable.rounded_button_add_active);

        }



        orderScreenActivity.accNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderScreenActivity.slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                orderScreenActivity.editNotes.setEnabled(false);
                orderScreenActivity.slideOpened = false;

                OrderScreenActivity orderScreenActivity1 = (OrderScreenActivity) v.getContext();

                String editNotesString = orderScreenActivity.editNotes.getText().toString();

                updateDataTransNotes(dataTransaction.getFoodTransName(), editNotesString);
                Log.e("FoodTransName", dataTransaction.getFoodTransName());
                Log.e("EditNotes", editNotesString);

                orderScreenActivity1.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();

            }
        });

    }


    private void updateDataTransNotes(String foodTransName, String foodTransNotes){

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_NOTES, foodTransNotes);
        db.update(DatabaseHelper.TABLE_FOOD_TRANSACTION, values, DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME	+ "	= ?", new String[] {String.valueOf(foodTransName)});

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img, favouriteFood;
        public TextView foodName, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
        public ImageButton openNotes;
        public CardView orderSummaryCard;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.orderFoodImage);
            foodName = itemView.findViewById(R.id.orderFoodName);
            foodPrice = itemView.findViewById(R.id.orderFoodPrice);
            foodPriceDiscount = itemView.findViewById(R.id.orderFoodPriceDiscount);
            decreaseChartQuantity = itemView.findViewById(R.id.orderDecreaseCartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.orderIncreaseCartQuantity);
            chartQuantity = itemView.findViewById(R.id.orderCartQuantity);
            openNotes = itemView.findViewById(R.id.openNotes);
            favouriteFood = itemView.findViewById(R.id.favouriteFood);
            orderSummaryCard = itemView.findViewById(R.id.orderSummaryCard);

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }
}

