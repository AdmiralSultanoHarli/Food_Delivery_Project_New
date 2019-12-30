package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Data.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.R;

import org.xml.sax.SAXException;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.ALL_QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_ADDTOCART_VISIBLE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MY_PREFERENCE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.QUANTITY;

import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> {

    List<DataKhanaval> menuList;
    Context context;
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

        viewHolder.img.setImageResource(menuList.get(i).getImg());
        viewHolder.foodName.setText(menuList.get(i).getFoodName());
        viewHolder.foodDescription.setText(menuList.get(i).getFoodDescription());
        viewHolder.foodPrice.setText(String.valueOf(menuList.get(i).getFoodPrice()));
        viewHolder.foodPriceDiscount.setText(String.valueOf(menuList.get(i).getFoodPriceDiscount()));
        viewHolder.buttonAddToChart.setTag(menuList.get(i).getButtonPosition());
        viewHolder.buttonAddPlusMinusChart.setTag(menuList.get(i).getButtonPosition());

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

        Log.e("Button Adapter", String.valueOf(viewHolder.buttonAddToChart.getTag(0)));

        if (SaveSharedPreference.getAllQuantity(context, quantityTotal) >= 1 ) {

            quantity[0] = SaveSharedPreference.getQuantity(context, quantity[0]);
            quantityTotal = SaveSharedPreference.getAllQuantity(context, quantityTotal);
            SaveSharedPreference.setQuantity(context, quantity[0]);
            SaveSharedPreference.setAllQuantity(context, quantityTotal);
            editor.putInt(QUANTITY, quantity[0]);
            editor.putInt(ALL_QUANTITY, quantityTotal);

            viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        }

        viewHolder.buttonAddToChart.setVisibility(SaveSharedPreference.getIsAddToCartVisible(context, true) ? View.VISIBLE : View.INVISIBLE);
        viewHolder.buttonAddPlusMinusChart.setVisibility(SaveSharedPreference.getIsAddToCartVisible(context, true) ? View.INVISIBLE : View.VISIBLE);
        viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!foodChartFragment.isAdded()) {
                    if (quantity[0] == 0) {

                        Log.e("Adapter Position", String.valueOf(position));

                        Log.e("Tag Position", String.valueOf(viewHolder.buttonAddToChart.getTag()));

                        viewHolder.buttonAddToChart.getTag();

                        //viewHolder.buttonAddToChart.setTag(i);
                        notifyItemChanged(foodPriceItem);

                        quantity[0] = 1;
                        priceTotal[0] = foodPriceItem;
                        priceDiscountTotal[0] = foodPriceDiscountItem;

                        quantityTotal = 1;
                        foodPriceTotal = foodPriceItem;
                        foodPriceDiscountTotal = foodPriceDiscountItem;

                        viewHolder.buttonAddToChart.setVisibility(View.GONE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                        // Showing the showing all item total quantity for the FoodChartFragment
                        /*bundle.putString("FoodName", menuList.get(i).getFoodName());
                        bundle.putString("FoodCount", String.valueOf(quantityTotal));
                        bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                        bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));*/

                        // Save to SharedPreference
                        SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                        SaveSharedPreference.setAllQuantity(context, quantityTotal);
                        SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                        SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                        SaveSharedPreference.setQuantity(context, quantity[0]);
                        SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                        SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);
                        SaveSharedPreference.setIsAddToCartVisible(context, false);
                        //SaveSharedPreference.setIsAddToCartVisible(context, false);

                        editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                        editor.putInt(ALL_QUANTITY, quantityTotal);
                        editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                        editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                        editor.putInt(QUANTITY, quantity[0]);
                        editor.putInt(FOOD_PRICE, priceTotal[0]);
                        editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);
                        editor.putBoolean(IS_ADDTOCART_VISIBLE, false);

                        /*editor.putBoolean("isAddToCartVisible", false).apply();
                        editor.putBoolean("isPlusMinusAddToCartVisible", true).apply();*/

                        // Showing the first item quantity shown in the middle decrement and increment
                        //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));


                        // Showing the log for tracking the 1 item total
                        Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                        Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                        Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                        Log.e("FoodCountTotal",  " = " + quantityTotal);
                        Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                        Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                        // If i want just to show the 1 item total quantity i can use like the comment bellow
                        /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                        bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                        bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                        //foodChartFragment.setArguments((bundle));
                        viewHolder.activity.getSupportFragmentManager().beginTransaction().add(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        //AdapterMenuScreen.this.notifyDataSetChanged();

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

                    // Showing the showing all item total quantity for the FoodChartFragment
                    /*bundle.putString("FoodName", menuList.get(i).getFoodName());
                    bundle.putString("FoodCount", String.valueOf(quantityTotal));
                    bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                    bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));*/

                    Log.e("Adapter Position", String.valueOf(position));

                    Log.e("Tag Position", String.valueOf(viewHolder.buttonAddToChart.getTag()));

                    viewHolder.buttonAddToChart.getTag();

                    // Save to SharedPreference
                    SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                    SaveSharedPreference.setAllQuantity(context, quantityTotal);
                    SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                    SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                    SaveSharedPreference.setQuantity(context, quantity[0]);
                    SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                    SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);
                    SaveSharedPreference.setIsAddToCartVisible(context, false);

                    editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                    editor.putInt(ALL_QUANTITY, quantityTotal);
                    editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                    editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                    editor.putInt(QUANTITY, quantity[0]);
                    editor.putInt(FOOD_PRICE, priceTotal[0]);
                    editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);
                    editor.putBoolean(IS_ADDTOCART_VISIBLE, false);

                    /*editor.putBoolean("isAddToCartVisible", false).apply();
                    editor.putBoolean("isPlusMinusAddToCartVisible", true).apply();*/

                    // Showing the first item quantity shown in the middle decrement and increment
                    //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                    viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

                    // Showing the log for tracking the 1 item total
                    Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                    Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                    Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                    Log.e("FoodCountTotal",  " = " + quantityTotal);
                    Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                    Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);
                    // If i want just to show the 1 item total quantity i can use like the comment bellow
                    /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                    bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                    bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                    //foodChartFragment.setArguments(bundle);
                    viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                }
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

                // Showing the showing all item total quantity for the FoodChartFragment
                /*bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));*/

                // Save to SharedPreference
                SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                SaveSharedPreference.setAllQuantity(context, quantityTotal);
                SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                SaveSharedPreference.setQuantity(context, quantity[0]);
                SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                editor.putInt(ALL_QUANTITY, quantityTotal);
                editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                editor.putInt(QUANTITY, quantity[0]);
                editor.putInt(FOOD_PRICE, priceTotal[0]);
                editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);

                // Showing the log for tracking the 1 item total
                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                Log.e("FoodCountTotal", " = " + quantityTotal);
                Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                // Showing the text that Counting number in the middle button Decrement and Increment
                //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, quantity[0])));

                // If i want just to show the 1 item total quantity i can use like the comment bellow
                /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                //foodChartFragment.setArguments(bundle);
                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
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

                        /*bundle.putString("FoodCount", String.valueOf(quantityTotal));
                        bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                        bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));*/

                        // Save to SharedPreference
                        SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                        SaveSharedPreference.setAllQuantity(context, quantityTotal);
                        SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                        SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                        SaveSharedPreference.setQuantity(context, quantity[0]);
                        SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                        SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);
                        SaveSharedPreference.setIsAddToCartVisible(context, true);

                        editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                        editor.putInt(ALL_QUANTITY, quantityTotal);
                        editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                        editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                        editor.putInt(QUANTITY, quantity[0]);
                        editor.putInt(FOOD_PRICE, priceTotal[0]);
                        editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);
                        editor.putBoolean(IS_ADDTOCART_VISIBLE, true);

                        Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                        Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                        Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                        Log.e("FoodCountTotal", " = " + quantityTotal);
                        Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                        Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

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

                       /* bundle.putString("FoodCount", String.valueOf(quantityTotal));
                        bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                        bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));*/

                        // Save to SharedPreference
                        SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                        SaveSharedPreference.setAllQuantity(context, quantityTotal);
                        SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                        SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                        SaveSharedPreference.setQuantity(context, quantity[0]);
                        SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                        SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                        editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                        editor.putInt(ALL_QUANTITY, quantityTotal);
                        editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                        editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                        editor.putInt(QUANTITY, quantity[0]);
                        editor.putInt(FOOD_PRICE, priceTotal[0]);
                        editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);

                        Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                        Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                        Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                        Log.e("FoodCountTotal", " = " + quantityTotal);
                        Log.e("FoodPriceTotal", " = " + foodPriceTotal);
                        Log.e("FoodDiscountTotal", " = " + foodPriceDiscountTotal);

                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                        //viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));

                        //foodChartFragment.setArguments(bundle);
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

                        // viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getQuantity(context, 0)));

                        // Showing the showing all item total quantity for the FoodChartFragment
                        /*bundle.putString("FoodCount", String.valueOf(quantityTotal));
                        bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                        bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));*/

                        // Save to SharedPreference
                        SaveSharedPreference.setFoodName(context, menuList.get(i).getFoodName());
                        SaveSharedPreference.setAllQuantity(context, quantityTotal);
                        SaveSharedPreference.setFoodPriceTotal(context, foodPriceTotal);
                        SaveSharedPreference.setFoodPriceDiscountTotal(context, foodPriceDiscountTotal);
                        SaveSharedPreference.setQuantity(context, quantity[0]);
                        SaveSharedPreference.setFoodPrice(context, priceTotal[0]);
                        SaveSharedPreference.setFoodPriceDiscount(context, priceDiscountTotal[0]);

                        editor.putString(FOOD_NAME, menuList.get(i).getFoodName());
                        editor.putInt(ALL_QUANTITY, quantityTotal);
                        editor.putInt(FOOD_PRICE_TOTAL, foodPriceTotal);
                        editor.putInt(FOOD_PRICE_DISCOUNT_TOTAL, foodPriceDiscountTotal);
                        editor.putInt(QUANTITY, quantity[0]);
                        editor.putInt(FOOD_PRICE, priceTotal[0]);
                        editor.putInt(FOOD_PRICE_DISCOUNT, priceDiscountTotal[0]);

                        // Showing the log for tracking the 1 item total
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

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

      public ImageView img;
      public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
      public Button buttonAddToChart;
      public CardView buttonAddPlusMinusChart;
      public AppCompatActivity activity;
      //public int currentPos = -1;

        public ViewHolder(@NonNull final View itemView/*, final FragmentCommunication mCommunicator*/) {
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
