package com.example.fooddeliveryproject.Activities.HomeScreenItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterFavouriteCategories extends RecyclerView.Adapter<AdapterFavouriteCategories.ViewHolder> {

    List<DataFood> topList;
    Context context;

    public AdapterFavouriteCategories(List<DataFood> topList, Context context) {
        this.topList = topList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFavouriteCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_categories, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavouriteCategories.ViewHolder viewHolder, int i) {

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
