package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
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
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> {

    List<DataKhanaval> menuList;
    Context context;
    Dialog mDialog;
    //int totalQuantity;
    FoodChartFragment foodChartFragment = new FoodChartFragment();
    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;

    public AdapterMenuScreen(List<DataKhanaval> menuList, Context context) {
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

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.img.setImageResource(menuList.get(i).getImg());
        viewHolder.foodName.setText(menuList.get(i).getFoodName());
        viewHolder.foodDescription.setText(menuList.get(i).getFoodDescription());
        viewHolder.foodPrice.setText(String.valueOf(menuList.get(i).getFoodPrice()));
        viewHolder.foodPriceDiscount.setText(String.valueOf(menuList.get(i).getFoodPriceDiscount()));
        final Bundle bundle = new Bundle();

        final int[] quantity = {menuList.get(i).getChartQuantity()};
        final int[] priceTotal = {menuList.get(i).getFoodPrice()};
        final int[] priceDiscountTotal = {menuList.get(i).getFoodPriceDiscount()};

        final int foodPriceItem = menuList.get(i).getFoodPrice();
        final int foodPriceDiscountItem = menuList.get(i).getFoodPriceDiscount();
        quantity[0] = 0;

        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!foodChartFragment.isAdded()) {
                    if (quantity[0] == 0) {

                        quantity[0] = 1;
                        quantityTotal = 1;
                        foodPriceTotal = foodPriceItem;
                        foodPriceDiscountTotal = foodPriceDiscountItem;

                        viewHolder.buttonAddToChart.setVisibility(View.INVISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                        // Showing the first item quantity shown in the middle decrement and increment
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        // Showing the showing all item total quantity for the FoodChartFragment
                        bundle.putString("FoodName", menuList.get(i).getFoodName());
                        bundle.putString("FoodCount", String.valueOf(quantityTotal));
                        bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                        bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                        // Showing the log for tracking the 1 item total
                        Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                        Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                        Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                        // If i want just to show the 1 item total quantity i can use like the comment bellow
                        /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                        bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                        bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                        foodChartFragment.setArguments((bundle));
                        viewHolder.activity.getSupportFragmentManager().beginTransaction().add(R.id.foodChartFragment, foodChartFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();


                    }
                }else if (foodChartFragment.isAdded()){

                    if (quantityTotal == 100){

                        Toast.makeText(context, "Sorry the item just can be 100", Toast.LENGTH_SHORT).show();
                        return;

                    }

                    quantity[0] = 1;
                    quantityTotal++;
                    foodPriceTotal+= foodPriceItem;
                    foodPriceDiscountTotal+= foodPriceDiscountItem;

                    viewHolder.buttonAddToChart.setVisibility(View.INVISIBLE);
                    viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                    // Showing the first item quantity shown in the middle decrement and increment
                    viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                    // Showing the showing all item total quantity for the FoodChartFragment
                    bundle.putString("FoodName", menuList.get(i).getFoodName());
                    bundle.putString("FoodCount", String.valueOf(quantityTotal));
                    bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                    bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                    // Showing the log for tracking the 1 item total
                    Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                    Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                    Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                    // If i want just to show the 1 item total quantity i can use like the comment bellow
                    /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                    bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                    bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                    foodChartFragment.setArguments(bundle);
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

                // Total All Item
                quantityTotal++;
                foodPriceTotal+= foodPriceItem;
                foodPriceDiscountTotal += foodPriceDiscountItem;

                // Total One Item
                quantity[0]++;
                priceTotal[0] += foodPriceItem;
                priceDiscountTotal[0] += foodPriceDiscountItem;

                // Showing the text that Counting number in the middle button Decrement and Increment
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                // Showing the showing all item total quantity for the FoodChartFragment
                bundle.putString("FoodCount", String.valueOf(quantityTotal));
                bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                // Showing the log for tracking the 1 item total
                Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                // If i want just to show the 1 item total quantity i can use like the comment bellow
                /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                foodChartFragment.setArguments(bundle);
                viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] == 1) {

                    //quantity[0] = 1;
                    quantity[0]--;
                    quantityTotal--;
                    quantity[0] = 0;
                    priceTotal[0] = foodPriceItem;
                    priceDiscountTotal[0] = foodPriceDiscountItem;
                    viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                    viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                    viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                } else {

                    if (quantityTotal > 1) {

                        // Total All Item
                        quantityTotal--;
                        foodPriceTotal -= foodPriceItem;
                        foodPriceDiscountTotal -= foodPriceDiscountItem;

                        // Total 1 item
                        quantity[0]--;
                        priceTotal[0] -= foodPriceItem;
                        priceDiscountTotal[0] -= foodPriceDiscountItem;

                        /**/

                        // Showing the text that Counting number in the middle button Decrement and Increment
                        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

                        // Showing the showing all item total quantity for the FoodChartFragment
                        bundle.putString("FoodCount", String.valueOf(quantityTotal));
                        bundle.putString("FoodPrice", String.valueOf(foodPriceTotal));
                        bundle.putString("FoodDiscount", String.valueOf(foodPriceDiscountTotal));

                        // Showing the log for tracking the 1 item total
                        Log.e("FoodCount", menuList.get(i).getFoodName() + " = " + String.valueOf(quantity[0]));
                        Log.e("FoodPrice", menuList.get(i).getFoodName() + " = " + String.valueOf(priceTotal[0]));
                        Log.e("FoodDiscount", menuList.get(i).getFoodName() + " = " + String.valueOf(priceDiscountTotal[0]));

                        // If i want just to show the 1 item total quantity i can use like the comment bellow
                        /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                        bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                        bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

                        foodChartFragment.setArguments(bundle);
                        viewHolder.activity.getSupportFragmentManager().beginTransaction().detach(foodChartFragment).attach(foodChartFragment).commit();

                    } else if (quantityTotal == 1) {

                        quantity[0] = 0;
                        viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                        viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                        viewHolder.activity.getSupportFragmentManager().beginTransaction().remove(foodChartFragment).commit();

                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      public ImageView img;
      public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
      public Button buttonAddToChart;
      public CardView buttonAddPlusMinusChart;
      public AppCompatActivity activity;

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
