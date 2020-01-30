package com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.ALL_QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_NAME;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.IS_ADDTOCART_VISIBLE;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.QUANTITY;

public class AdapterOrderScreenOrder  extends RecyclerView.Adapter<AdapterOrderScreenOrder.ViewHolder> {

    List<DataKhanaval> menuDetailList;
    Context context;
    int quantityTotal;
    int foodPriceTotal;
    int foodPriceDiscountTotal;
    //boolean wishlistAdded = false;

    public AdapterOrderScreenOrder(List<DataKhanaval> menuDetailList, Context context) {
        this.menuDetailList = menuDetailList;
        this.context = context;
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

        viewHolder.foodName.setText(menuDetailList.get(i).getFoodName());
        //viewHolder.foodPrice.setText(String.valueOf(menuDetailList.get(i).getFoodPrice()));
        viewHolder.img.setImageResource(menuDetailList.get(i).getImg());
        viewHolder.foodPrice.setText(String.valueOf(SaveSharedPreference.getFoodPriceTotal(context, 0)));
        viewHolder.foodPriceDiscount.setText(String.valueOf(SaveSharedPreference.getFoodPriceDiscountTotal(context, 0)));
        viewHolder.chartQuantity.setText(String.valueOf(SaveSharedPreference.getAllQuantity(context, 0)));

        final int quantity[] = {menuDetailList.get(i).getChartQuantity()};

        quantity[0] = SaveSharedPreference.getAllQuantity(context, 0);

        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

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
        return menuDetailList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img, favouriteFood;
        public TextView foodName, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
        public ImageButton openNotes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodPriceDiscount = itemView.findViewById(R.id.foodPriceDiscount);
            decreaseChartQuantity = itemView.findViewById(R.id.decreaseChartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.increaseChartQuantity);
            chartQuantity = itemView.findViewById(R.id.chartQuantity);
            openNotes = itemView.findViewById(R.id.openNotes);
            favouriteFood = itemView.findViewById(R.id.favouriteFood);

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        }
    }
}

