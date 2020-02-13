package com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterBestCusineCategories;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterOrderPaymentDetails extends RecyclerView.Adapter<AdapterOrderPaymentDetails.ViewHolder> {

    List<DataTransaction> topList;
    Context context;
    private DatabaseHelper helper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //BestCusineFragment bestCusineFragment = new BestCusineFragment();

    public AdapterOrderPaymentDetails(List<DataTransaction> topList, Context context) {
        this.topList = topList;
        this.context = context;
        helper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public AdapterOrderPaymentDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_payment_details, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrderPaymentDetails.ViewHolder viewHolder, final int i) {

        final DataTransaction data = topList.get(i);

        DecimalHelper decimalHelper = new DecimalHelper();

        viewHolder.foodCategoriesName.setText(data.getFoodTransName());
        viewHolder.foodCategoriesPrice.setText(decimalHelper.formatter(data.getFoodTransPriceTotal()));

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView foodCategoriesName, foodCategoriesPrice;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            foodCategoriesName = itemView.findViewById(R.id.foodCategoriesName);
            foodCategoriesPrice = itemView.findViewById(R.id.foodCategoriesPrice);

        }
    }

}
