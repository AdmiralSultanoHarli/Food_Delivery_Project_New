package com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Data.DataKhanaval;
import com.example.fooddeliveryproject.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterMenuDetailScreenAddOn extends RecyclerView.Adapter<AdapterMenuDetailScreenAddOn.ViewHolder> {

    List<DataKhanaval> menuDetailList;
    Context context;

    public AdapterMenuDetailScreenAddOn(List<DataKhanaval> menuDetailList, Context context) {
        this.menuDetailList = menuDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMenuDetailScreenAddOn.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_add_on, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.foodName.setText(menuDetailList.get(i).getFoodName());
        viewHolder.foodPrice.setText(String.valueOf(menuDetailList.get(i).getFoodPrice()));
        viewHolder.img.setImageResource(menuDetailList.get(i).getImg());

        final int quantity[] = {menuDetailList.get(i).getChartQuantity()};

        quantity[0] = 0;

        viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] == 100){

                    return;

                }

                quantity[0]++;
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] == 0){

                    return;

                }
                quantity[0]--;
                viewHolder.chartQuantity.setText(String.valueOf(quantity[0]));

            }
        });

    }


    @Override
    public int getItemCount() {
        return menuDetailList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView foodName, foodPrice, decreaseChartQuantity, increaseChartQuantity, chartQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            decreaseChartQuantity = itemView.findViewById(R.id.decreaseChartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.increaseChartQuantity);
            chartQuantity = itemView.findViewById(R.id.chartQuantity);


        }
    }
}
