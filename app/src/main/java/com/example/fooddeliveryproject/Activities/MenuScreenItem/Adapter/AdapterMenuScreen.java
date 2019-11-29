package com.example.fooddeliveryproject.Activities.MenuScreenItem.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataFood;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.DataFoodMenu;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.FoodChartFragment;
import com.example.fooddeliveryproject.Activities.MenuScreenItem.Fragment.MenuScreenFragment;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMenuScreen extends RecyclerView.Adapter<AdapterMenuScreen.ViewHolder> {

    List<DataFoodMenu> menuList;
    Context context;
    Dialog mDialog;
    public int quantity = 1;
    FoodChartFragment foodChartFragment = new FoodChartFragment();
    //boolean foodChartIsShowing = true;


    //private FragmentCommunication mCommunicator;

    public AdapterMenuScreen(List<DataFoodMenu> menuList, Context context/*, FragmentCommunication mCommunicator*/) {
        this.menuList = menuList;
        this.context = context;
        /*this.mCommunicator = mCommunicator;*/
    }


    @NonNull
    @Override
    public AdapterMenuScreen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_menu_screen, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v/*, mCommunicator*/);

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        //final DataFoodMenu dataFoodMenu = menuList.get(i);


        viewHolder.img.setImageResource(menuList.get(i).getImg());
        viewHolder.foodName.setText(menuList.get(i).getFoodName());
        viewHolder.foodDescription.setText(menuList.get(i).getFoodDescription());
        viewHolder.foodPrice.setText(menuList.get(i).getFoodPrice());
        viewHolder.foodPriceDiscount.setText(menuList.get(i).getFoodPriceDiscount());





        viewHolder.buttonAddToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();



                //viewHolder.foodChartFragment.animate().translationY(100).setDuration(2000);

                viewHolder.buttonAddToChart.setVisibility(View.GONE);
                viewHolder.buttonAddPlusMinusChart.setVisibility(View.VISIBLE);

                quantity = 1;
                viewHolder.displayQuantity(quantity);
                Bundle bundle = new Bundle();
                bundle.putString("Food", menuList.get(i).getFoodName());
                Log.e("test Bundle", menuList.get(i).getFoodName());
                foodChartFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.foodChartFragment, foodChartFragment).addToBackStack(null).commit();

                //foodChartIsShowing = false;


            }
        });

        viewHolder.decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                if (quantity == 1){
                    viewHolder.buttonAddToChart.setVisibility(View.VISIBLE);
                    viewHolder.buttonAddPlusMinusChart.setVisibility(View.GONE);
                    activity.getSupportFragmentManager().beginTransaction().remove(foodChartFragment).commit();
                    return;
                }
                quantity = quantity-1;
                viewHolder.displayQuantity(quantity);
            }
        });

        viewHolder.increaseChartQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity == 100){
                    return;
                }
                quantity = quantity+1;
                viewHolder.displayQuantity(quantity);

            }
        });



    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

   /* public interface FragmentCommunication{

        void respond(int i, String foodName);

    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{

      //public RelativeLayout item_food;
      //public LinearLayout foodChartFragment;
      public ImageView img;
      public TextView foodName, foodDescription, foodPrice, foodPriceDiscount, decreaseChartQuantity, increaseChartQuantity, chartQuantity;
      public Button buttonAddToChart;
      public CardView buttonAddPlusMinusChart;
      /*FragmentCommunication mCommunication;*/
      //private PopupWindow mPopupWindow;

        public void displayQuantity(int number){

            chartQuantity.setText(""+number);
            /*Intent intent = new Intent(context, MenuScreenFragment.class);
            intent.putExtra("ss", chartQuantity);*/

        }

        public ViewHolder(@NonNull final View itemView/*, final FragmentCommunication mCommunicator*/) {
            super(itemView);

            //item_food = itemView.findViewById(R.id.food_item_id);
            //foodChartFragment = itemView.findViewById(R.id.foodChartFragment);
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

            /*mCommunication = mCommunicator;*/

            foodPriceDiscount.setPaintFlags(foodPriceDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            /*final AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
            final FoodChartFragment myFragment = new FoodChartFragment();*/

            /*decreaseChartQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (quantity == 1){
                        buttonAddToChart.setVisibility(View.VISIBLE);
                        buttonAddPlusMinusChart.setVisibility(View.GONE);
                        activity.getSupportFragmentManager().beginTransaction().remove(myFragment).commit();
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
            });*/



            /*buttonAddToChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context, PopUp.class);
                    context.startActivity(i);


                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View customView = inflater.inflate(R.layout.view_floating_food_chart, null);

                    mPopupWindow = new PopupWindow(
                            customView,
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                }
            });
*/
        }
    }

}
