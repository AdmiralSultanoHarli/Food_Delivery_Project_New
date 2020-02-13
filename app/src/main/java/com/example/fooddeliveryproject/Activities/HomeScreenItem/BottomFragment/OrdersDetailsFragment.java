package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;


public class OrdersDetailsFragment extends Fragment {

    TextView date, orderPrice, orderPaymentMethod, orderFoodName;
    ImageView orderFoodImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_orders_details, container, false);

        orderFoodName = v.findViewById(R.id.orderFoodName);
        date = v.findViewById(R.id.date);
        orderPaymentMethod = v.findViewById(R.id.orderPaymentMethod);
        orderPrice = v.findViewById(R.id.orderPrice);
        orderFoodImage = v.findViewById(R.id.orderFoodImage);

        DecimalHelper decimalHelper = new DecimalHelper();

        orderFoodName.setText(SaveSharedPreference.getShopName(getContext(), ""));
        date.setText(SaveSharedPreference.getTransactionDate(getContext(), ""));
        orderPaymentMethod.setText("Payment done by " + SaveSharedPreference.getTransactionPaymentMethod(getContext(), ""));
        orderPrice.setText(decimalHelper.formatter(SaveSharedPreference.getTransactionTotalPayment(getContext(), 0)));
        orderFoodImage.setImageResource(SaveSharedPreference.getShopImg(getContext(), 0));

        /*SaveSharedPreference.setShopName(context, historyList.get(i).getShopName());
        SaveSharedPreference.setTransactionDate(context, historyList.get(i).getDate());
        SaveSharedPreference.setTransactionPaymentMethod(context, historyList.get(i).getPaymentMethod());
        SaveSharedPreference.setTransactionTotalPayment(context, historyList.get(i).getTotalPayment());
        SaveSharedPreference.setShopImg(context,historyList.get(i).getShopImg());*/

        return v;

    }


}
