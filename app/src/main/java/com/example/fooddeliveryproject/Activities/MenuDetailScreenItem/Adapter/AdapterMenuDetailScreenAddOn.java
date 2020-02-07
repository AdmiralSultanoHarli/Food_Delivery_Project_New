package com.example.fooddeliveryproject.Activities.MenuDetailScreenItem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

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

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_add_on_new, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Locale locale = Locale.getDefault();
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(locale);
        formatSymbols.setDecimalSeparator(',');
        formatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("", formatSymbols);

        viewHolder.foodName.setText(menuDetailList.get(i).getFoodName());
        viewHolder.foodPrice.setText("Rp. " + decimalFormat.format(menuDetailList.get(i).getFoodPrice()));
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

            img = itemView.findViewById(R.id.orderFoodImage);
            foodName = itemView.findViewById(R.id.orderFoodName);
            foodPrice = itemView.findViewById(R.id.orderFoodPrice);
            decreaseChartQuantity = itemView.findViewById(R.id.orderDecreaseCartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.orderIncreaseCartQuantity);
            chartQuantity = itemView.findViewById(R.id.orderCartQuantity);


        }
    }
}
