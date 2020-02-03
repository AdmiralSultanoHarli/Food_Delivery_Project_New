package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter.AdapterMenuScreen;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MenuScreenActivity extends BaseActivity {

    ImageView backButton, cartButton;
    public static ImageView searchButton;
    RelativeLayout menu1, menuSearch;
    SearchView searchView;
    public FloatingActionButton numberCount;

    private DatabaseHelper mDatabase;
    private ArrayList<DataKhanaval> allData = new ArrayList<>();
    private AdapterMenuScreen mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        backButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);
        numberCount = findViewById(R.id.numberCount);
        searchButton = findViewById(R.id.searchButton);
        menu1 = findViewById(R.id.menu1);
        menuSearch = findViewById(R.id.menuSearch);
        searchView = findViewById(R.id.searchView);

        mDatabase = new DatabaseHelper(this);
        allData = mDatabase.listDataMenuScreen();
        mAdapter = new AdapterMenuScreen(allData, this);



        //numberCount.se

        /*searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                menu1.setVisibility(View.VISIBLE);
                menuSearch.setVisibility(View.GONE);
                return true;
            }
        });*/

        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                *//*DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                databaseHelper.findDataFood(newText);*//*
                Log.e("Adapter", String.valueOf(mAdapter));

                if (mAdapter != null){

                    mAdapter.getFilter().filter(newText);
                    Log.e("What", newText);

                }

                return true;
            }
        });*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final MenuScreenFragment menuScreenFragment = new MenuScreenFragment();
        fragmentTransaction.add(R.id.menuScreenFragment, menuScreenFragment, menuScreenFragment.getTag());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MenuScreenFragment menuScreenFragment_s = new MenuScreenFragment();
                menuScreenFragment_s.searchView.setVisibility(View.VISIBLE);
                menuScreenFragment_s.searchView.setIconified(false);

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                /*Intent backHomeScreenActivity = new Intent(MenuScreenActivity.this, HomeScreenActivity.class);
                startActivity(backHomeScreenActivity);*/


            }
        });




       /* FoodChartFragment foodChartFragment = new FoodChartFragment();
        fragmentTransaction.add(R.id.foodChartFragment, foodChartFragment, foodChartFragment.getTag());*/

        fragmentTransaction.commit();

    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/
}
