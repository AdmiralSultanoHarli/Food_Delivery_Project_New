package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.gms.dynamic.IFragmentWrapper;

public class MenuScreenActivity extends BaseActivity {

    ImageView backButton, cartButton;
    public static ImageView searchButton;
    RelativeLayout menu1, menuSearch;
    SearchView searchView;
    //public FloatingActionButton numberCount;
    public static TextView numberCountText;
    ProgressBar progressBar;
    boolean progress = false;

    MenuScreenFragment menuScreenFragment = new MenuScreenFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        backButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);
        searchButton = findViewById(R.id.searchButton);
        menu1 = findViewById(R.id.menu1);
        menuSearch = findViewById(R.id.menuSearch);
        searchView = findViewById(R.id.searchView);
        numberCountText = findViewById(R.id.numberCountText);
        progressBar = findViewById(R.id.progressBar);

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

        if (SaveSharedPreference.getAllQuantity(this, 0) >= 1 ) {

            numberCountText.setVisibility(View.VISIBLE);
            numberCountText.setText(String.valueOf(SaveSharedPreference.getAllQuantity(this, 0)));

            cartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SaveSharedPreference.getAllQuantity(getApplicationContext(), 0) >= 1 ) {

                        Intent i = new Intent(MenuScreenActivity.this, OrderScreenActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }
                }
            });

        }else {

            cartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SaveSharedPreference.getAllQuantity(getApplicationContext(), 0) >= 1 ) {

                        Intent i = new Intent(MenuScreenActivity.this, OrderScreenActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }else {

                        Toast.makeText(getApplicationContext(), "There'is no item in the cart", Toast.LENGTH_SHORT).show();
                        Log.e("AllQuantity", String.valueOf(SaveSharedPreference.getAllQuantity(MenuScreenActivity.this, 0)));

                    }

                }
            });

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.INVISIBLE);

            }

        }, 2000);

    }


    @Override
    public void onBackPressed() {

        Intent i = new Intent(MenuScreenActivity.this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}
