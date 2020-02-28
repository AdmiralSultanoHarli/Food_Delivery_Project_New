package com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderScreenOrder;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class OrderScreenOrderFragment extends Fragment {

    RecyclerView orderScreenOrder;
    AdapterOrderScreenOrder orderScreenOrderAdapter;
    ArrayList<DataTransaction> allData;
    private DatabaseHelper helper;
    SQLiteDatabase db;


    public OrderScreenOrderFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_screen_order_summary, container, false);

        helper = new DatabaseHelper(getActivity());
        orderScreenOrder = v.findViewById(R.id.orderScreenOrderRecyclerView);
        allData = helper.listDataTransaction();
        //db = helper.getWritableDatabase();
        orderScreenOrder.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        orderScreenOrder.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0){

            orderScreenOrderAdapter = new AdapterOrderScreenOrder(allData, getActivity());
            orderScreenOrder.setAdapter(orderScreenOrderAdapter);

        }

        return v;

    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();

        db.close();
        Log.e("Order Screen", "Called");
    }*/

}