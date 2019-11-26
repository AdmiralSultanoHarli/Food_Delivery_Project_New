package com.example.fooddeliveryproject.Activities.JustForTest.PopUp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;

import androidx.annotation.Nullable;

import com.example.fooddeliveryproject.R;

public class PopUp extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_food_chart);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout((int)(width*.2), (int)(height*.2));
    }
}
