package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.R;

public class MenuScreenActivity extends AppCompatActivity {

    ImageButton backButton, cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        backButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                /*Intent backHomeScreenActivity = new Intent(MenuScreenActivity.this, HomeScreenActivity.class);
                startActivity(backHomeScreenActivity);*/

            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuScreenFragment menuScreenFragment = new MenuScreenFragment();
        fragmentTransaction.add(R.id.menuScreenFragment, menuScreenFragment, menuScreenFragment.getTag());

       /* FoodChartFragment foodChartFragment = new FoodChartFragment();
        fragmentTransaction.add(R.id.foodChartFragment, foodChartFragment, foodChartFragment.getTag());*/

        fragmentTransaction.commit();

    }

}
