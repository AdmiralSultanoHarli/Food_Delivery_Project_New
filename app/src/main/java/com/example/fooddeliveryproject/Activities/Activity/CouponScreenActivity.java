package com.example.fooddeliveryproject.Activities.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fooddeliveryproject.Activities.CouponScreenItem.AdapterCouponScreen;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class CouponScreenActivity extends BaseActivity {

    private DatabaseHelper mDatabase;
    private ArrayList<DataKhanaval> allData = new ArrayList<>();
    private AdapterCouponScreen mAdapter;

    SearchView searchView;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_screen);

        searchView = findViewById(R.id.searchView);
        backButton = findViewById(R.id.backButton);

        RecyclerView dataView = findViewById(R.id.couponListRecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        dataView.setLayoutManager(linearLayoutManager);
        dataView.setHasFixedSize(true);
        mDatabase = new DatabaseHelper(this);
        allData = mDatabase.listDataCoupon();

        if (allData.size() > 0){

            dataView.setVisibility(View.VISIBLE);
            mAdapter = new AdapterCouponScreen(allData, this);
            dataView.setAdapter(mAdapter);

        }else {

            dataView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no data in database, start adding now", Toast.LENGTH_SHORT).show();

        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("Adapter", String.valueOf(mAdapter));

                if (mAdapter != null){

                    mAdapter.getFilter().filter(newText);

                }

                return true;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
