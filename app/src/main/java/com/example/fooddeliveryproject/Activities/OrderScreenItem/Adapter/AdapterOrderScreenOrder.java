package com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuDetailsScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;
import java.util.Locale;

public class AdapterOrderScreenOrder extends RecyclerView.Adapter<AdapterOrderScreenOrder.ViewHolder> {

    List<DataTransaction> topList;
    List<DataTransaction> mTopList;
    Context context;
    DatabaseHelper helper;
    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;
    //boolean wishlistAdded = false;

    public AdapterOrderScreenOrder(List<DataTransaction> topList, Context context) {
        this.topList = topList;
        this.context = context;
        this.mTopList = topList;
        helper = new DatabaseHelper(context);

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

        //final DataTransaction data = topList.get(i);
        DecimalHelper decimalHelper = new DecimalHelper();

        /*viewHolder.foodName.setText(menuDetailList.get(i).getFoodName());
        //viewHolder.foodPrice.setText(String.valueOf(menuDetailList.get(i).getFoodPrice()));
        viewHolder.img.setImageResource(menuDetailList.get(i).getImg());
        viewHolder.foodPrice.setText(decimalFormat.format(SaveSharedPreference.getFoodPriceTotal(context, 0)));
        viewHolder.foodPriceDiscount.setText(decimalFormat.format(SaveSharedPreference.getFoodPriceDiscountTotal(context, 0)));
        viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getAllQuantity(context, 0)));
        viewHolder.img.setImageResource(topList.get(i).getImg());*/


        final int quantity[] = {topList.get(i).getFoodTransItemCount()};

        quantity[0] = SaveSharedPreference.getAllQuantity(context, 0);

        //viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

        Log.e("transaction data",viewHolder.toString());
        viewHolder.foodName.setText(topList.get(i).getFoodTransName());
        viewHolder.foodPrice.setText(decimalHelper.formatter(topList.get(i).getFoodTransPrice()));
        viewHolder.foodPriceDiscount.setText(decimalHelper.formatter(topList.get(i).getFoodTransPriceDiscount()));
        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));
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

                if (quantity[0] == 100){

                    return;

                }

                quantity[0]++;
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] == 0){

                    return;

                }
                quantity[0]--;
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

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

        //viewHolder.favouriteFood.setColorFilter(ContextCompat.getColor(context, R.color.colorButtonGray));

       /* viewHolder.favouriteFood.setOnClickListener(new View.OnClickListener() {
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
        });*/

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

