package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter.AdapterMenuScreen;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.DataFoodMenu;
import com.example.fooddeliveryproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class MenuScreenFragment extends Fragment {

    RecyclerView menuScreenCategories;
    AdapterMenuScreen menuScreenAdapter;
    String[] foodName = {"Veg Thali", "Goan Special", "Butter Chicken", "Bhendi Masala", "Murg Musallam", "Basmati Rice Chicken Biryani", "Jeera Alo", "Mix Veggies"};
    String[] foodDescription = {"3 puri + 2 vegitable dish + rice + dal + sweet", "3 Goan special dish + rice + dal + sweet", "3 Butter Roti + Butter chicken + rice", "3 Butter Roti + Bhendi Masala + rice", "3 Butter Roti + rostated chicken + rice", "basmati rice chicken biryani", "3 Butter Roti + Jeera alo + rice", "3 Butter Roti + mix vegitables + rice"};
    /*String[] foodPrice = {"30000", "50000", "40000", "40000", "100000", "50000", "30000", "40000"};
    String[] foodPriceDiscount = {"40000", "65000", "55000", "50000", "115000", "55000", "40000", "45000"};*/
    int[] foodPrice = {30000, 50000, 40000, 40000, 100000, 50000, 30000, 40000};
    int[] foodPriceDiscount = {40000, 65000, 55000, 50000, 115000, 55000, 40000, 45000};
    int[] img = {R.drawable.maharashtra_thali, R.drawable.goan_vegetarian_thali, R.drawable.butter_chicken, R.drawable.bhindi_masala, R.drawable.murg_musallam, R.drawable.basmati_rice_chicken_biryani, R.drawable.jeera_alo, R.drawable.mix_veggies};

    public MenuScreenFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_screen, container, false);

        menuScreenCategories = v.findViewById(R.id.menuScreenRecyclerView);

        menuScreenCategories.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerMenuScreen = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        menuScreenCategories.setLayoutManager(layoutManagerMenuScreen);

        ArrayList<DataFoodMenu> dataFoodMenus = getData();

        menuScreenAdapter = new AdapterMenuScreen(dataFoodMenus, getActivity()/*, communication*/);
        menuScreenCategories.setAdapter(menuScreenAdapter);

        return v;

    }

    private ArrayList<DataFoodMenu> getData(){

        ArrayList<DataFoodMenu> foodMenuArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length ; i++){

            DataFoodMenu dataFoodMenu = new DataFoodMenu();
            dataFoodMenu.setImg(img[i]);
            dataFoodMenu.setFoodName(foodName[i]);
            dataFoodMenu.setFoodDescription(foodDescription[i]);
            dataFoodMenu.setFoodPrice(Integer.parseInt(String.valueOf(foodPrice[i])));
            dataFoodMenu.setFoodPriceDiscount(Integer.parseInt(String.valueOf(foodPriceDiscount[i])));
            foodMenuArrayList.add(dataFoodMenu);
        }

        return foodMenuArrayList;
    }

    /*AdapterMenuScreen.FragmentCommunication communication = new AdapterMenuScreen.FragmentCommunication() {
        @Override
        public void respond(int i, String foodName) {

            FoodChartFragment foodChartFragment = new FoodChartFragment();
            Bundle bundle = new Bundle();
            bundle.putString("FoodName", foodName);
            foodChartFragment.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.foodChartFragment, foodChartFragment).commit();

        }
    };*/

}
