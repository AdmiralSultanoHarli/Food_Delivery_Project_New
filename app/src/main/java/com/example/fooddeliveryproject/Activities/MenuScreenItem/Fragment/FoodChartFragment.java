package com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.fooddeliveryproject.R;

public class FoodChartFragment extends Fragment {

    RelativeLayout foodChartRelative;
    CardView buttonAddToChartThenOrder;

    public FoodChartFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_food_chart,container, false);

        buttonAddToChartThenOrder = v.findViewById(R.id.buttonAddToChartThenOrder);

        buttonAddToChartThenOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(container.getContext(), "Test", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }


}
