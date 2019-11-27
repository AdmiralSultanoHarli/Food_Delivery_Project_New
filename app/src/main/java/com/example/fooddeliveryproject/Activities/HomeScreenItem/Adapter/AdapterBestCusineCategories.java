package com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterBestCusineCategories extends RecyclerView.Adapter<AdapterBestCusineCategories.ViewHolder> {

    List<DataFood> topList;
    Context context;

    public AdapterBestCusineCategories(List<DataFood> topList, Context context) {
        this.topList = topList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBestCusineCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_categories, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, "Test Click! " + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MenuScreenActivity.class);
                context.startActivity(i);

            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBestCusineCategories.ViewHolder viewHolder, int i) {

        viewHolder.foodName.setText(topList.get(i).getFoodName());
        viewHolder.img.setImageResource(topList.get(i).getImg());

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.tvNameFood);

        }
    }

}