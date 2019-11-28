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

import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTodayCategories;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class TodaySpecialsFragment extends Fragment {

    RecyclerView todaySpecialsCategories;
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

        todaySpecialsCategories = v.findViewById(R.id.third_categories);

        todaySpecialsCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerTodaySpecials = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        todaySpecialsCategories.setLayoutManager(layoutManagerTodaySpecials);

        ArrayList <DataFood> dataFoods = getData();

        adapterTodayCategories = new AdapterTodayCategories(dataFoods, getActivity());
        todaySpecialsCategories.setAdapter(adapterTodayCategories);

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
