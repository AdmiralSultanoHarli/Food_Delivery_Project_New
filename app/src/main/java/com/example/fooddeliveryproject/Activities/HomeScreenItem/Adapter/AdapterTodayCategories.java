package com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterTodayCategories extends RecyclerView.Adapter<AdapterTodayCategories.ViewHolder> {

    List<DataFood> topList;
    Context context;

    public AdapterTodayCategories(List<DataFood> topList, Context context) {
        this.topList = topList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTodayCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_categories, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTodayCategories.ViewHolder viewHolder, int i) {

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.tvNameFood);

        }
    }

}
