package com.example.fooddeliveryproject.Activities.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.BestCusineFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.CustomFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.TodaySpecialsFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.TopFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.ViewPagerAdapter;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.YourFavouritesFragment;
import com.example.fooddeliveryproject.Activities.ViewPager.Slide;
import com.example.fooddeliveryproject.Activities.ViewPager.SliderPagerAdapter;
import com.example.fooddeliveryproject.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeScreenActivity extends AppCompatActivity {

    private List<Slide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        sliderPager = findViewById(R.id.sliderPager);
        indicator = findViewById(R.id.indicator);

        slideList = new ArrayList<>();
        slideList.add(new Slide(R.drawable.panang_curry));
        slideList.add(new Slide(R.drawable.butter_chicken));
        slideList.add(new Slide(R.drawable.maharashtra_thali));
        slideList.add(new Slide(R.drawable.cashback));
        slideList.add(new Slide(R.drawable.chow_mein));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, slideList);
        sliderPager.setAdapter(adapter);

        //important
        //for setting the padding to see the previous and next card view
        sliderPager.setPadding(50,0,50,0);

        //Setup time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeScreenActivity.SliderTimer(),0,4000);

        indicator.setupWithViewPager(sliderPager, true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /*TopFragment topFragment = new TopFragment();
        fragmentTransaction.add(R.id.topFragment, topFragment , topFragment.getTag());*/

        BestCusineFragment bestCusineFragment = new BestCusineFragment();
        fragmentTransaction.add(R.id.bestCusineFragment, bestCusineFragment, bestCusineFragment.getTag());

        TodaySpecialsFragment todaySpecialsFragment = new TodaySpecialsFragment();
        fragmentTransaction.add(R.id.todaySpecialsFragment, todaySpecialsFragment, todaySpecialsFragment.getTag());

        YourFavouritesFragment yourFavouritesFragment = new YourFavouritesFragment();
        fragmentTransaction.add(R.id.yourFavouritesFragment, yourFavouritesFragment, yourFavouritesFragment.getTag());

        CustomFragment customFragment = new CustomFragment();
        fragmentTransaction.add(R.id.customFragment, customFragment, customFragment.getTag());

        fragmentTransaction.commit();

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

    }

    class SliderTimer extends TimerTask{


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
    }

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
