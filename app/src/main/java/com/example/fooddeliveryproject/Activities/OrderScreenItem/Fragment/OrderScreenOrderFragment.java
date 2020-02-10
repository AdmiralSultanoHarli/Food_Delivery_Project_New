package com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderScreenOrder;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.EventListener;

public class OrderScreenOrderFragment extends Fragment {

    RecyclerView orderScreenOrder;
    AdapterOrderScreenOrder orderScreenOrderAdapter;
    ArrayList<DataTransaction> allData;
    private DatabaseHelper helper;


    public OrderScreenOrderFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_screen_order_summary, container, false);

        helper = new DatabaseHelper(getActivity());
        orderScreenOrder = v.findViewById(R.id.orderScreenOrderRecyclerView);
        allData = helper.listDataTransaction();
        orderScreenOrder.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        orderScreenOrder.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0){

            orderScreenOrderAdapter = new AdapterOrderScreenOrder(allData, getActivity());
            orderScreenOrder.setAdapter(orderScreenOrderAdapter);
            Toast.makeText(getContext(), "THERE IS DATA", Toast.LENGTH_SHORT).show();
//            Log.e("data", String.valueOf(allData.get(1)));

        }else {

            Toast.makeText(getContext(), "THERE IS NO DATA", Toast.LENGTH_SHORT).show();

        }

        return v;

    }


}