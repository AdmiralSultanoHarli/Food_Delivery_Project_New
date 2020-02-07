package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FoodChartFragment extends Fragment {

    CardView buttonAddToChartThenOrder;
    TextView itemCount, price, discountPrice, itemName;
    String foodCategoryName, foodCountTotal, foodPriceTotal, foodDiscountTotal;

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

        DecimalHelper decimalHelper = new DecimalHelper();

        buttonAddToChartThenOrder = v.findViewById(R.id.buttonAddToChartThenOrder);
        itemCount = v.findViewById(R.id.itemCount);
        price = v.findViewById(R.id.price);
        discountPrice = v.findViewById(R.id.discountPrice);
        itemName = v.findViewById(R.id.itemName);

        //sharedPreferences = getContext().getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);

        /*Bundle testBundle = this.getArguments()*//*, home = getActivity().getIntent().getExtras()*//*;
        //Log.e("bundlesss", String.valueOf(testBundle));
        //foodName = home.getString("FoodShop");
        foodCount = testBundle.getString("FoodCount");
        foodPrice = testBundle.getString("FoodPrice");
        foodDiscount = testBundle.getString("FoodDiscount");*/

        foodCategoryName = SaveSharedPreference.getFoodCategory(getContext(), "");
        foodCountTotal = String.valueOf(SaveSharedPreference.getAllQuantity(getContext(), 0));
        foodPriceTotal = decimalHelper.formatter(SaveSharedPreference.getFoodPriceTotal(getContext(), 0));
        foodDiscountTotal = decimalHelper.formatter(SaveSharedPreference.getFoodPriceDiscountTotal(getContext(), 0));

        //Log.e("FoodQuantity", String.valueOf(SaveSharedPreference.getQuantity(getContext(), 0)));

        itemName.setText(foodCategoryName);
        itemCount.setText(foodCountTotal + " Item");
        price.setText(foodPriceTotal);
        discountPrice.setText(foodDiscountTotal);

        discountPrice.setPaintFlags(discountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        buttonAddToChartThenOrder.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(container.getContext(), "Test", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(container.getContext(), OrderScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        return v;
    }


}
