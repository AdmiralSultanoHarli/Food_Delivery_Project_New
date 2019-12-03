package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.DataFoodMenu;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.R;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> {

    List<DataFoodMenu> menuList;
    Context context;
    Dialog mDialog;
    //public int quantity = 1;
    FoodChartFragment foodChartFragment = new FoodChartFragment();
    TextView chartQuantity;
    //boolean foodChartIsShowing = true;


    //private FragmentCommunication mCommunicator;

    public AdapterMenuScreen(List<DataFoodMenu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterMenuScreen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_menu_screen, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v/*, mCommunicator*/);

        /*//dialog init
        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.view_floating_food_chart);

        viewHolder.item_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TextView itemCount = mDialog.findViewById(R.id.itemCount);
                TextView price = mDialog.findViewById(R.id.price);
                TextView discountPrice = mDialog.findViewById(R.id.discountPrice);
                TextView itemName = mDialog.findViewById(R.id.itemName);
                //itemCount.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodName());
                price.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodPrice());
                discountPrice.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodPriceDiscount());
                itemName.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodName());
                mDialog.show();

            }
        });*/

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Test! " + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

            }
        });*/

        return viewHolder;

    }

   /* public void displayQuantity(int number){

        chartQuantity.setText(""+number);
        *//*Intent intent = new Intent(context, MenuScreenFragment.class);
        intent.putExtra("ss", chartQuantity);*//*

    }*/



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.img.setImageResource(menuList.get(i).getImg());
        viewHolder.foodName.setText(menuList.get(i).getFoodName());
        viewHolder.foodDescription.setText(menuList.get(i).getFoodDescription());
        viewHolder.foodPrice.setText(String.valueOf(menuList.get(i).getFoodPrice()));
        viewHolder.foodPriceDiscount.setText(String.valueOf(menuList.get(i).getFoodPriceDiscount()));
        final int[] quantity = {menuList.get(i).getChartQuantity()};
        final int[] priceTotal = {menuList.get(i).getFoodPrice()};
        final int[] priceDiscountTotal = {menuList.get(i).getFoodPriceDiscount()};
        final Bundle bundle = new Bundle();
        /*priceTotal = menuList.get(i).getFoodPrice();
        priceDiscountTotal= menuList.get(i).getFoodPriceDiscount();*/
        priceTotal[0] = menuList.get(i).getFoodPrice();
        priceDiscountTotal[0] = menuList.get(i).getFoodPriceDiscount();
        quantity[0] = 0;


        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("priceFood", String.valueOf(priceTotal[0] + 20000));
                Log.e("Button test", String.valueOf(quantity[0]));
                Log.e("The position is", String.valueOf(viewHolder.getAdapterPosition()));

                if (!foodChartFragment.isAdded()) {
                    if (quantity[0] == 0) {

                        quantity[0] = 1;
                        viewHolder.buttonAddToChart.setVisibility(View.INVISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                        bundle.putString("FoodName", menuList.get(i).getFoodName());
                        bundle.putString("FoodCount", String.valueOf(quantity[0]));
                        bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                        bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));

                        Log.e("test Bundle", menuList.get(i).getFoodName());
                        foodChartFragment.setArguments((bundle));

                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

                        Log.e(menuList.get(i).getFoodName(), String.valueOf(quantity[0]));

                    }
                }/*else if (foodChartFragment.isAdded()){

                    viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).commit();
                    bundle.putString("FoodName", menuList.get(i).getFoodName());
                    bundle.putString("FoodCount", String.valueOf(quantity[0]));
                    bundle.putString("FoodPrice", menuList.get(i).getFoodPrice());
                    bundle.putString("FoodDiscount", menuList.get(i).getFoodPriceDiscount());
                    foodChartFragment.setArguments((bundle));


                }*//*else {

                    viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                }*/
                /*viewHolder.buttonAddToChart.setVisibility(View.GONE);
                viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);*/

//
                /*bundle.putString("Food", menuList.get(i).getFoodName());
                Log.e("test Bundle", menuList.get(i).getFoodName());
                foodChartFragment.setArguments(bundle);*/


               /* Log.e(menuList.get(i).getFoodName(), String.valueOf(quantity[0]));
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0] = 1));

                if (quantity[0] == 1){

                    if(foodChartFragment.isAdded()){

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).commit();
                        Log.e("Already added", String.valueOf(viewHolder.activity.getContentTransitionManager()));

                    }else{

                        viewHolder.activity.getSupportFragmentManager().beginTransaction().add(R.id.foodChartFragment, foodChartFragment).commit();

                    }

                }*/

            }
        });

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AppCompatActivity activity = (AppCompatActivity) view.getContext();

                Log.e("Where is the button", menuList.get(i).getFoodName());
                Log.e("The position is", String.valueOf(viewHolder.getAdapterPosition()));



                if (quantity[0] == 100){

                    return;

                }

                quantity[0]++;
                priceTotal[0] = priceTotal[0] + priceTotal[0];
                priceDiscountTotal[0] = priceDiscountTotal[0] + priceDiscountTotal[0];
                Log.e(menuList.get(i).getFoodName(), String.valueOf(quantity[0]));
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                bundle.putString("FoodCount", String.valueOf(quantity[0]));
                bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));
                foodChartFragment.setArguments(bundle);

                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();



                //foodChartFragment.setArguments(bundle);

                        /*if (menuList.get(i).isAddedToCart()) {

                            if (quantity == 100) {
                                return;
                            }
                            quantity++;
                            menuList.get(i).setChartQuantity(quantity);
                            viewHolder.displayQuantity(quantity);

                        }*/
            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AppCompatActivity activity = (AppCompatActivity) view.getContext();


                if (quantity[0] == 1) {

                    quantity[0] = 0;
                    viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                    viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                    viewHolder.activity.getSupportFragmentManager().beginTransaction().remove(foodChartFragment).commit();
                    return;

                }

                priceDiscountTotal[0] = priceDiscountTotal[0] - priceDiscountTotal[0];
                priceTotal[0] = priceTotal[0] - priceTotal[0];
                quantity[0]--;
                Log.e(menuList.get(i).getFoodName(), String.valueOf(quantity[0]));
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
                bundle.putString("FoodCount", String.valueOf(quantity[0]));
                bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));
                foodChartFragment.setArguments(bundle);
                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();
                //viewHolder.activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).commit();

                //foodChartFragment.setArguments(bundle);

                        /*if (menuList.get(i).isAddedToCart()){

                            if (quantity == 1) {
                                viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                                viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                                activity.getSupportFragmentManager().beginTransaction().remove(foodChartFragment).commit();
                                Log.e("menuList", String.valueOf(menuList.get(i).getFoodName()));
                                menuList.get(i).setAddedToCart(false);
                                return;
                            }
                            quantity--;
                            viewHolder.displayQuantity(quantity);

                        }*/

            }
        });





    }



    @Override
    public int getItemCount() {
        return menuList.size();
    }

   /* public interface FragmentCommunication{

        void respond(int i, String foodName);

    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{

      public ImageView img;
      public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
      public Button buttonAddToChart;
      public CardView buttonAddPlusMinusChart;
      public AppCompatActivity activity;
      /*FragmentCommunication mCommunication;*/
      //private PopupWindow mPopupWindow;

       /* public void displayQuantity(int number){

            chartQuantity.setText(""+number);
            *//*Intent intent = new Intent(context, MenuScreenFragment.class);
            intent.putExtra("ss", chartQuantity);*//*

        }*/


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
