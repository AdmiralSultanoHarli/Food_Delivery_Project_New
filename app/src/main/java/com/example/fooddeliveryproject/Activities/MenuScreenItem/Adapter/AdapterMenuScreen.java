package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.R;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.ALL_QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_ADDTOCART_VISIBLE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MY_PREFERENCE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.QUANTITY;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> implements Filterable {

    List<DataKhanaval> menuList;
    List<DataKhanaval> mTopList;
    Context context;
    private DatabaseHelper helper;

    Dialog mDialog;
    FoodChartFragment foodChartFragment = new FoodChartFragment();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int currentPos = -1;
    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;
    boolean buttonOpened = false;

    public AdapterMenuScreen(List<DataKhanaval> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
        this.mTopList = menuList;
        helper = new DatabaseHelper(context);
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

//        DecimalFormat formatter = new DecimalFormat("#.###.###");

        Locale locale = Locale.getDefault();
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(locale);
        formatSymbols.setDecimalSeparator(',');
        formatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("", formatSymbols);

        viewHolder.img.setImageResource(data.getImg());
        viewHolder.foodName.setText(data.getFoodName());
        viewHolder.foodDescription.setText(data.getFoodDescription());
        viewHolder.foodPrice.setText(decimalFormat.format(data.getFoodPrice()));
        viewHolder.foodPriceDiscount.setText(decimalFormat.format(data.getFoodPriceDiscount()));
        //viewHolder.buttonAddToChart.setEnabled(data.getButtonPosition() == 0 ? true : false);
        viewHolder.buttonAddToChart.setVisibility(data.getButtonPosition() == 0 ? View.VISIBLE : View.GONE);
        viewHolder.buttonAddPlusMinusChart.setVisibility(data.getButtonPosition() == 1 ? View.VISIBLE : View.GONE);

        //0

        //1
        final Bundle bundle = new Bundle();

        final int[] quantity = {menuList.get(i).getChartQuantity()};
        final int[] priceTotal = {menuList.get(i).getFoodPrice()};
        final int[] priceDiscountTotal = {menuList.get(i).getFoodPriceDiscount()};
        final int[] buttonPosition = {menuList.get(i).getButtonPosition()};

        final int foodPriceItem = menuList.get(i).getFoodPrice();
        final int foodPriceDiscountItem = menuList.get(i).getFoodPriceDiscount();

        final int position = viewHolder.getAdapterPosition();
        quantity[0] = 0;

        sharedPreferences = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //viewHolder.buttonAddToChart.setTag(0);

        /*if (viewHolder.buttonAddToChart.getTag().equals(0)){

            *//*viewHolder.buttonAddToChart.setVisibility(View.GONE);
            viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);*//*

            viewHolder.buttonAddToChart.setVisibility(SaveSharedPreference.getIsAddToCartVisible(context, true) ? View.VISIBLE : View.INVISIBLE);
            viewHolder.buttonAddPlusMinusChart.setVisibility(SaveSharedPreference.getIsAddToCartVisible(context, true) ? View.INVISIBLE : View.VISIBLE);
            viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

        }*/

        //2
        Log.e("Button Adapter", String.valueOf(viewHolder.buttonAddToChart.getTag(0)));

        //3
        /*if (SaveSharedPreference.getAllQuantity(context, quantityTotal) >= 1 ) {

            quantity[0] = SaveSharedPreference.getQuantity(context, quantity[0]);
            quantityTotal = SaveSharedPreference.getAllQuantity(context, quantityTotal);
            SaveSharedPreference.setQuantity(context, quantity[0]);
            SaveSharedPreference.setAllQuantity(context, quantityTotal);
            editor.putInt(QUANTITY, quantity[0]);
            editor.putInt(ALL_QUANTITY, quantityTotal);

            viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        }

        //4
        viewHolder.buttonAddToChart.setVisibility(SaveSharedPreference.getIsAddToCartVisible(context, true) ? View.VISIBLE : View.INVISIBLE);
        viewHolder.buttonAddPlusMinusChart.setVisibility(SaveSharedPreference.getIsAddToCartVisible(context, true) ? View.INVISIBLE : View.VISIBLE);
        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));
        viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getAllQuantity(context, quantityTotal)));*/

        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                //5
                MenuScreenActivity menuScreenActivity = (MenuScreenActivity) view.getContext();

                if (!foodChartFragment.isAdded()) {
                    if (quantity[0] == 0) {



                        viewHolder.buttonAddToChart.getTag();

                        //viewHolder.buttonAddToChart.setTag(i);
                        notifyItemChanged(foodPriceItem);
                        menuScreenActivity.numberCount.setVisibility(View.VISIBLE);

                        quantity[0] = 1;
                        priceTotal[0] = foodPriceItem;
                        priceDiscountTotal[0] = foodPriceDiscountItem;

                        quantityTotal = 1;
                        foodPriceTotal = foodPriceItem;
                        foodPriceDiscountTotal = foodPriceDiscountItem;

                        viewHolder.buttonAddToChart.setVisibility(View.GONE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                        logListItem();

                        viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().add(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

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

                    logListItem();

                    viewHolder.buttonAddToChart.getTag();

                    viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

                    viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                }
            }

            public void logListItem() {

                Log.e("Adapter Position", String.valueOf(position));

                Log.e("Tag Position", String.valueOf(viewHolder.buttonAddToChart.getTag()));

                bundle.putString("FoodName", menuList.get(i).getFoodName());
                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                // Save to SharedPreference
                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);
                SaveSharedPreference.setIsAddToCartVisible(context, false);

                /*editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                editor.putInt(ALL_QUANTITY, quantityTotal);
                editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                editor.putInt(QUANTITY, quantity[0]);
                editor.putInt(FOOD_PRICE, priceTotal[0]);
                editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);
                editor.putBoolean(IS_ADDTOCART_VISIBLE, false);

                editor.putBoolean("isAddToCartVisible", false).apply();
                editor.putBoolean("isPlusMinusAddToCartVisible", true).apply();*/

                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

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

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //6
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

                logListItem();

                viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();
            }

            public void logListItem() {

                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                // Save to SharedPreference
                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                /*editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                editor.putInt(ALL_QUANTITY, quantityTotal);
                editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                editor.putInt(QUANTITY, quantity[0]);
                editor.putInt(FOOD_PRICE, priceTotal[0]);
                editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);*/

                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

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

                //7
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

                        logListItem();

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));
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

                        logListItem();

                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));

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

                        // Showing the text that Counting number in the middle button Decrement and Increment
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        logListItem();

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                    }

                }
            }

            public void logListItem() {

                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                // Save to SharedPreference
                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                /*editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                editor.putInt(ALL_QUANTITY, quantityTotal);
                editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                editor.putInt(QUANTITY, quantity[0]);
                editor.putInt(FOOD_PRICE, priceTotal[0]);
                editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);*/

                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

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

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


    //fix this
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

            }
        };

    }




    public class ViewHolder extends RecyclerView.ViewHolder{

      public ImageView img;
      public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
      public Button buttonAddToChart;
      public CardView buttonAddPlusMinusChart;
      public AppCompatActivity activity;
      //public int currentPos = -1;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //item_food = itemView.findViewById(R.id.food_item_id);
            //foodChartFragment = itemView.findViewById(R.id.foodChartFragment);
            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.foodName);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodPriceDiscount = itemView.findViewById(R.id.foodPriceDiscount);
            buttonAddToChart = itemView.findViewById(R.id.buttonAddToChart);
            buttonAddPlusMinusChart = itemView.findViewById(R.id.buttonAddPlusMinusChart);
            decreaseChartQuantity = itemView.findViewById(R.id.decreaseChartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.increaseChartQuantity);
            chartQuantity = itemView.findViewById(R.id.chartQuantity);
            activity = (AppCompatActivity) itemView.getContext();

            /*mCommunication = mCommunicator;*/

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }

}
