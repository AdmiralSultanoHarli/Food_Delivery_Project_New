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

import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderScreenOrder;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class OrderScreenOrderFragment extends Fragment {

    RecyclerView orderScreenOrder;
    AdapterOrderScreenOrder orderScreenOrderAdapter;
    String foodName[] = {"Nasi Padang"};
    /*int foodPrice[] = {40000, 50000, 60000};
    int foodPriceDiscount[] = {55000, 65000, 75000};*/
    int img[] = {R.drawable.nasi_padang_s};

    public OrderScreenOrderFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_order_screen_order_summary, container, false);

        orderScreenOrder = v.findViewById(R.id.orderScreenOrderRecyclerView);

        orderScreenOrder.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerAddOn =  new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        orderScreenOrder.setLayoutManager(layoutManagerAddOn);

        ArrayList<DataKhanaval> dataAddOn = getData();

        orderScreenOrderAdapter = new AdapterOrderScreenOrder(dataAddOn, getActivity());
        orderScreenOrder.setAdapter(orderScreenOrderAdapter);

        return v;

    }

    private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodAddOnArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setImg(img[i]);
            dataKhanaval.setFoodName(foodName[i]);
            /*dataKhanaval.setFoodPrice(Integer.parseInt(String.valueOf(foodPrice[i])));
            dataKhanaval.setFoodPriceDiscount(Integer.parseInt(String.valueOf(foodPriceDiscount[i])));*/
            foodAddOnArrayList.add(dataKhanaval);

        }

        return foodAddOnArrayList;
    }


}