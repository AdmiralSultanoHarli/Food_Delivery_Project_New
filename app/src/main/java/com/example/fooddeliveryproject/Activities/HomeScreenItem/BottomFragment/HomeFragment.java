package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment;

import android.media.TimedMetaData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTopSliderPager;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataTopSlide;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Fragment.BestCusineFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Fragment.CustomFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Fragment.TodaySpecialsFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Fragment.YourFavouritesFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.material.tabs.TabLayout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private List<DataTopSlide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sliderPager = container.findViewById(R.id.sliderPager);
        indicator = container.findViewById(R.id.indicator);

        slideList = new ArrayList<>();
        slideList.add(new DataTopSlide(R.drawable.panang_curry));
        slideList.add(new DataTopSlide(R.drawable.butter_chicken));
        slideList.add(new DataTopSlide(R.drawable.maharashtra_thali));
        slideList.add(new DataTopSlide(R.drawable.cashback));
        slideList.add(new DataTopSlide(R.drawable.chow_mein));

        AdapterTopSliderPager adapter = new AdapterTopSliderPager(container.getContext(), slideList);
        sliderPager.setAdapter(adapter);

        sliderPager.setPadding(50,0,50,0);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 0, 4000);

        indicator.setupWithViewPager(sliderPager, true);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BestCusineFragment bestCusineFragment = new BestCusineFragment();
        fragmentTransaction.add(R.id.bestCusineFragment, bestCusineFragment, bestCusineFragment.getTag());

        TodaySpecialsFragment todaySpecialsFragment = new TodaySpecialsFragment();
        fragmentTransaction.add(R.id.todaySpecialsFragment, todaySpecialsFragment, todaySpecialsFragment.getTag());

        YourFavouritesFragment yourFavouritesFragment = new YourFavouritesFragment();
        fragmentTransaction.add(R.id.yourFavouritesFragment, yourFavouritesFragment, yourFavouritesFragment.getTag());

        CustomFragment customFragment = new CustomFragment();
        fragmentTransaction.add(R.id.customFragment, customFragment, customFragment.getTag());

        fragmentTransaction.commit();

        return inflater.inflate(R.layout.fragment_home_home, container, false);
    }

    class SliderTimer extends TimerTask{

        @Override
        public void run() {

            if (sliderPager.getCurrentItem()<slideList.size()-1){

                sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);

            }else {

                sliderPager.setCurrentItem(0);

            }

        }
    }

}
