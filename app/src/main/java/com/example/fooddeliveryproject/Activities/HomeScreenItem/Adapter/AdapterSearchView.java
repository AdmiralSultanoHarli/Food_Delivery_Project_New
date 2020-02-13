package com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.List;

public class AdapterSearchView extends RecyclerView.Adapter<AdapterSearchView.ViewHolder> implements Filterable {

    List<DataKhanaval> topList;
    List<DataKhanaval> mTopList;
    Context context;

    private DatabaseHelper helper;

    public AdapterSearchView(List<DataKhanaval> topList, Context context) {
        this.topList = topList;
        this.context = context;
        this.mTopList = topList;
        helper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public AdapterSearchView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_search_item, viewGroup, false);
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
    public void onBindViewHolder(@NonNull AdapterSearchView.ViewHolder viewHolder, final int i) {

        final DataKhanaval data = topList.get(i);

        viewHolder.restName.setText(data.getFoodName());
        viewHolder.restAddress.setText(data.getAddress());
        viewHolder.img.setImageResource(data.getImg());

        viewHolder.searchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(context, MenuScreenActivity.class);
                /*bundle.putString("FoodShop", topList.get(i).getFoodName());
                foodChartFragment.setArguments(bundle);*/
                b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                SaveSharedPreference.setFoodCategory(context, data.getFoodName());
                SaveSharedPreference.setFoodShopImg(context, data.getImg());

                //that i comment below
                /*b.putExtra("FoodShop", topList.get(i).getFoodName());
                Log.e("item food name bundle", topList.get(i).getFoodName());*/
                context.startActivity(b);

            }
        });

    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()){

                    topList = mTopList;

                }else {

                    ArrayList<DataKhanaval> filteredList = new ArrayList<>();
                    for (DataKhanaval data : mTopList){

                        if (data.getFoodName().toLowerCase().contains(charString) || data.getFoodName().contains(charString)
                                || data.getFoodName().toUpperCase().contains(charString)){

                            filteredList.add(data);

                        }

                    }

                    topList = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = topList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                topList = (ArrayList<DataKhanaval>) filterResults.values;
                notifyDataSetChanged();

            }
        };

    }

    @Override
    public int getItemCount() {
        return topList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView restName, restAddress;
        public CardView searchCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.orderFoodImage);
            restName = itemView.findViewById(R.id.restName);
            restAddress = itemView.findViewById(R.id.restAddress);
            searchCard = itemView.findViewById(R.id.searchCard);

        }
    }

}