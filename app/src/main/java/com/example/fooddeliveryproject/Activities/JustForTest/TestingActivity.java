package com.example.fooddeliveryproject.Activities.JustForTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTopSliderPager;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataTopSlide;
import com.example.fooddeliveryproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestingActivity extends AppCompatActivity {

    private List<DataTopSlide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        sliderPager = findViewById(R.id.sliderPager);
        indicator = findViewById(R.id.indicator);


        // Prepare a list of slides

        slideList = new ArrayList<>();
        slideList.add(new DataTopSlide(R.drawable.beverage));
        slideList.add(new DataTopSlide(R.drawable.butter_chicken));
        slideList.add(new DataTopSlide(R.drawable.chapati));
        slideList.add(new DataTopSlide(R.drawable.cashback));

        AdapterTopSliderPager adapter = new AdapterTopSliderPager(this, slideList);
        sliderPager.setAdapter(adapter);

        //Setup Time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TestingActivity.SliderTimer(), 0, 4000);

        indicator.setupWithViewPager(sliderPager, true);

    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {

            TestingActivity.this.runOnUiThread(new Runnable() {
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
