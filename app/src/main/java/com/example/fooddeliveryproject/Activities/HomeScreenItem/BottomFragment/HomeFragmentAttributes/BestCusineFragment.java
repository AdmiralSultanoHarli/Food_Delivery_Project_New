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
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterCustomCategories;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterBestCusineCategories;
import com.example.fooddeliveryproject.Activities.Model.DataTest;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class BestCusineFragment extends Fragment{

    RecyclerView bestCusineCategories;
    AdapterBestCusineCategories adapterBestCusineCategories;
    ArrayList<DataTest> allData = new ArrayList<>();
    private DatabaseHelper helper;

    public BestCusineFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_best_cusine, container, false);

        helper = new DatabaseHelper(getActivity());
        bestCusineCategories = v.findViewById(R.id.second_categories);
        allData = helper.listDataBestCusine();
        bestCusineCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        bestCusineCategories.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0){

            bestCusineCategories.setVisibility(View.VISIBLE);
            adapterBestCusineCategories = new AdapterBestCusineCategories(allData, getActivity());
            bestCusineCategories.setAdapter(adapterBestCusineCategories);

        }

        /*ArrayList<DataKhanaval> dataFoods = getData();

        adapterBestCusineCategories = new AdapterBestCusineCategories(dataFoods, getActivity());
        bestCusineCategories.setAdapter(adapterBestCusineCategories);*/


        return v;

    }

    /*private ArrayList<DataKhanaval> getData() {

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();
        for (int i = 0; i < foods.length; i++){
            DataKhanaval dataFood = new DataKhanaval();
            dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);
        }

        return foodArrayList;

    }*/
}
