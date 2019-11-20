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

    RecyclerView top_categories ;
    AdapterTopCategories adapterTopCategories;

    //String foods[] = {"Maha Thali", "Dal Tadkda", "Goan Special", "Butter Chicken"};
    int img[] = {R.drawable.maharashtra_thali, R.drawable.dal_tadkda, R.drawable.goan_vegetarian_thali, R.drawable.butter_chicken};

    public TopFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_top_categories, container, false);

        top_categories = v.findViewById(R.id.top_categories);

        top_categories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        top_categories.setLayoutManager(layoutManager);

        ArrayList<DataFood> dataFoods = getData();

        adapterTopCategories = new AdapterTopCategories(dataFoods, getActivity());
        top_categories.setAdapter(adapterTopCategories);

        return v;

    }


    private ArrayList<DataFood> getData(){

        ArrayList<DataFood> foodArrayList = new ArrayList<>();
        for (int i = 0; i < img.length; i++){

            DataFood dataFood = new DataFood();
            //dataFood.setFoodName(foods[i]);
            dataFood.setImg(img[i]);
            foodArrayList.add(dataFood);
        }

        return foodArrayList;
    }
}
