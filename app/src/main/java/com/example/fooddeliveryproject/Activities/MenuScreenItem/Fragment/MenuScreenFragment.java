package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

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

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter.AdapterMenuScreen;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class MenuScreenFragment extends Fragment {

    public RecyclerView menuScreenCategories;
    public static SearchView searchView;
    AdapterMenuScreen menuScreenAdapter;
    ArrayList<DataKhanaval> allData = new ArrayList<>();
    private DatabaseHelper helper;

    public MenuScreenFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_screen, container, false);

        searchView = v.findViewById(R.id.searchView);

        helper = new DatabaseHelper(getActivity());
        menuScreenCategories = v.findViewById(R.id.menuScreenRecyclerView);
        allData = helper.listDataMenuScreen();
        menuScreenCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerMenuScreen = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        menuScreenCategories.setLayoutManager(layoutManagerMenuScreen);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                menuScreenAdapter.getFilter().filter(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (menuScreenAdapter != null){

                    menuScreenAdapter.getFilter().filter(newText);

                }

                return false;
            }
        });



        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                searchView.setVisibility(View.GONE);
                return true;
            }
        });


        if (allData.size() > 0){

            menuScreenCategories.setVisibility(View.VISIBLE);
            menuScreenAdapter = new AdapterMenuScreen(allData, getActivity());
            menuScreenCategories.setAdapter(menuScreenAdapter);
            Log.e("DATA", String.valueOf(allData));

        }

        return v;

    }

}
