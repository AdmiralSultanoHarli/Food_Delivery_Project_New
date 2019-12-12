package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryproject.Activities.Activity.MenuDetailsScreenActivity;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.ALL_QUANTITY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_CATEGORY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_DISCOUNT_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_PRICE_TOTAL;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MY_PREFERENCE;

public class FoodChartFragment extends Fragment {

    CardView buttonAddToChartThenOrder;
    TextView itemCount, price, discountPrice, itemName;
    String foodCategoryName, foodCountTotal, foodPriceTotal, foodDiscountTotal;
    SharedPreferences sharedPreferences;

    public FoodChartFragment() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_floating_food_chart,container, false);

//        Log.d(getArguments().getString("FoodName"), "There is");

        buttonAddToChartThenOrder = v.findViewById(R.id.buttonAddToChartThenOrder);
        itemCount = v.findViewById(R.id.itemCount);
        price = v.findViewById(R.id.price);
        discountPrice = v.findViewById(R.id.discountPrice);
        itemName = v.findViewById(R.id.itemName);
        sharedPreferences = getContext().getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);

        /*Bundle testBundle = this.getArguments()*//*, home = getActivity().getIntent().getExtras()*//*;
        //Log.e("bundlesss", String.valueOf(testBundle));
        //foodName = home.getString("FoodShop");
        foodCount = testBundle.getString("FoodCount");
        foodPrice = testBundle.getString("FoodPrice");
        foodDiscount = testBundle.getString("FoodDiscount");*/


        foodCategoryName = SaveSharedPreference.getFoodCategory(getContext(), "");
        foodCountTotal = String.valueOf(SaveSharedPreference.getAllQuantity(getContext(), 0));
        foodPriceTotal = String.valueOf(SaveSharedPreference.getFoodPriceTotal(getContext(), 0));
        foodDiscountTotal = String.valueOf(SaveSharedPreference.getFoodPriceDiscountTotal(getContext(), 0));

        //Log.e("FoodQuantity", String.valueOf(SaveSharedPreference.getQuantity(getContext(), 0)));

        itemName.setText(foodCategoryName);
        itemCount.setText(foodCountTotal + " Item");
        price.setText(foodPriceTotal);
        discountPrice.setText(foodDiscountTotal);

        discountPrice.setPaintFlags(discountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        buttonAddToChartThenOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(container.getContext(), "Test", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(container.getContext(), MenuDetailsScreenActivity.class);
                startActivity(i);

            }
        });

        return v;
    }


}
