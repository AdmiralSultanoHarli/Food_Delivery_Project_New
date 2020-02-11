package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.HomeScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterCustomCategories;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterSearchView;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragment;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class SearchViewFragment extends Fragment {

    RecyclerView customCategories;
    AdapterSearchView adapterCustomCategories;
    ArrayList<DataKhanaval> allData = new ArrayList<>();
    private DatabaseHelper helper;
    public static SearchView searchView;

    //String foods[] = {"Beverages", "Snacks", "Sweets"};
    int img[] = {R.drawable.beverage, R.drawable.snacks, R.drawable.sweets};

    public SearchViewFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search_categories, container, false);

        searchView = v.findViewById(R.id.searchView);
        helper = new DatabaseHelper(getActivity());
        customCategories = v.findViewById(R.id.search_categories);
        allData = helper.listDataRestaurantMax();
        customCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerCustom = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        customCategories.setLayoutManager(layoutManagerCustom);

        helper = new DatabaseHelper(getContext());
        allData = helper.listDataRestaurantMax();

        Log.d("db data",allData.toString());


        searchView.setIconified(false);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                /*HomeFragment homeFragment = new HomeFragment();
                homeFragment.searchView.setVisibility(View.VISIBLE);
                homeFragment.searchFragment.setVisibility(View.GONE);
                homeFragment.scrollView.setVisibility(View.VISIBLE);
                homeFragment.searchView.setIconified(true);*/

                HomeScreenActivity homeScreenActivity = (HomeScreenActivity) getActivity();
                homeScreenActivity.isSearchFragmentOpened = false;
                homeScreenActivity.getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new HomeFragment()).commit();

                return true;

            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        customCategories.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Adapter", String.valueOf(adapterCustomCategories));

                if (adapterCustomCategories != null){

                    if (!newText.isEmpty()){

                        adapterCustomCategories.getFilter().filter(newText);
                        customCategories.setVisibility(View.VISIBLE);

                    }else if (newText.isEmpty()){

                        customCategories.setVisibility(View.GONE);

                    }

                }

                return true;

            }
        });

        if (allData.size() > 0){

            adapterCustomCategories = new AdapterSearchView(allData, getActivity());
            customCategories.setAdapter(adapterCustomCategories);

        }

        /*ArrayList<Data> dataFoods = getData();

        adapterCustomCategories = new AdapterCustomCategories(dataFoods, getActivity());
        customCategories.setAdapter(adapterCustomCategories);*/

        return v;
    }

    /*private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();

        for(int i = 0; i<img.length; i++){

            DataKhanaval dataFood = new DataKhanaval();
            //dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);

        }

        return foodArrayList;

    }*/
}