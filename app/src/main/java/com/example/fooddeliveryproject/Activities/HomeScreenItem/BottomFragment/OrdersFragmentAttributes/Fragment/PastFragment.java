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

import com.example.fooddeliveryproject.Activities.Data.DataKhanaval;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Adapter.AdapterCurrent;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.BottomFragment.OrdersFragmentAttributes.Adapter.AdapterPast;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class PastFragment extends Fragment{

    RecyclerView pastCategories;
    AdapterPast adapterPast;
    String[] date = {"23 March 2019", "24 March 2019", "25 March 2019", "26 March 2019"};
    String[] foodName = {"Goan Special", "Butter Chicken", "Maha Thali", "Chow Mein"};
    String[] orderTracker = {"Order Delivered", "Order Delivered", "Order Delivered", "Order Delivered"};
    int[] foodPrice = {49000, 50000, 30000, 20000};
    int[] img = {R.drawable.goan_vegetarian_thali, R.drawable.butter_chicken, R.drawable.maharashtra_thali, R.drawable.chow_mein};

    public PastFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_orders_current, container, false);

        pastCategories = v.findViewById(R.id.orderCurrentRecyclerView);

        pastCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        pastCategories.setLayoutManager(layoutManagerBestCusine);

        ArrayList<DataKhanaval> dataFoods = getData();

        adapterPast = new AdapterPast(dataFoods, getActivity());
        pastCategories.setAdapter(adapterPast);

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
