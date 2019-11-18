package com.example.fooddeliveryproject.HomeScreenTop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.R;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<ListTopClass> items;

    public TopAdapter(Context context, ArrayList<ListTopClass> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_rectangle_layout, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.listTop.setImageResource(items.get(position).getListTop());

    }
    @Override
    public int getItemCount() {
        return items.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView listTop;

        public CustomViewHolder(View view) {
            super(view);

            listTop = view.findViewById(R.id.rectangleTop);

        }


    }


}

