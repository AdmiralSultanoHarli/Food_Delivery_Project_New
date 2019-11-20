package com.example.fooddeliveryproject.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.BestCusineFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.TopFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.ViewPagerAdapter;
import com.example.fooddeliveryproject.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class HomeScreenActivity extends AppCompatActivity {

    //ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        TopFragment fragment = new TopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.foodCategoriesFragment, fragment);
        
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
