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

/**
 * A simple {@link Fragment} subclass.
 */

public class TopFragment extends Fragment {

    RecyclerView top_categories ,second_categories, third_categories, fourth_categories, fifth_categories;
    AdapterTopCategories mAdapter;
    AdapterBestCusineCategories mAdapter2;
    AdapterTodayCategories mAdapter3;
    AdapterFavouriteCategories mAdapter4;
    AdapterTestCategories mAdapter5;
    String foods[] = {"Maha Thali", "Dal Tadkda", "Goan Special", "Butter Chicken"};
    int img[] = {R.drawable.maharashtra_thali, R.drawable.dal_tadkda, R.drawable.goan_vegetarian_thali, R.drawable.butter_chicken};

    public TopFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_food_categories, container, false);

        top_categories = v.findViewById(R.id.top_categories);
        second_categories = v.findViewById(R.id.second_categories);
        third_categories = v.findViewById(R.id.third_categories);
        fourth_categories = v.findViewById(R.id.fourth_categories);
        fifth_categories = v.findViewById(R.id.fifth_categories);

        top_categories.setHasFixedSize(true);
        second_categories.setHasFixedSize(true);
        third_categories.setHasFixedSize(true);
        fourth_categories.setHasFixedSize(true);
        fifth_categories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        top_categories.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        second_categories.setLayoutManager(layoutManager2);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        third_categories.setLayoutManager(layoutManager3);

        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fourth_categories.setLayoutManager(layoutManager4);

        RecyclerView.LayoutManager layoutManager5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fifth_categories.setLayoutManager(layoutManager5);

        ArrayList<DataFood> dataFoods = getData();

        mAdapter = new AdapterTopCategories(dataFoods, getActivity());
        top_categories.setAdapter(mAdapter);

        mAdapter2 = new AdapterBestCusineCategories(dataFoods, getActivity());
        second_categories.setAdapter(mAdapter2);

        mAdapter3 = new AdapterTodayCategories(dataFoods, getActivity());
        third_categories.setAdapter(mAdapter3);

        mAdapter4 = new AdapterFavouriteCategories(dataFoods, getActivity());
        fourth_categories.setAdapter(mAdapter4);

        mAdapter5 = new AdapterTestCategories(dataFoods, getActivity());
        fifth_categories.setAdapter(mAdapter5);

        return v;

    }


    private ArrayList<DataFood> getData(){

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
