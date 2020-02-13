package com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterBestCusineCategories;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderPaymentDetails;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class OrderPaymentDetailsFragment extends Fragment {

    RecyclerView paymentDetailsRecyclerview;
    AdapterOrderPaymentDetails adapterOrderPaymentDetails;
    ArrayList<DataTransaction> allData;
    private DatabaseHelper helper;

    public OrderPaymentDetailsFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_payment_details, container, false);

        helper = new DatabaseHelper(getActivity());
        paymentDetailsRecyclerview = v.findViewById(R.id.paymentDetailsRecyclerview);
        allData = helper.listDataTransaction();
        paymentDetailsRecyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        paymentDetailsRecyclerview.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0) {

            paymentDetailsRecyclerview.setVisibility(View.VISIBLE);
            adapterOrderPaymentDetails = new AdapterOrderPaymentDetails(allData, getActivity());
            paymentDetailsRecyclerview.setAdapter(adapterOrderPaymentDetails);

        }

        return v;

    }
}
