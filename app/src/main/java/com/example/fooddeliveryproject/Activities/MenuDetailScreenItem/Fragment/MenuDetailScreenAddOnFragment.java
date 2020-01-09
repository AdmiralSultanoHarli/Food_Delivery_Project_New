package com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Fragment;

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
import com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Adapter.AdapterMenuDetailScreenAddOn;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class MenuDetailScreenAddOnFragment extends Fragment {

    RecyclerView menuDetailAddOn;
    AdapterMenuDetailScreenAddOn menuDetailAddOnAdapter;
    String foodName[] = {"Rice", "Dal", "Roti", "Mango Shake", "Samosa", "Test", "Test", "Test", "Test"};
    int foodPrice[] = {10000, 10000, 10000, 20000, 25000, 0, 0, 0, 0 };
    int img[] = {R.drawable.basmati_rice, R.drawable.dal_tadkda, R.drawable.chapati, R.drawable.beverage, R.drawable.snacks, R.drawable.chow_mein, R.drawable.chow_mein, R.drawable.chow_mein, R.drawable.chow_mein};

    public MenuDetailScreenAddOnFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_detail_screen_add_on, container, false);

        menuDetailAddOn = v.findViewById(R.id.menuDetailAddOnRecyclerView);

        menuDetailAddOn.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerAddOn =  new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        menuDetailAddOn.setLayoutManager(layoutManagerAddOn);

        ArrayList<DataKhanaval> dataAddOn = getData();

        menuDetailAddOnAdapter = new AdapterMenuDetailScreenAddOn(dataAddOn, getActivity());
        menuDetailAddOn.setAdapter(menuDetailAddOnAdapter);

        return v;

    }

    private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodAddOnArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setImg(img[i]);
            dataKhanaval.setFoodName(foodName[i]);
            dataKhanaval.setFoodPrice(Integer.parseInt(String.valueOf(foodPrice[i])));
            foodAddOnArrayList.add(dataKhanaval);

        }

        return foodAddOnArrayList;
    }


}
