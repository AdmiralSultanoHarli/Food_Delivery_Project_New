package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fooddeliveryproject.Activities.OrderScreenItem.Fragment.OrderScreenOrderFragment;
import com.example.fooddeliveryproject.R;

public class OrderScreenActivity extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        OrderScreenOrderFragment orderScreenOrderFragment = new OrderScreenOrderFragment();
        fragmentTransaction.add(R.id.orderSummaryFragment, orderScreenOrderFragment, orderScreenOrderFragment.getTag());

        fragmentTransaction.commit();

    }
}
