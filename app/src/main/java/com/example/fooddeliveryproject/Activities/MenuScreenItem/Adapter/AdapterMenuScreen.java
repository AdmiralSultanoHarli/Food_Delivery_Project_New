package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.MenuScreenItem.DataFoodMenu;
import com.example.fooddeliveryproject.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> {

    List<DataFoodMenu> menuList;
    Context context;

    public AdapterMenuScreen(List<DataFoodMenu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMenuScreen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_menu_screen, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.img.setImageResource(menuList.get(i).getImg());
        viewHolder.foodName.setText(menuList.get(i).getFoodName());
        viewHolder.foodDescription.setText(menuList.get(i).getFoodDescription());
        viewHolder.foodPrice.setText(menuList.get(i).getFoodPrice());

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      public ImageView img;
      public TextView foodName, foodDescription, foodPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.foodName);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.foodPrice);
        }
    }

}
