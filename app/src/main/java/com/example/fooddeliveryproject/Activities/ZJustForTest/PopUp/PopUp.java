package com.example.fooddeliveryproject.Activities.ZJustForTest.PopUp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;

import androidx.annotation.Nullable;

import com.example.fooddeliveryproject.R;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MY_PREFERENCE;

public class PopUp extends Activity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_floating_food_chart);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout((int)(width*.2), (int)(height*.2));
        /*sharedPreferences = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();*/


        // If i want just to show the 1 item total quantity i can use like the comment bellow
                /*bundle.putString("FoodCount", String.valueOf(quantity[0]));
                bundle.putString("FoodPrice", String.valueOf(priceTotal[0]));
                bundle.putString("FoodDiscount", String.valueOf(priceDiscountTotal[0]));*/

        //foodChartFragment.setArguments(bundle);

        /*currentCategories = v.findViewById(R.id.orderCurrentRecyclerView);
        noItem = v.findViewById(R.id.noItem);

        currentCategories.setHasFixedSize(true);

        date = new String[]{SaveSharedPreference.getDate(getContext(), "")};
        foodPrice = new int[]{SaveSharedPreference.getTotalPaymentSuccess(getContext(), 0)};

        dates = SaveSharedPreference.getDate(getContext(), "");

        Log.e("date", dates);
        Log.e("price", String.valueOf(SaveSharedPreference.getTotalPayment(getContext(), 0)));
        RecyclerView.LayoutManager layoutManagerBestCusine = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        currentCategories.setLayoutManager(layoutManagerBestCusine);

        if (SaveSharedPreference.getNoOrderComplete(getContext(), 0) > 0) {

            ArrayList<DataKhanaval> dataFoods = getData();

            adapterCurrent = new AdapterCurrent(dataFoods, getActivity());
            currentCategories.setAdapter(adapterCurrent);
            noItem.setVisibility(View.GONE);

        }

        return v;*/

        /*private ArrayList<DataKhanaval> getData() {

        ArrayList<DataKhanaval> foodArrayList = new ArrayList<>();
        for (int i = 0; i < foodName.length; i++){
            DataKhanaval dataFood = new DataKhanaval();
            dataFood.setDate(date[i]);
            dataFood.setFoodName(foodName[i]);
            dataFood.setImg(img[i]);
            dataFood.setOrderTracker(orderTracker[i]);
            dataFood.setFoodPrice(foodPrice[i]);
            foodArrayList.add(dataFood);
        }

        return foodArrayList;

    }*/
    }
}
