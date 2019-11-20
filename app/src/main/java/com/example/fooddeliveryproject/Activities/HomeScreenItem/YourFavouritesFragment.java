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

public class YourFavouritesFragment extends Fragment {

    RecyclerView fourthCategories;
    AdapterFavouriteCategories adapterFavouriteCategories;

    String foods[] = {"Gado - Gado", "Ketoprak", "Ayam Goreng", "Nasi Padang"};
    int img[] = {R.drawable.gado_gado, R.drawable.ketoprak, R.drawable.ayam_goreng, R.drawable.nasi_padang};

    public YourFavouritesFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_your_favourites, container, false);

        fourthCategories = v.findViewById(R.id.fourth_categories);

        fourthCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fourthCategories.setLayoutManager(layoutManager4);

        ArrayList<DataFood> dataFoods = getData();

        adapterFavouriteCategories = new AdapterFavouriteCategories(dataFoods, getActivity());
        fourthCategories.setAdapter(adapterFavouriteCategories);

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
