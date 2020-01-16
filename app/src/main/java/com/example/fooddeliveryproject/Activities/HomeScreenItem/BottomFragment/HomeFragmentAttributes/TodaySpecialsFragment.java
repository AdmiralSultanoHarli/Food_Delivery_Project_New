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
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTodayCategories;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class TodaySpecialsFragment extends Fragment {

    RecyclerView todaySpecialsCategories;
    AdapterTodayCategories adapterTodayCategories;
    ArrayList<DataKhanaval> allData = new ArrayList<>();
    private DatabaseHelper helper;

    /*String foods[] = {"Goan Special", "Maha Thali", "Panang Curry", "Chapati"};
    int img[] = {R.drawable.goan_vegetarian_thali, R.drawable.maharashtra_thali, R.drawable.panang_curry, R.drawable.chapati};*/

    public TodaySpecialsFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_today_special, container, false);

        helper = new DatabaseHelper(getActivity());
        todaySpecialsCategories = v.findViewById(R.id.third_categories);
        allData = helper.listDataTodaySpecial();
        todaySpecialsCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerTodaySpecials = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        todaySpecialsCategories.setLayoutManager(layoutManagerTodaySpecials);

        if (allData.size() > 0){

            todaySpecialsCategories.setVisibility(View.VISIBLE);
            adapterTodayCategories = new AdapterTodayCategories(allData, getActivity());
            todaySpecialsCategories.setAdapter(adapterTodayCategories);

        }

        /*ArrayList <DataKhanaval> dataFoods = getData();
        adapterTodayCategories = new AdapterTodayCategories(dataFoods, getActivity());
        todaySpecialsCategories.setAdapter(adapterTodayCategories);*/

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
