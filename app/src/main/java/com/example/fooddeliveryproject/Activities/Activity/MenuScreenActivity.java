package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuScreenActivity extends BaseActivity {

    ImageView backButton, cartButton;
    public static ImageView searchButton;
    RelativeLayout menu1, menuSearch;
    SearchView searchView;
    public FloatingActionButton numberCount;

    MenuScreenFragment menuScreenFragment = new MenuScreenFragment();

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.menuScreenFragment, menuScreenFragment).commit();

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

            }
        });


    }

}
