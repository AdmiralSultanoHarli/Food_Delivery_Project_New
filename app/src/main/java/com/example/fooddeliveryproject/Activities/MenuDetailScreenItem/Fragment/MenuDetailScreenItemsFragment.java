package com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Fragment;

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
import com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Adapter.AdapterMenuDetailScreenItems;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class MenuDetailScreenItemsFragment extends Fragment {

    RecyclerView menuDetailItem;
    AdapterMenuDetailScreenItems menuDetailItemAdapter;
    String foodName[] = {"Butter Roti", "Butter Chicken", "Rice", "Sate", "Gado - gado"};

    public MenuDetailScreenItemsFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_detail_screen_add_on, container, false);

        menuDetailItem = v.findViewById(R.id.menuDetailAddOnRecyclerView);

        menuDetailItem.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerAddOn =  new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        menuDetailItem.setLayoutManager(layoutManagerAddOn);

        ArrayList<DataKhanaval> dataItems = getData();

        menuDetailItemAdapter = new AdapterMenuDetailScreenItems(dataItems, getActivity());
        menuDetailItem.setAdapter(menuDetailItemAdapter);

        return v;

    }

    private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodItemsArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setFoodName(foodName[i]);
            foodItemsArrayList.add(dataKhanaval);

        }

        return foodItemsArrayList;
    }


}

