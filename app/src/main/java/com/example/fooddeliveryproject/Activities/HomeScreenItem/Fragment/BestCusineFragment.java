package com.example.fooddeliveryproject.Activities.HomeScreenItem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterBestCusineCategories;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class BestCusineFragment extends Fragment{

    RecyclerView secondCategories;
    AdapterBestCusineCategories bestCusineAdapter;
    String[] foods = {"Thai Special", "Indian", "Chinese", "Maha Thali"};
    int[] img = {R.drawable.panang_curry, R.drawable.dal_tadkda, R.drawable.chow_mein, R.drawable.maharashtra_thali};

    public BestCusineFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_best_cusine, container, false);

        secondCategories = v.findViewById(R.id.second_categories);

        secondCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        secondCategories.setLayoutManager(layoutManagerBestCusine);

        ArrayList<DataFood> dataFoods = getData();

        bestCusineAdapter = new AdapterBestCusineCategories(dataFoods, getActivity());
        secondCategories.setAdapter(bestCusineAdapter);

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
