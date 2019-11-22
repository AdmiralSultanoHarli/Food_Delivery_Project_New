package com.example.fooddeliveryproject.Activities.JustForTest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterTopCategories extends RecyclerView.Adapter<AdapterTopCategories.ViewHolder> {

    List<DataFood> topList;
    Context context;

    public AdapterTopCategories(List<DataFood> topList, Context context) {
        this.topList = topList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTopCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_top_categories, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
        
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTopCategories.ViewHolder viewHolder, int i) {

        viewHolder.img.setImageResource(topList.get(i).getImg());
        
    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);

        }
    }

}
