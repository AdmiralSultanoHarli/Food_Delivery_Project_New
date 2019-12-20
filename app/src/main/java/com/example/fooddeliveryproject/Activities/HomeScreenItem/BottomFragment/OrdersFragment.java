package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Fragment.PastFragment;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Fragment.CurrentFragment;
import com.example.fooddeliveryproject.R;
import com.google.android.material.tabs.TabLayout;

public class OrdersFragment extends Fragment {

    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_orders, container, false);

        tabLayout = v.findViewById(R.id.historyTab);

        /*final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();*/
        getFragmentManager().beginTransaction().replace(R.id.selectedFragment, new CurrentFragment()).commit();
       /* tabLayout.addTab(tabLayout.newTab().setText("Current"));
        tabLayout.addTab(tabLayout.newTab().setText("Past"));*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment selectedFragment = null;

                if(tab.getPosition() == 0){

                    selectedFragment = new CurrentFragment();

                }else if(tab.getPosition() == 1){

                    selectedFragment = new PastFragment();

                }

                getFragmentManager().beginTransaction().replace(R.id.selectedFragment, selectedFragment).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {



            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {



            }


        });



        return v;

    }
}
