package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterYourFavouritesCategories;
import com.example.fooddeliveryproject.Activities.Model.DataYourFavourites;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.Collections;

public class YourFavouritesFragment extends Fragment {

    RecyclerView yourFavouritesCategories;
    AdapterYourFavouritesCategories adapterYourFavouritesCategories;
    ArrayList<DataYourFavourites> allData = new ArrayList<>();
    private DatabaseHelper helper;
    View line;
    TextView yourFavourites;

    public YourFavouritesFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_your_favourites, container, false);

        helper = new DatabaseHelper(getActivity());
        yourFavouritesCategories = v.findViewById(R.id.fourth_categories);
        line = v.findViewById(R.id.line);
        yourFavourites = v.findViewById(R.id.yourFavourites);
        allData = helper.listDataYourFavourites();
        yourFavouritesCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerYourFavourites = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        yourFavouritesCategories.setLayoutManager(layoutManagerYourFavourites);

        if (allData.size() > 0){

            yourFavouritesCategories.setVisibility(View.VISIBLE);
            adapterYourFavouritesCategories = new AdapterYourFavouritesCategories(allData, getActivity());
            yourFavouritesCategories.setAdapter(adapterYourFavouritesCategories);
            line.setVisibility(View.VISIBLE);
            yourFavourites.setVisibility(View.VISIBLE);

        }else {

            line.setVisibility(View.GONE);
            yourFavourites.setVisibility(View.GONE);

        }

        return v;
    }

}
