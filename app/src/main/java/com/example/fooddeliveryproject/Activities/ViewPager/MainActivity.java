package com.example.fooddeliveryproject.Activities.ViewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fooddeliveryproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<Slide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPager = findViewById(R.id.sliderPager);
        indicator = findViewById(R.id.indicator);


        // Prepare a list of slides

        slideList = new ArrayList<>();
        slideList.add(new Slide(R.drawable.beverage));
        slideList.add(new Slide(R.drawable.butter_chicken));
        slideList.add(new Slide(R.drawable.chapati));
        slideList.add(new Slide(R.drawable.cashback));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, slideList);
        sliderPager.setAdapter(adapter);

        //Setup Time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 0, 4000);

        indicator.setupWithViewPager(sliderPager, true);

    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(sliderPager.getCurrentItem()<slideList.size()-1){

                        sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);

                    }else{

                        sliderPager.setCurrentItem(0);

                    }

                }
            });

        }

    }

}
