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

/**
 * A simple {@link Fragment} subclass.
 */

public class BestCusineFragment extends Fragment{

    RecyclerView secondCategories;
    AdapterBestCusineCategories bestCusineCategories;
    String[] foods = {"Ayam", "Nasi", "Soto", "Nasi Padang"};
    int[] img = {R.drawable.butter_chicken, R.drawable.goan_vegetarian_thali, R.drawable.dal_tadkda, R.drawable.maharashtra_thali, R.drawable.butter_chicken};

    public BestCusineFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_food_categories, container, false);

        secondCategories = v.findViewById(R.id.second_categories);

        secondCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        secondCategories.setLayoutManager(layoutManager2);

        ArrayList<DataFood> dataFoods = getData();

        //return super.onCreateView(inflater, container, savedInstanceState);

        bestCusineCategories = new AdapterBestCusineCategories(dataFoods, getActivity());
        secondCategories.setAdapter(bestCusineCategories);

        return v;


    }

    private ArrayList<DataFood> getData() {

        ArrayList<DataFood> foodArrayList = new ArrayList<>();
        for (int i = 0; i < foods.length; i++){
            DataFood dataFood = new DataFood();
            dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);
        }

        return foodArrayList;

    }
}
