package com.example.fooddeliveryproject.Activities.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.AccountFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.InboxFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeScreenActivity extends AppCompatActivity {

   /* private List<DataTopSlide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;*/
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

       /* sliderPager = findViewById(R.id.sliderPager);
        indicator = findViewById(R.id.indicator);*/
        bottomNav = findViewById(R.id.nav_view);

        /*slideList = new ArrayList<>();
        slideList.add(new DataTopSlide(R.drawable.panang_curry));
        slideList.add(new DataTopSlide(R.drawable.butter_chicken));
        slideList.add(new DataTopSlide(R.drawable.maharashtra_thali));
        slideList.add(new DataTopSlide(R.drawable.cashback));
        slideList.add(new DataTopSlide(R.drawable.chow_mein));

        AdapterTopSliderPager adapter = new AdapterTopSliderPager(this, slideList);
        sliderPager.setAdapter(adapter);

        //important
        //for setting the padding to see the previous and next card view
        sliderPager.setPadding(50,0,50,0);

        //Setup time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeScreenActivity.SliderTimer(),0,4000);

        indicator.setupWithViewPager(sliderPager, true);*/

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        *//*TopFragment topFragment = new TopFragment();
        fragmentTransaction.add(R.id.topFragment, topFragment , topFragment.getTag());*//*

        BestCusineFragment bestCusineFragment = new BestCusineFragment();
        fragmentTransaction.add(R.id.bestCusineFragment, bestCusineFragment, bestCusineFragment.getTag());

        TodaySpecialsFragment todaySpecialsFragment = new TodaySpecialsFragment();
        fragmentTransaction.add(R.id.todaySpecialsFragment, todaySpecialsFragment, todaySpecialsFragment.getTag());

        YourFavouritesFragment yourFavouritesFragment = new YourFavouritesFragment();
        fragmentTransaction.add(R.id.yourFavouritesFragment, yourFavouritesFragment, yourFavouritesFragment.getTag());

        CustomFragment customFragment = new CustomFragment();
        fragmentTransaction.add(R.id.customFragment, customFragment, customFragment.getTag());

        fragmentTransaction.commit();*/

        //ViewPagerTest
        /*viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);*/


        /*ArrayList<ListTopClass> items = new ArrayList<>();
        TopAdapter adapter = new TopAdapter(this, items);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setAdapter(adapter);

        for (int i = 0; i<5; i++){


            if(i == 0){

                items.add(new ListTopClass(R.drawable.maharashtra_thali));

            }else if(i == 1){

                items.add(new ListTopClass(R.drawable.dal_tadkda));

            }else if(i == 2){

                items.add(new ListTopClass(R.drawable.butter_chicken));

            }else if(i == 3){

                items.add(new ListTopClass(R.drawable.goan_vegetarian_thali));

            }

            adapter.notifyDataSetChanged();

        }*/
        bottomNav.setSelectedItemId(R.id.navigation_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, new HomeFragment()).commit();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

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

                getSupportFragmentManager().beginTransaction().replace(R.id.layout_selected, selectedFragment).commit();

                return true;
            }
        });

    }

    /*class SliderTimer extends TimerTask{


        @Override
        public void run() {

            HomeScreenActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (sliderPager.getCurrentItem()<slideList.size()-1){

                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);

                    }else {

                        sliderPager.setCurrentItem(0);

                    }

                }
            });

        }
    }*/

    //ViewPagerTest
    /*private void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new BestCusineFragment(), null);

        adapter.addFragment(new TopFragment(), null);
        viewPager.setAdapter(adapter);


    }*/

    /*private void setupLinearLayout(View view){

        LinearLayout linearLayout = new Lin


    }*/

}
