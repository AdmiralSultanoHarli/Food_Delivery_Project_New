package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Fragment.MenuDetailScreenAddOnFragment;
import com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Fragment.MenuDetailScreenItemsFragment;
import com.example.fooddeliveryproject.R;

public class MenuDetailsScreenActivity extends AppCompatActivity {

    TextView itemTotalPriceDiscount;
    ImageButton backButton;
    Button buttonOrder, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details_screen);

        itemTotalPriceDiscount = findViewById(R.id.itemTotalPriceDiscount);
        backButton = findViewById(R.id.backButton);
        buttonOrder = findViewById(R.id.buttonOrder);
        buttonCancel = findViewById(R.id.buttonCancel);

        itemTotalPriceDiscount.setPaintFlags(itemTotalPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MenuDetailsScreenActivity.this, OrderScreenActivity.class);
                startActivity(i);

            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MenuDetailScreenAddOnFragment menuDetailScreenAddOnFragment = new MenuDetailScreenAddOnFragment();
        fragmentTransaction.add(R.id.menuDetailScreenAddOnFragment, menuDetailScreenAddOnFragment, menuDetailScreenAddOnFragment.getTag());

        MenuDetailScreenItemsFragment menuDetailScreenItemsFragment = new MenuDetailScreenItemsFragment();
        fragmentTransaction.add(R.id.menuDetailScreenItemFragment, menuDetailScreenItemsFragment, menuDetailScreenItemsFragment.getTag());

        fragmentTransaction.commit();

    }
}
