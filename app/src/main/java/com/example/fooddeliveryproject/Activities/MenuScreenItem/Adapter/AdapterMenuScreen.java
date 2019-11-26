package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.MenuScreenItem.DataFoodMenu;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> {

    List<DataFoodMenu> menuList;
    Context context;
    Dialog mDialog;
    public int quantity = 1;

    public AdapterMenuScreen(List<DataFoodMenu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMenuScreen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_menu_screen, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        /*//dialog init
        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.view_floating_food_chart);

        viewHolder.item_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TextView itemCount = mDialog.findViewById(R.id.itemCount);
                TextView price = mDialog.findViewById(R.id.price);
                TextView discountPrice = mDialog.findViewById(R.id.discountPrice);
                TextView itemName = mDialog.findViewById(R.id.itemName);
                //itemCount.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodName());
                price.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodPrice());
                discountPrice.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodPriceDiscount());
                itemName.setText(menuList.get(viewHolder.getAdapterPosition()).getFoodName());
                mDialog.show();

            }
        });*/

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Test! " + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

            }
        });*/

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.img.setImageResource(menuList.get(i).getImg());
        viewHolder.foodName.setText(menuList.get(i).getFoodName());
        viewHolder.foodDescription.setText(menuList.get(i).getFoodDescription());
        viewHolder.foodPrice.setText(menuList.get(i).getFoodPrice());
        viewHolder.foodPriceDiscount.setText(menuList.get(i).getFoodPriceDiscount());

        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FoodChartFragment myFragment = new FoodChartFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, myFragment).addToBackStack(null).commit();
                viewHolder.buttonAddToChart.setVisibility(View.GONE);
                viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                quantity = 1;
                viewHolder.displayQuantity(quantity);

            }
        });



    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      public RelativeLayout item_food;
      public ImageView img;
      public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
      public Button buttonAddToChart;
      public CardView buttonAddPlusMinusChart;
      //private PopupWindow mPopupWindow;

        public void displayQuantity(int number){

            chartQuantity.setText(""+number);

        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            item_food = itemView.findViewById(R.id.food_item_id);
            img = itemView.findViewById(R.id.img);
            foodName = itemView.findViewById(R.id.foodName);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodPriceDiscount = itemView.findViewById(R.id.foodPriceDiscount);
            buttonAddToChart = itemView.findViewById(R.id.buttonAddToChart);
            buttonAddPlusMinusChart = itemView.findViewById(R.id.buttonAddPlusMinusChart);
            decreaseChartQuantity = itemView.findViewById(R.id.decreaseChartQuantity);
            increaseChartQuantity = itemView.findViewById(R.id.increaseChartQuantity);
            chartQuantity = itemView.findViewById(R.id.chartQuantity);

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (quantity == 1){
                        buttonAddToChart.setVisibility(View.VISIBLE);
                        buttonAddPlusMinusChart.setVisibility(View.GONE);
                        return;
                    }
                    quantity = quantity-1;
                    displayQuantity(quantity);

                }
            });

            increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (quantity == 100){
                        return;
                    }
                    quantity = quantity+1;
                    displayQuantity(quantity);

                }
            });

            /*buttonAddToChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context, PopUp.class);
                    context.startActivity(i);


                    *//*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View customView = inflater.inflate(R.layout.view_floating_food_chart, null);

                    mPopupWindow = new PopupWindow(
                            customView,
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );*//*
                }
            });*/

        }
    }

}
