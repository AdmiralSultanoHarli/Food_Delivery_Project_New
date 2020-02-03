package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;


public class OrdersDetailsFragment extends Fragment {

    TextView date, orderPrice, orderTracker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_orders_details, container, false);

        date = v.findViewById(R.id.date);
        orderPrice = v.findViewById(R.id.orderPrice);
        orderTracker = v.findViewById(R.id.orderTracker);

        DecimalHelper decimalHelper = new DecimalHelper();

        date.setText(SaveSharedPreference.getDate(getContext(), ""));
        orderPrice.setText(decimalHelper.formatter(SaveSharedPreference.getTotalPayment(getContext(), 0)));
        orderTracker.setText("Payment done by " + SaveSharedPreference.getPaymentName(getContext(), ""));

        return v;

    }


}
