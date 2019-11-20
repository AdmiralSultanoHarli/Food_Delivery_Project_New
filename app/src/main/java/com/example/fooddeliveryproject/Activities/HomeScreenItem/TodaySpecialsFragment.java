package com.example.fooddeliveryproject.Activities.HomeScreenItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */

public class TodaySpecialsFragment extends Fragment {

    RecyclerView thirdCategories;
    AdapterTodayCategories adapterTodayCategories;

    String foods[] = {"Goan Special", "Maha Thali", "Panang Curry", "Chapati"};
    int img[] = {R.drawable.goan_vegetarian_thali, R.drawable.maharashtra_thali, R.drawable.panang_curry, R.drawable.chapati};

    public TodaySpecialsFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_today_special, container, false);

        thirdCategories = v.findViewById(R.id.third_categories);

        thirdCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        thirdCategories.setLayoutManager(layoutManager3);

        ArrayList <DataFood> dataFoods = getData();

        adapterTodayCategories = new AdapterTodayCategories(dataFoods, getActivity());
        thirdCategories.setAdapter(adapterTodayCategories);

        return v;
    }

    private ArrayList<DataFood> getData(){

        ArrayList<DataFood> foodArrayList = new ArrayList<>();
        for(int i = 0; i<foods.length; i++){

            DataFood dataFood = new DataFood();
            dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);

        }

        return foodArrayList;
    }

}
