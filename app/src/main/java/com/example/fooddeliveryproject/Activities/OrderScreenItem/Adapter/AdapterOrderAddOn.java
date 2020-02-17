package com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.Model.DataAlsoOrderThis;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderAddOnFragment;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderPaymentDetailsFragment;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class AdapterOrderAddOn extends RecyclerView.Adapter<AdapterOrderAddOn.ViewHolder> {

    List<DataAlsoOrderThis> menuDetailList;
    Context context;
    DatabaseHelper helper;
    SQLiteDatabase db;

    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;

    DecimalHelper decimalHelper;

    OrderScreenActivity orderScreenActivity = new OrderScreenActivity();


    public AdapterOrderAddOn(List<DataAlsoOrderThis> menuDetailList, Context context) {
        this.menuDetailList = menuDetailList;
        this.context = context;
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    @NonNull
    @Override
    public AdapterOrderAddOn.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_add_on_new, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final DataAlsoOrderThis data = menuDetailList.get(i);
        final Bundle bundle = new Bundle();


        decimalHelper = new DecimalHelper();

        viewHolder.foodName.setText(menuDetailList.get(i).getFoodName());
        viewHolder.foodPrice.setText("Rp. " + decimalHelper.formatter(menuDetailList.get(i).getFoodPrice()));
        viewHolder.img.setImageResource(menuDetailList.get(i).getFoodImg());

        final int quantity[] = {1};
        final int[] priceTotal = {1};
        final int[] priceDiscountTotal = {1};
        final int[] isChartQuantity = {1};

        final int foodPriceItem = menuDetailList.get(i).getFoodPrice();
        final int foodPriceDiscountItem = menuDetailList.get(i).getFoodPriceDiscount();
        final int position = viewHolder.getAdapterPosition();

        quantity[0] = data.getFoodItemCount();
        priceTotal[0] = data.getFoodPriceTotal();
        priceDiscountTotal[0] = data.getFoodPriceDiscountTotal();

        quantityTotal = SaveSharedPreference.getAllQuantity(context, 0);
        foodPriceTotal = SaveSharedPreference.getFoodPriceTotal(context, 0);
        foodPriceDiscountTotal = SaveSharedPreference.getFoodPriceDiscountTotal(context, 0);

        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] == 1){

                    Toast.makeText(context, "Damn", Toast.LENGTH_SHORT).show();

                }else if (quantityTotal == 100){

                    Toast.makeText(context, "Iam sorry the maximum item is 100", Toast.LENGTH_SHORT).show();

                }else {

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
                    String foodTransName = data.getFoodName();
                    String foodTransDesc = data.getFoodDesc();
                    int foodTransPrice = data.getFoodPrice();
                    int foodTransPriceDiscount = data.getFoodPriceDiscount();
                    int foodTransPriceTotal = priceTotal[0];
                    int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                    int buttonTransPosition = isChartQuantity[0];
                    int foodTransItemCount = quantity[0];
                    int img = data.getFoodImg();

                    insertData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                    updateDataFood(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                    deleteDataNew(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                    logListItem();

                    viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                    OrderScreenActivity orderScreenActivity = (OrderScreenActivity) view.getContext();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.orderSummaryFragment, new OrderScreenOrderFragment()).commit();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.paymentDetailsFragment, new OrderPaymentDetailsFragment()).commit();
                    orderScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.menuOrderAlsoOrderFragment, new OrderAddOnFragment()).commit();

                }
            }

            public void insertData(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                   int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

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
                contentValues.put(DatabaseHelper.COLUMN_FOOD_TRANSACTION_IMG, img);
                db.insert(DatabaseHelper.TABLE_FOOD_TRANSACTION, null, contentValues);

            }

            public void deleteDataNew(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                      int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_ID_NEW + " FROM "+ DatabaseHelper.TABLE_FOOD_NEW +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_NAME_NEW + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    helper.deleteDataNew(foodTransName);

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

                menuDetailList.get(i).setFoodPriceTotal(priceTotal[0]);
                menuDetailList.get(i).setFoodPriceDiscountTotal(priceDiscountTotal[0]);
                menuDetailList.get(i).setButtonPosition(isChartQuantity[0]);
                menuDetailList.get(i).setFoodItemCount(quantity[0]);

                Log.e("FoodCount", menuDetailList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuDetailList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuDetailList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                int totalPayment = SaveSharedPreference.getTotalPayment(context, 0) + menuDetailList.get(i).getFoodPrice();

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

        /*viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] == 0){

                    return;

                }
                quantity[0]--;
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

            }
        });*/

    }


    @Override
    public int getItemCount() {
        return menuDetailList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName, foodPrice, decreaseChartQuantity, increaseChartQuantity, chartQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.orderFoodImage);
            foodName = itemView.findViewById(R.id.orderFoodName);
            foodPrice = itemView.findViewById(R.id.orderFoodPrice);
            decreaseChartQuantity = itemView.findViewById(R.id.orderDecreaseCartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.orderIncreaseCartQuantity);
            chartQuantity = itemView.findViewById(R.id.orderCartQuantity);


        }
    }
}
