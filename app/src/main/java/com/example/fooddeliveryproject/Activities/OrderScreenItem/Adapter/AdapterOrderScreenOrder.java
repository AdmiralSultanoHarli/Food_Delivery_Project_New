package com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersDetailsFragment;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;
import java.util.Locale;

public class AdapterOrderScreenOrder extends RecyclerView.Adapter<AdapterOrderScreenOrder.ViewHolder> {

    List<DataTransaction> topList;
    Context context;
    DatabaseHelper helper;
    SQLiteDatabase db;

    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;

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
        DecimalHelper decimalHelper = new DecimalHelper();

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

        Log.e("Food name",viewHolder.foodName.getText().toString());

        viewHolder.orderSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MenuDetailsScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

                Log.e("Adapter Position", String.valueOf(position));

                // Total All Item
                quantityTotal++;
                foodPriceTotal += foodPriceItem;
                foodPriceDiscountTotal += foodPriceDiscountItem;

                // Total One Item
                quantity[0]++;
                priceTotal[0] += foodPriceItem;
                priceDiscountTotal[0] += foodPriceDiscountItem;

                isChartQuantity[0] = 1;

                int foodId = data.getFoodId();
                String foodTransName = data.getFoodTransName();
                String foodTransDesc = data.getFoodTransDesc();
                int foodTransPrice = data.getFoodTransPrice();
                int foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                int foodTransPriceTotal = priceTotal[0];
                int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                int buttonTransPosition = isChartQuantity[0];
                int foodTransItemCount = quantity[0];
                int img = data.getFoodImg();

                updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                        foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                updateDataToOne(data);
                logListItem();

                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

            }

            public void updateDataToOne(final DataTransaction data){

                DataTransaction dataTransaction = new DataTransaction(data.getFoodId(), data.getFoodTransName(),
                        data.getFoodTransDesc(), data.getFoodTransPrice(), data.getFoodTransPriceDiscount(), priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getFoodImg());

                helper.updateDataTrans(dataTransaction);


            }

            public void updateDataFood(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID + " FROM "+ DatabaseHelper.TABLE_FOOD +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    Log.e("Data", "Exists");
                    DataKhanaval dataKhanaval = new DataKhanaval(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
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

                Log.e("FoodCount", topList.get(i).getFoodTransName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

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

                        Log.e("Adapter Position", String.valueOf(position));

                        // Total All Item
                        quantityTotal = 0;
                        foodPriceTotal = 0;
                        foodPriceDiscountTotal = 0;

                        // Total One Item
                        quantity[0] = 0;
                        priceTotal[0] = 0;
                        priceDiscountTotal[0] = 0;

                        isChartQuantity[0] = 0;

                        int foodId = data.getFoodId();
                        String foodTransName = data.getFoodTransName();
                        String foodTransDesc = data.getFoodTransDesc();
                        int foodTransPrice = data.getFoodTransPrice();
                        int foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getFoodImg();
                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataToZero(data);
                        logListItem();

                        Intent i = new Intent(context, MenuScreenActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(i);

                        //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));

                    }else if(quantityTotal > 1) {

                        Log.e("Adapter Position", String.valueOf(position));

                        // Total All Item
                        quantityTotal--;
                        foodPriceTotal -= foodPriceItem;
                        foodPriceDiscountTotal -= foodPriceDiscountItem;

                        // Total 1 item
                        quantity[0]--;
                        priceTotal[0] -= foodPriceItem;
                        priceDiscountTotal[0] -= foodPriceDiscountItem;

                        isChartQuantity[0] = 0;

                        int foodId = data.getFoodId();
                        String foodTransName = data.getFoodTransName();
                        String foodTransDesc = data.getFoodTransDesc();
                        int foodTransPrice = data.getFoodTransPrice();
                        int foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getFoodImg();
                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataToZero(data);
                        logListItem();

                        //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));


                        OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                        orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();
                        //viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));

                    }

                } else if(quantity[0] > 1){

                    if (quantityTotal > 1) {

                        Log.e("Adapter Position", String.valueOf(position));

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

                        int foodId = data.getFoodId();
                        String foodTransName = data.getFoodTransName();
                        String foodTransDesc = data.getFoodTransDesc();
                        int foodTransPrice = data.getFoodTransPrice();
                        int foodTransPriceDiscount = data.getFoodTransPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getFoodImg();

                        updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataToZero(data);
                        logListItem();

                    }

                }

            }

            public void updateDataFood(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID + " FROM "+ DatabaseHelper.TABLE_FOOD +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    Log.e("Data", "Exists");
                    DataKhanaval dataKhanaval = new DataKhanaval(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                    helper.updateData(dataKhanaval);

                }

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
                        data.getFoodTransDesc(), data.getFoodTransPrice(), data.getFoodTransPriceDiscount(), priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getFoodImg());

                helper.updateDataTrans(dataTransaction);

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

                Log.e("FoodCount", topList.get(i).getFoodTransName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", topList.get(i).getFoodTransName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

            }
        });

        viewHolder.openNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                orderScreenActivity.slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                orderScreenActivity.slideOpened = true;
                orderScreenActivity.editNotes.requestFocus();
                orderScreenActivity.editNotes.setEnabled(true);

                if (orderScreenActivity.counter == 0) {

                    orderScreenActivity.accNotes.setEnabled(false);
                    orderScreenActivity.accNotes.setBackgroundResource(R.drawable.rounded_button_add_nonactive);

                }else{

                    orderScreenActivity.accNotes.setEnabled(true);
                    orderScreenActivity.accNotes.setBackgroundResource(R.drawable.rounded_button_add_active);
                }

                //orderScreenActivity.editNotes.setShowSoftInputOnFocus(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

            }
        });

        viewHolder.favouriteFood.setColorFilter(ContextCompat.getColor(context, R.color.colorButtonGray));

        viewHolder.favouriteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                if (orderScreenActivity.wishlistAdded == false) {

                    viewHolder.favouriteFood.setColorFilter(ContextCompat.getColor(context, R.color.circleRed));
                    orderScreenActivity.wishlistAdded = true;

                }else if (orderScreenActivity.wishlistAdded == true){

                    viewHolder.favouriteFood.setColorFilter(ContextCompat.getColor(context, R.color.colorButtonGray));
                    orderScreenActivity.wishlistAdded = false;

                }
            }
        });

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

