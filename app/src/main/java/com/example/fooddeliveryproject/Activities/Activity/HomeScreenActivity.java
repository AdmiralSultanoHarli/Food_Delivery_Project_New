package com.example.fooddeliveryproject.Activities.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.AccountFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.InboxFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class HomeScreenActivity extends BaseActivity {

    BottomNavigationView bottomNav;
    boolean isOrderFragmentOpened = false;
    public static boolean isSearchFragmentOpened = false;
    public boolean isOrderDetailsFragmentOpened = false;

    boolean isSearchBarOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        bottomNav = findViewById(R.id.nav_view);

        bottomNav.setSelectedItemId(R.id.navigation_home);

        isOrderFragmentOpened = SaveSharedPreference.getFragmentOrderOpened(this, false);

        if (isOrderFragmentOpened == true){

            bottomNav.setSelectedItemId(R.id.navigation_orders);
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new OrdersFragment()).commit();
            SaveSharedPreference.setFragmentOrderOpened(this, false);

        }else {

            bottomNav.setSelectedItemId(R.id.navigation_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new HomeFragment()).commit();


        }

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                isOrderDetailsFragmentOpened = false;

                switch (menuItem.getItemId()){

                    case R.id.navigation_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.navigation_orders:
                        selectedFragment = new OrdersFragment();
                        break;
                    case R.id.navigation_account:
                        selectedFragment = new AccountFragment();
                        break;
                    case R.id.navigation_inbox:
                        selectedFragment = new InboxFragment();
                        break;

                }
                isSearchFragmentOpened = false;

                getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, selectedFragment).commit();

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (isOrderDetailsFragmentOpened == true) {

           if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {

                getSupportFragmentManager().popBackStack();
                isOrderDetailsFragmentOpened = false;
                isSearchFragmentOpened = false;

           }else {

               SaveSharedPreference.setLocationOpened(HomeScreenActivity.this, false);
               Log.e("done", "done");
               finishAffinity();

           }

        }else if(isOrderDetailsFragmentOpened == false){

            if (isSearchFragmentOpened == true){

                if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {

                    //getSupportFragmentManager().popBackStack();

                    bottomNav.setSelectedItemId(R.id.navigation_home);
                    //getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new HomeFragment()).commit();
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().attach(homeFragment).detach(homeFragment).commit();

                    isSearchFragmentOpened = false;

                }else {

                    finishAffinity();

                }

            }else {

                SaveSharedPreference.setLocationOpened(HomeScreenActivity.this, false);
                finishAffinity();
            }

        }else {

            Toast.makeText(this, "The Fuck", Toast.LENGTH_SHORT).show();
            Log.e("weird", "The Fuck");

        }

    }

}
