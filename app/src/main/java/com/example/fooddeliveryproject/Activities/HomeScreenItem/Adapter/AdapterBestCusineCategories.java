package com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.R;

import java.util.List;

import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.FOOD_CATEGORY;
import static com.example.fooddeliveryproject.Activities.Helper.PreferencesUtility.MY_PREFERENCE;

public class AdapterBestCusineCategories extends RecyclerView.Adapter<AdapterBestCusineCategories.ViewHolder> {

    List<DataKhanaval> topList;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //BestCusineFragment bestCusineFragment = new BestCusineFragment();

    public AdapterBestCusineCategories(List<DataKhanaval> topList, Context context) {
        this.topList = topList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBestCusineCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_categories, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, "Test Click! " + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();


            }
        });*/

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBestCusineCategories.ViewHolder viewHolder, final int i) {

        viewHolder.foodName.setText(topList.get(i).getFoodName());
        viewHolder.img.setImageResource(topList.get(i).getImg());
        /*final Bundle bundle = new Bundle();*/
        sharedPreferences = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        viewHolder.itemCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(context, MenuScreenActivity.class);
                /*bundle.putString("FoodShop", topList.get(i).getFoodName());
                foodChartFragment.setArguments(bundle);*/

                SaveSharedPreference.setFoodCategory(context, topList.get(i).getFoodName());
                editor.putString(FOOD_CATEGORY, topList.get(i).getFoodName());

                //b.putExtra("FoodShop", topList.get(i).getFoodName());
                //Log.e("item food name bundle", topList.get(i).getFoodName());
                context.startActivity(b);

            }
        });

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName;
        public CardView itemCard;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.tvNameFood);
            itemCard = itemView.findViewById(R.id.itemCard);

        }
    }

}
