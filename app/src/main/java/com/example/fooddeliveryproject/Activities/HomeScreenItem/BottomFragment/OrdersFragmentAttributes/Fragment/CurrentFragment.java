package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Adapter.AdapterCurrent;
import com.example.fooddeliveryproject.Activities.Model.DataTransactionDone;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderPaymentDetails;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.Collections;

public class CurrentFragment extends Fragment{

    /*String dates;
    String[] date;
    String[] foodName = {"Nasi Padang"};
    String[] orderTracker = {"Order Placed"};
    int[] foodPrice;
    int[] img = {R.drawable.nasi_padang_s};
    TextView noItem;*/

    private DatabaseHelper helper;
    RecyclerView currentCategories;
    AdapterCurrent adapterCurrent;
    ArrayList<DataTransactionDone> allData;

    TextView noItem;

    public CurrentFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_orders_current, container, false);

        helper = new DatabaseHelper(getActivity());
        currentCategories = v.findViewById(R.id.orderCurrentRecyclerView);
        noItem = v.findViewById(R.id.noItem);
        allData = helper.listDataTransactionDone();
        Collections.reverse(allData);
        currentCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        currentCategories.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0) {

            currentCategories.setVisibility(View.VISIBLE);
            noItem.setVisibility(View.GONE);
            adapterCurrent = new AdapterCurrent(allData, getActivity());
            currentCategories.setAdapter(adapterCurrent);

            Log.e("CURRENT DATA", String.valueOf(allData));

        }else {

            noItem.setVisibility(View.VISIBLE);

        }

        return v;

    }

}
