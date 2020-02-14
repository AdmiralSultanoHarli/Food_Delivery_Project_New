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
import com.example.fooddeliveryproject.Activities.Model.DataAlsoOrderThis;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Model.DataTransaction;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderAddOn;
import com.example.fooddeliveryproject.Activities.OrderScreenItem.Adapter.AdapterOrderScreenOrder;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class OrderAddOnFragment extends Fragment {

    RecyclerView orderAddOn;
    AdapterOrderAddOn orderAddOnAdapter;
    ArrayList<DataAlsoOrderThis> allData;
    private DatabaseHelper helper;

    /*String foodName[] = {"Nasi", "Kentang", "Roti", "Jus Mangga", "Gorengan"};
    int foodPrice[] = {10000, 10000, 10000, 20000, 25000};
    int img[] = {R.drawable.basmati_rice, R.drawable.kentang, R.drawable.chapati, R.drawable.beverage, R.drawable.snacks};*/

    public OrderAddOnFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_detail_screen_add_on, container, false);

        helper = new DatabaseHelper(getActivity());
        orderAddOn = v.findViewById(R.id.menuDetailAddOnRecyclerView);
        allData = helper.listDataNew();
        orderAddOn.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        orderAddOn.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0){

            orderAddOnAdapter = new AdapterOrderAddOn(allData, getActivity());
            orderAddOn.setAdapter(orderAddOnAdapter);

        }

        return v;

        /*orderAddOn = v.findViewById(R.id.menuDetailAddOnRecyclerView);

        orderAddOn.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerAddOn =  new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        orderAddOn.setLayoutManager(layoutManagerAddOn);

        ArrayList<DataKhanaval> dataAddOn = getData();

        orderAddOnAdapter = new AdapterOrderAddOn(dataAddOn, getActivity());
        orderAddOn.setAdapter(orderAddOnAdapter);

        return v;*/

    }

    /*private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodAddOnArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setImg(img[i]);
            dataKhanaval.setFoodName(foodName[i]);
            dataKhanaval.setFoodPrice(Integer.parseInt(String.valueOf(foodPrice[i])));
            foodAddOnArrayList.add(dataKhanaval);

        }

        return foodAddOnArrayList;
    }*/


}
