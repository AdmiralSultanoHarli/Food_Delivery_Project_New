package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

import com.example.fooddeliveryproject.Activities.Activity.HomeScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersDetailsFragment;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.R;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MY_PREFERENCE;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> implements Filterable {

    List<DataKhanaval> menuList;
    List<DataKhanaval> mTopList;
    Context context;
    DatabaseHelper helper;
    SQLiteDatabase db;

    Dialog mDialog;
    FoodChartFragment foodChartFragment = new FoodChartFragment();
    MenuScreenFragment menuScreenFragment = new MenuScreenFragment();

    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;

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

        //Log.e("Button Adapter", String.valueOf(viewHolder.buttonAddToChart.getTag(0)));

        //Log.e("Data filterable", String.valueOf(menuList));

        if (SaveSharedPreference.getAllQuantity(context, quantityTotal) >= 1 ) {

            viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            //viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

        }

        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                MenuScreenActivity menuScreenActivity = (MenuScreenActivity) view.getContext();

                if (!foodChartFragment.isAdded()) {
                    if (quantity[0] == 0) {


                        viewHolder.buttonAddToChart.getTag();

                        menuScreenActivity.numberCount.setVisibility(View.VISIBLE);

                        quantity[0] = 1;
                        priceTotal[0] = foodPriceItem;
                        priceDiscountTotal[0] = foodPriceDiscountItem;

                        quantityTotal = 1;
                        foodPriceTotal = foodPriceItem;
                        foodPriceDiscountTotal = foodPriceDiscountItem;

                        viewHolder.buttonAddToChart.setVisibility(View.GONE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                        isChartQuantity[0] = 1;

                        int foodId = data.getId();
                        String foodTransName = data.getFoodName();
                        String foodTransDesc = data.getFoodDescription();
                        int foodTransPrice = data.getFoodPrice();
                        int foodTransPriceDiscount = data.getFoodPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getImg();
                        insertData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
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

                    int foodId = data.getId();
                    String foodTransName = data.getFoodName();
                    String foodTransDesc = data.getFoodDescription();
                    int foodTransPrice = data.getFoodPrice();
                    int foodTransPriceDiscount = data.getFoodPriceDiscount();
                    int foodTransPriceTotal = priceTotal[0];
                    int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                    int buttonTransPosition = isChartQuantity[0];
                    int foodTransItemCount = quantity[0];
                    int img = data.getImg();
                    insertData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                    updateDataToOne(data, view);
                    logListItem();

                    

                    viewHolder.buttonAddToChart.getTag();

                    viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                    viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

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

            public void updateDataToOne(final DataKhanaval data, View view){

                DataKhanaval dataKhanaval = new DataKhanaval(data.getId(), data.getFoodName(),
                        data.getFoodDescription(), data.getFoodPrice(), data.getFoodPriceDiscount(), priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getImg());

                helper.updateData(dataKhanaval);

                Log.e("Datakhanaval changed", String.valueOf(dataKhanaval));

            }

            public void logListItem() {

                Log.e("Adapter Position", String.valueOf(position));

                Log.e("Tag Position", String.valueOf(viewHolder.buttonAddToChart.getTag()));

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

                Log.e("Data filterable", String.valueOf(menuList));


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

                int foodId = data.getId();
                String foodTransName = data.getFoodName();
                String foodTransDesc = data.getFoodDescription();
                int foodTransPrice = data.getFoodPrice();
                int foodTransPriceDiscount = data.getFoodPriceDiscount();
                int foodTransPriceTotal = priceTotal[0];
                int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                int buttonTransPosition = isChartQuantity[0];
                int foodTransItemCount = quantity[0];
                int img = data.getImg();

                updateDataTrans(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                        foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                updateDataToOne(data, view);
                logListItem();



                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();
            }

            public void updateDataToOne(final DataKhanaval data, View view){

                DataKhanaval dataKhanaval = new DataKhanaval(data.getId(), data.getFoodName(),
                        data.getFoodDescription(), data.getFoodPrice(), data.getFoodPriceDiscount(), priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getImg());

                helper.updateData(dataKhanaval);
                Log.e("Datakhanaval changed", String.valueOf(dataKhanaval));

            }

            public void updateDataTrans(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                   int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    Log.e("Data", "Exists");
                    DataTransaction dataTransaction1 = new DataTransaction(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
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

                Log.e("Data filterable", String.valueOf(menuList));

            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                MenuScreenActivity menuScreenActivity = (MenuScreenActivity) view.getContext();

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

                        menuScreenActivity.numberCount.setVisibility(View.GONE);

                        isChartQuantity[0] = 0;

                        int foodId = data.getId();
                        String foodTransName = data.getFoodName();
                        String foodTransDesc = data.getFoodDescription();
                        int foodTransPrice = data.getFoodPrice();
                        int foodTransPriceDiscount = data.getFoodPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getImg();
                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                        updateDataToZero(data, view);
                        logListItem();

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().remove(foodChartFragment).commit();

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

                        int foodId = data.getId();
                        String foodTransName = data.getFoodName();
                        String foodTransDesc = data.getFoodDescription();
                        int foodTransPrice = data.getFoodPrice();
                        int foodTransPriceDiscount = data.getFoodPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getImg();
                        deleteData(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                        updateDataToZero(data, view);
                        logListItem();

                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();
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

                        int foodId = data.getId();
                        String foodTransName = data.getFoodName();
                        String foodTransDesc = data.getFoodDescription();
                        int foodTransPrice = data.getFoodPrice();
                        int foodTransPriceDiscount = data.getFoodPriceDiscount();
                        int foodTransPriceTotal = priceTotal[0];
                        int foodTransPriceDiscountTotal = priceDiscountTotal[0];
                        int buttonTransPosition = isChartQuantity[0];
                        int foodTransItemCount = quantity[0];
                        int img = data.getImg();

                        updateDataTrans(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                                foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);

                        updateDataToZero(data, view);
                        logListItem();

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                    }

                }

            }

            public void updateDataTrans(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount,
                                        int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int img){

                String query = "SELECT "+ DatabaseHelper.COLUMN_FOOD_TRANSACTION_ID + " FROM "+ DatabaseHelper.TABLE_FOOD_TRANSACTION +" WHERE "+
                        DatabaseHelper.COLUMN_FOOD_TRANSACTION_NAME + " = '"+foodTransName+"'";
                Cursor data = db.rawQuery(query,null);

                if (data.moveToFirst()){

                    Log.e("Data", "Exists");
                    DataTransaction dataTransaction1 = new DataTransaction(foodId, foodTransName, foodTransDesc, foodTransPrice, foodTransPriceDiscount,
                            foodTransPriceTotal, foodTransPriceDiscountTotal, buttonTransPosition, foodTransItemCount, img);
                    helper.updateDataTrans(dataTransaction1);

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

            public void updateDataToZero(final DataKhanaval data, View view){

                DataKhanaval dataKhanaval = new DataKhanaval(data.getId(), data.getFoodName(),
                        data.getFoodDescription(), data.getFoodPrice(), data.getFoodPriceDiscount(), priceTotal[0], priceDiscountTotal[0], isChartQuantity[0], quantity[0], data.getImg());

                helper.updateData(dataKhanaval);
                Log.e("Datakhanaval changed", String.valueOf(dataKhanaval));

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

    /*@Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<DataKhanaval> results = new ArrayList<>();

                if (mTopList == null)
                    mTopList = menuList;
                //Log.e("menu list",mTopList.toString());
                if (constraint != null) {
                    if (mTopList != null && mTopList.size() > 0) {
                        for (final DataKhanaval f : mTopList) {
                            if (f.getFoodName().toLowerCase()
                                    .contains(constraint.toString()))
                                //Log.e("Data Result", f.getFoodName());
                                results.add(f);
                        }
                    }
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                //menuList.addAll((ArrayList<DataKhanaval>) results.values);
               *//* menuList = (ArrayList<DataKhanaval>) results.values;
                notifyDataSetChanged();*//*
               updateMenuList(menuList);


            }
        };
    }*/

    /*public void updateMenuList(List<DataKhanaval> newList){

        menuList.clear(); //here items is an ArrayList populating the RecyclerView
        this.notifyDataSetChanged();
        menuList.addAll(newList);// add new data
        this.notifyItemRangeInserted(0, menuList.size());// notify adapter of new data

    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity,
        chartQuantity;
        public Button buttonAddToChart;
        public CardView buttonAddPlusMinusChart;
        public AppCompatActivity activity;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //item_food = itemView.findViewById(R.id.food_item_id);
            //foodChartFragment = itemView.findViewById(R.id.foodChartFragment);
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

            /*foodPriceTotal = itemView.findViewById(R.id.foodPriceTotal);
            foodPriceDiscountTotal = itemView.findViewById(R.id.foodPriceDiscountTotal);*/

            /*mCommunication = mCommunicator;*/

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }

}
