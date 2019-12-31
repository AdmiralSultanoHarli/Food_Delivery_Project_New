package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Data.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter.AdapterMenuScreen;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class MenuScreenFragment extends Fragment {

    //this is test
    RecyclerView menuScreenCategories;
    AdapterMenuScreen menuScreenAdapter;
    String[] foodName = {"Veg Thali", "Goan Special", "Butter Chicken", "Bhendi Masala", "Murg Musallam", "Basmati Rice Chicken Biryani", "Jeera Alo", "Mix Veggies"};
    String[] foodDescription = {"3 puri + 2 vegitable dish + rice + dal + sweet", "3 Goan special dish + rice + dal + sweet", "3 Butter Roti + Butter chicken + rice", "3 Butter Roti + Bhendi Masala + rice", "3 Butter Roti + rostated chicken + rice", "basmati rice chicken biryani", "3 Butter Roti + Jeera alo + rice", "3 Butter Roti + mix vegitables + rice"};
    /*String[] foodPrice = {"30000", "50000", "40000", "40000", "100000", "50000", "30000", "40000"};
    String[] foodPriceDiscount = {"40000", "65000", "55000", "50000", "115000", "55000", "40000", "45000"};*/
    int[] foodPrice = {30000, 50000, 40000, 40000, 100000, 50000, 30000, 40000};
    int[] foodPriceDiscount = {40000, 65000, 55000, 50000, 115000, 55000, 40000, 45000};
    int[] img = {R.drawable.maharashtra_thali, R.drawable.goan_vegetarian_thali, R.drawable.butter_chicken, R.drawable.bhindi_masala, R.drawable.murg_musallam, R.drawable.basmati_rice_chicken_biryani, R.drawable.jeera_alo, R.drawable.mix_veggies};
    int[] buttonPosition = {0, 1, 2, 3, 4, 5, 6, 7};


    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";
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

        ArrayList<DataKhanaval> dataKhanavals = getData();

        menuScreenAdapter = new AdapterMenuScreen(dataKhanavals, getActivity());
        menuScreenCategories.setAdapter(menuScreenAdapter);

        return v;

    }

    private ArrayList<DataKhanaval> getData(){

        ArrayList<DataKhanaval> foodMenuArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length ; i++){

            DataKhanaval dataKhanaval = new DataKhanaval();
            dataKhanaval.setImg(img[i]);
            dataKhanaval.setFoodName(foodName[i]);
            dataKhanaval.setFoodDescription(foodDescription[i]);
            dataKhanaval.setFoodPrice(Integer.parseInt(String.valueOf(foodPrice[i])));
            dataKhanaval.setFoodPriceDiscount(Integer.parseInt(String.valueOf(foodPriceDiscount[i])));
            dataKhanaval.setButtonPosition(Integer.parseInt(String.valueOf(buttonPosition[i])));
            foodMenuArrayList.add(dataKhanaval);
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable mListState = menuScreenCategories.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(KEY_RECYCLER_STATE, mListState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        Parcelable liststate = menuScreenCategories.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE,liststate);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBundleRecyclerViewState = new Bundle();
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            menuScreenCategories.getLayoutManager().onRestoreInstanceState(listState);
        }
    }
}
