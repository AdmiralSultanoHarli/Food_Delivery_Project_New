package com.example.fooddeliveryproject.Activities.HomeScreenItem;

import android.os.Bundle;
import android.provider.ContactsContract;
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

public class CustomFragment extends Fragment {

    RecyclerView customCategories;
    AdapterCustomCategories customCategoriesAdapter;

    String foods[] = {"Beverages", "Snacks", "Sweets"};
    int img[] = {R.drawable.beverage, R.drawable.snacks, R.drawable.sweets};

    public CustomFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_custom_categories, container, false);

        customCategories = v.findViewById(R.id.custom_categories);

        customCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        customCategories.setLayoutManager(layoutManager5);

        ArrayList<DataFood> dataFoods = getData();

        customCategoriesAdapter = new AdapterCustomCategories(dataFoods, getActivity());
        customCategories.setAdapter(customCategoriesAdapter);

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
