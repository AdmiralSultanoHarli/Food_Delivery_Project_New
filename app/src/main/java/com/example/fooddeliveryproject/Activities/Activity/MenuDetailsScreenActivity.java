package com.example.fooddeliveryproject.Activities.Activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddeliveryproject.Activities.Helper.DecimalHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Fragment.MenuDetailScreenItemsFragment;
import com.example.fooddeliveryproject.R;

public class MenuDetailsScreenActivity extends BaseActivity {

    TextView itemTotalPriceDiscount, itemTotalPrice, itemName, itemDesc;
    ImageView backButton, img;
    Button buttonContinue;

    DecimalHelper decimalHelper;

    //aaa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details_screen);

        itemTotalPriceDiscount = findViewById(R.id.itemTotalPriceDiscount);
        itemTotalPrice = findViewById(R.id.itemTotalPrice);
        itemName = findViewById(R.id.itemName);
        itemDesc = findViewById(R.id.itemDesc);
        backButton = findViewById(R.id.backButton);
        buttonContinue = findViewById(R.id.buttonContinue);
        img = findViewById(R.id.img);

        decimalHelper = new DecimalHelper();

        itemTotalPriceDiscount.setPaintFlags(itemTotalPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        img.setImageResource(SaveSharedPreference.getFoodDetailImg(this, 0));
        itemName.setText(SaveSharedPreference.getFoodDetailName(this, ""));
        itemTotalPrice.setText(decimalHelper.formatter(SaveSharedPreference.getFoodDetailPrice(this, 0)));
        itemTotalPriceDiscount.setText(decimalHelper.formatter(SaveSharedPreference.getFoodDetailDiscountPrice(this, 0)));
        itemDesc.setText("Sate atau satai adalah makanan yang terbuat dari daging yang dipotong ... bumbu-bumbu Tionghoa, sehingga sate Tionghoa memiliki cita rasa sepert");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /*OrderAddOnFragment menuDetailScreenAddOnFragment = new OrderAddOnFragment();
        fragmentTransaction.replace(R.id.menuDetailScreenAddOnFragment, menuDetailScreenAddOnFragment, menuDetailScreenAddOnFragment.getTag());*/

        MenuDetailScreenItemsFragment menuDetailScreenItemsFragment = new MenuDetailScreenItemsFragment();
        fragmentTransaction.replace(R.id.menuDetailScreenItemFragment, menuDetailScreenItemsFragment, menuDetailScreenItemsFragment.getTag());

        fragmentTransaction.commit();

    }
}
