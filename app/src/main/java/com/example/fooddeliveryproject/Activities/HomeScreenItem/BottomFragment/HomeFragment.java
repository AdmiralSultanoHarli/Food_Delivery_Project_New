package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.fooddeliveryproject.Activities.Data.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterTopSliderPager;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.BestCusineFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.CustomFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.TodaySpecialsFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.HomeFragmentAttributes.YourFavouritesFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    //private List<DataKhanaval> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private Context mContext;
    SearchView searchView;
    int[] img = {R.drawable.panang_curry, R.drawable.butter_chicken, R.drawable.maharashtra_thali, R.drawable.cashback, R.drawable.chow_mein};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView= inflater.inflate(R.layout.fragment_home_home, container, false);
        sliderPager = mView.findViewById(R.id.sliderPager);
        indicator = mView.findViewById(R.id.indicator);
        searchView = mView.findViewById(R.id.searchView);

       /* slideList = new ArrayList<>();
        slideList.add(new DataKhanaval(R.drawable.panang_curry, "PanangCurry", ));
        slideList.add(new DataFood(R.drawable.butter_chicken, "ButterChicken"));
        slideList.add(new DataFood(R.drawable.maharashtra_thali, "MaharashtraThali"));
        slideList.add(new DataFood(R.drawable.cashback, "Cashback"));
        slideList.add(new DataFood(R.drawable.chow_mein, "ChowMein"));*/

       searchView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               searchView.setIconified(false);

           }
       });

        ArrayList<DataKhanaval> dataKhanavals = getData();

        Log.d("context", String .valueOf(sliderPager));
        if(mContext!=null) {
            AdapterTopSliderPager adapter = new AdapterTopSliderPager(dataKhanavals, getActivity());
            sliderPager.setAdapter(adapter);
        }

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

        return mView;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext =  context;
    }

    private ArrayList<DataKhanaval> getData(){


        ArrayList<DataKhanaval> foodMenuArrayList = new ArrayList<>();
        for (int i = 0; i<img.length; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setImg(img[i]);
            foodMenuArrayList.add(dataKhanaval);

        }

        return foodMenuArrayList;

    }


    class SliderTimer extends TimerTask{

        @Override
        public void run() {

            if(getActivity() == null)
                return;


            getActivity().runOnUiThread(
                    new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<getData().size()-1){

                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);

                    }else {

                        sliderPager.setCurrentItem(0);

                    }
                }
            });



        }
    }

}
