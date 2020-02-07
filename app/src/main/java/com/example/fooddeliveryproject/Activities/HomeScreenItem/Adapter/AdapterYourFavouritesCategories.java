package com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterYourFavouritesCategories extends RecyclerView.Adapter<AdapterYourFavouritesCategories.ViewHolder> {

    List<DataKhanaval> topList;
    List<DataKhanaval> mTopList;
    Context context;

    private DatabaseHelper helper;

    public AdapterYourFavouritesCategories(List<DataKhanaval> topList, Context context) {
        this.topList = topList;
        this.context = context;
        this.mTopList = topList;
        helper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public AdapterYourFavouritesCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_categories, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MenuScreenActivity.class);
                context.startActivity(i);

            }
        });*/

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterYourFavouritesCategories.ViewHolder viewHolder, final int i) {

        final DataKhanaval data = topList.get(i);

        viewHolder.foodName.setText(data.getFoodName());
        viewHolder.img.setImageResource(data.getImg());

        viewHolder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(context, MenuScreenActivity.class);
                /*bundle.putString("FoodShop", topList.get(i).getFoodName());
                foodChartFragment.setArguments(bundle);*/

                SaveSharedPreference.setFoodCategory(context, data.getFoodName());

               /* b.putExtra("FoodShop", topList.get(i).getFoodName());
                Log.e("item food name bundle", topList.get(i).getFoodName());*/
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.orderFoodImage);
            foodName = itemView.findViewById(R.id.tvNameFood);
            itemCard = itemView.findViewById(R.id.itemCard);

        }
    }

}
