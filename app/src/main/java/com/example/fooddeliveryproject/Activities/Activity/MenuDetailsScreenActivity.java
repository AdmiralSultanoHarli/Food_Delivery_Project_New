package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fooddeliveryproject.R;

public class MenuDetailsScreenActivity extends AppCompatActivity {

    TextView itemTotalPriceDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details_screen);

        itemTotalPriceDiscount = findViewById(R.id.itemTotalPriceDiscount);

        itemTotalPriceDiscount.setPaintFlags(itemTotalPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }
}
