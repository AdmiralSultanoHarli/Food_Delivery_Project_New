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
        currentCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        currentCategories.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0) {

            currentCategories.setVisibility(View.VISIBLE);
            noItem.setVisibility(View.GONE);
            adapterCurrent = new AdapterCurrent(allData, getActivity());
            currentCategories.setAdapter(adapterCurrent);

        }else {

            noItem.setVisibility(View.VISIBLE);

        }

        return v;
        /*currentCategories = v.findViewById(R.id.orderCurrentRecyclerView);
        noItem = v.findViewById(R.id.noItem);

        currentCategories.setHasFixedSize(true);

        date = new String[]{SaveSharedPreference.getDate(getContext(), "")};
        foodPrice = new int[]{SaveSharedPreference.getTotalPaymentSuccess(getContext(), 0)};

        dates = SaveSharedPreference.getDate(getContext(), "");

        Log.e("date", dates);
        Log.e("price", String.valueOf(SaveSharedPreference.getTotalPayment(getContext(), 0)));
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        currentCategories.setLayoutManager(layoutManagerBestCusine);

        if (SaveSharedPreference.getNoOrderComplete(getContext(), 0) > 0) {

            ArrayList<DataKhanaval> dataFoods = getData();

            adapterCurrent = new AdapterCurrent(dataFoods, getActivity());
            currentCategories.setAdapter(adapterCurrent);
            noItem.setVisibility(View.GONE);

        }

        return v;*/

    }

    /*private ArrayList<DataKhanaval> getData() {

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){
            DataKhanaval dataFood = new DataKhanaval();
            dataFood.setDate(date[i]);
            dataFood.setFoodName(foodName[i]);
            dataFood.setImg(img[i]);
            dataFood.setOrderTracker(orderTracker[i]);
            dataFood.setFoodPrice(foodPrice[i]);
            foodArrayList.add(dataFood);
        }

        return foodArrayList;

    }*/
}
