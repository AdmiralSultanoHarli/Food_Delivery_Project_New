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
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterYourFavouritesCategories;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class YourFavouritesFragment extends Fragment {

    RecyclerView yourFavouritesCategories;
    AdapterYourFavouritesCategories adapterYourFavouritesCategories;
    ArrayList<DataKhanaval> allData = new ArrayList<>();
    private DatabaseHelper helper;

    /*String foods[] = {"Maha Thali", "Samosa", "Murg Mussalam"};
    int img[] = {R.drawable.maharashtra_thali, R.drawable.snacks, R.drawable.murg_musallam};*/

    public YourFavouritesFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_your_favourites, container, false);

        helper = new DatabaseHelper(getActivity());
        yourFavouritesCategories = v.findViewById(R.id.fourth_categories);
        allData = helper.listDataYourFavourites();
        yourFavouritesCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerYourFavourites = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        yourFavouritesCategories.setLayoutManager(layoutManagerYourFavourites);

        if (allData.size() > 0){

            yourFavouritesCategories.setVisibility(View.VISIBLE);
            adapterYourFavouritesCategories = new AdapterYourFavouritesCategories(allData, getActivity());
            yourFavouritesCategories.setAdapter(adapterYourFavouritesCategories);

        }

        /*ArrayList<DataKhanaval> dataFoods = getData();
        adapterYourFavouritesCategories = new AdapterYourFavouritesCategories(dataFoods, getActivity());
        yourFavouritesCategories.setAdapter(adapterYourFavouritesCategories);*/

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
