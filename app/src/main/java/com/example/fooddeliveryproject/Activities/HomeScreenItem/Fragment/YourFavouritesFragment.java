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

import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterYourFavouritesCategories;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class YourFavouritesFragment extends Fragment {

    RecyclerView fourthCategories;
    AdapterYourFavouritesCategories adapterYourFavouritesCategories;

    String foods[] = {"Maha Thali", "Samosa", "Murg Mussalam"};
    int img[] = {R.drawable.maharashtra_thali, R.drawable.snacks, R.drawable.murg_musallam};

    public YourFavouritesFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_your_favourites, container, false);

        fourthCategories = v.findViewById(R.id.fourth_categories);

        fourthCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerYourFavourites = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fourthCategories.setLayoutManager(layoutManagerYourFavourites);

        ArrayList<DataFood> dataFoods = getData();

        adapterYourFavouritesCategories = new AdapterYourFavouritesCategories(dataFoods, getActivity());
        fourthCategories.setAdapter(adapterYourFavouritesCategories);

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
