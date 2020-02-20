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
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterRestaurantsCategories;
import com.example.fooddeliveryproject.R;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class RestaurantsFragment extends Fragment{

    RecyclerView restaurantsCategories;
    AdapterRestaurantsCategories adapterRestaurantsCategories;
    ArrayList<DataKhanaval> allData = new ArrayList<>();
    private DatabaseHelper helper;

    public RestaurantsFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_restaurants, container, false);

        helper = new DatabaseHelper(getActivity());
        restaurantsCategories = v.findViewById(R.id.second_categories);
        allData = helper.listDataRestaurant();
        restaurantsCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        restaurantsCategories.setLayoutManager(layoutManagerBestCusine);

        if (allData.size() > 0){

            restaurantsCategories.setVisibility(View.VISIBLE);
            adapterRestaurantsCategories = new AdapterRestaurantsCategories(allData, getActivity());
            restaurantsCategories.setAdapter(adapterRestaurantsCategories);

        }

        /*ArrayList<DataKhanaval> dataFoods = getData();

        adapterRestaurantsCategories = new AdapterRestaurantsCategories(dataFoods, getActivity());
        restaurantsCategories.setAdapter(adapterRestaurantsCategories);*/


        return v;

    }

    /*private ArrayList<DataKhanaval> getData() {

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();
        for (int i = 0; i < foods.length; i++){
            DataKhanaval dataFood = new DataKhanaval();
            dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);
        }

        return foodArrayList;

    }*/
}
