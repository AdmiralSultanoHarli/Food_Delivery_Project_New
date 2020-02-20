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
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterBestCusineCategories;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class BestCusineFragment extends Fragment {

    RecyclerView todaySpecialsCategories;
    AdapterBestCusineCategories adapterBestCusineCategories;
    ArrayList<DataKhanaval> allData = new ArrayList<>();
    private DatabaseHelper helper;

    public BestCusineFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_best_cusine, container, false);

        helper = new DatabaseHelper(getActivity());
        todaySpecialsCategories = v.findViewById(R.id.third_categories);
        allData = helper.listDataBestCusine();
        todaySpecialsCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerTodaySpecials = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        todaySpecialsCategories.setLayoutManager(layoutManagerTodaySpecials);

        if (allData.size() > 0){

            todaySpecialsCategories.setVisibility(View.VISIBLE);
            adapterBestCusineCategories = new AdapterBestCusineCategories(allData, getActivity());
            todaySpecialsCategories.setAdapter(adapterBestCusineCategories);

        }

        /*ArrayList <DataKhanaval> dataFoods = getData();
        adapterBestCusineCategories = new AdapterBestCusineCategories(dataFoods, getActivity());
        todaySpecialsCategories.setAdapter(adapterBestCusineCategories);*/

        return v;
    }

    /*private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();
        for(int i = 0; i<foods.length; i++){

            DataKhanaval dataFood = new DataKhanaval();
            dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);

        }

        return foodArrayList;
    }*/

}
