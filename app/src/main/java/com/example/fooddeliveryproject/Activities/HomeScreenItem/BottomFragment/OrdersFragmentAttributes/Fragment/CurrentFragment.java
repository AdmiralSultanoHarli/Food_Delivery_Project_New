package com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Adapter.AdapterCurrent;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class CurrentFragment extends Fragment{

    RecyclerView currentCategories;
    AdapterCurrent adapterCurrent;
    String[] date = {"23 Sept 2019", "24 Sept 2019", "25 Sept 2019", "26 Sept 2019"};
    String[] foodName = {"Butter Chicken", "Dal Tadkda", "Chow Mein", "Maharashtra Thali"};
    String[] orderTracker = {"Order Placed", "Order Placed", "Order Placed", "Order Placed"};
    int[] foodPrice = {49000, 50000, 30000, 20000};
    int[] img = {R.drawable.butter_chicken, R.drawable.dal_tadkda, R.drawable.chow_mein, R.drawable.maharashtra_thali};

    public CurrentFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_orders_current, container, false);

        currentCategories = v.findViewById(R.id.orderCurrentRecyclerView);

        currentCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        currentCategories.setLayoutManager(layoutManagerBestCusine);

        ArrayList<DataKhanaval> dataFoods = getData();

        adapterCurrent = new AdapterCurrent(dataFoods, getActivity());
        currentCategories.setAdapter(adapterCurrent);

        return v;

    }

    private ArrayList<DataKhanaval> getData() {

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){
            DataKhanaval dataFood = new DataKhanaval();
            dataFood.setDate(date[i]);
            dataFood.setFoodName(foodName[i]);
            dataFood.setImg(img[i]);
            dataFood.setOrderTracker(orderTracker[i]);
            dataFood.setFoodPrice(Integer.parseInt(String.valueOf(foodPrice[i])));
            foodArrayList.add(dataFood);
        }

        return foodArrayList;

    }
}
