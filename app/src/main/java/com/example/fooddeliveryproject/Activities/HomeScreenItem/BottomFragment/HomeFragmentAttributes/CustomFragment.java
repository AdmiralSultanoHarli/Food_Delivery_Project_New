package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes;

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
import com.example.fooddeliveryproject.Activities.Model.Data;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterCustomCategories;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class CustomFragment extends Fragment {

    RecyclerView customCategories;
    AdapterCustomCategories adapterCustomCategories;
    ArrayList<Data> allData = new ArrayList<>();
    private DatabaseHelper helper;

    //String foods[] = {"Beverages", "Snacks", "Sweets"};
    int img[] = {R.drawable.beverage, R.drawable.snacks, R.drawable.sweets};

    public CustomFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_custom_categories, container, false);

        helper = new DatabaseHelper(getActivity());
        customCategories = v.findViewById(R.id.custom_categories);
        allData = helper.listData();
        customCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerCustom = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        customCategories.setLayoutManager(layoutManagerCustom);

        if (allData.size() > 0){

            customCategories.setVisibility(View.VISIBLE);
            adapterCustomCategories = new AdapterCustomCategories(allData, getActivity());
            customCategories.setAdapter(adapterCustomCategories);

        }

        ArrayList<Data> dataFoods = getData();

        adapterCustomCategories = new AdapterCustomCategories(dataFoods, getActivity());
        customCategories.setAdapter(adapterCustomCategories);

        return v;
    }

    private ArrayList<Data> getData(){

        ArrayList<Data> foodArrayList = new ArrayList<>();

        for(int i = 0; i<img.length; i++){

            Data dataFood = new Data();
            //dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);

        }

        return foodArrayList;

    }
}
