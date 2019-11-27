package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryproject.Activities.Activity.MenuDetailsScreenActivity;
import com.example.fooddeliveryproject.R;

public class FoodChartFragment extends Fragment {

    CardView buttonAddToChartThenOrder;
    TextView itemCount, price, discountPrice, itemName;

    public FoodChartFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_floating_food_chart,container, false);

        buttonAddToChartThenOrder = v.findViewById(R.id.buttonAddToChartThenOrder);
        itemCount = v.findViewById(R.id.itemCount);
        price = v.findViewById(R.id.price);
        discountPrice = v.findViewById(R.id.discountPrice);
        itemName = v.findViewById(R.id.itemName);

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
