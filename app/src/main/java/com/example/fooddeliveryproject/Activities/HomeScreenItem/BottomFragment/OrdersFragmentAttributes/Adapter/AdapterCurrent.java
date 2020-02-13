package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.HomeScreenActivity;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersDetailsFragment;
import com.example.fooddeliveryproject.Activities.Model.DataTransactionDone;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterCurrent extends RecyclerView.Adapter<AdapterCurrent.ViewHolder> {

    List<DataTransactionDone> historyList;
    Context context;

    public AdapterCurrent(List<DataTransactionDone> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCurrent.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_order_history, viewGroup, false);
        AdapterCurrent.ViewHolder viewHolder = new AdapterCurrent.ViewHolder(v);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterCurrent.ViewHolder viewHolder, final int i) {

        DecimalHelper decimalHelper = new DecimalHelper();

        viewHolder.foodName.setText(historyList.get(i).getShopName());
        viewHolder.date.setText(historyList.get(i).getDate());
        viewHolder.orderTracker.setText(historyList.get(i).getOrderTracker());
        viewHolder.orderPrice.setText(decimalHelper.formatter(historyList.get(i).getTotalPayment()));
        viewHolder.img.setImageResource(historyList.get(i).getShopImg());

        viewHolder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeScreenActivity homeScreenActivity = (HomeScreenActivity) view.getContext();
                homeScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new OrdersDetailsFragment()).addToBackStack(null).commit();

                SaveSharedPreference.setShopName(context, historyList.get(i).getShopName());
                SaveSharedPreference.setTransactionDate(context, historyList.get(i).getDate());
                SaveSharedPreference.setTransactionPaymentMethod(context, historyList.get(i).getPaymentMethod());
                SaveSharedPreference.setTransactionTotalPayment(context, historyList.get(i).getTotalPayment());
                SaveSharedPreference.setShopImg(context,historyList.get(i).getShopImg());

                homeScreenActivity.isOrderDetailsFragmentOpened = true;

            }
        });

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName, date, orderTracker, orderPrice, buttonDetail;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.orderFoodImage);
            foodName = itemView.findViewById(R.id.orderFoodName);
            date = itemView.findViewById(R.id.date);
            orderTracker = itemView.findViewById(R.id.orderPaymentMethod);
            orderPrice = itemView.findViewById(R.id.orderPrice);
            buttonDetail = itemView.findViewById(R.id.buttonDetail);

        }
    }

}
