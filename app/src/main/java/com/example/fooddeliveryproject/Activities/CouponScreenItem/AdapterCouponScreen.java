package com.example.fooddeliveryproject.Activities.CouponScreenItem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryproject.Activities.Activity.MenuScreenActivity;
import com.example.fooddeliveryproject.Activities.Activity.OrderScreenActivity;
import com.example.fooddeliveryproject.Activities.Database.DatabaseHelper;
import com.example.fooddeliveryproject.Activities.Helper.SaveSharedPreference;
import com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter.AdapterBestCusineCategories;
import com.example.fooddeliveryproject.Activities.Model.DataKhanaval;
import com.example.fooddeliveryproject.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCouponScreen extends RecyclerView.Adapter<AdapterCouponScreen.ViewHolder> implements Filterable {

    List<DataKhanaval> menuList;
    List<DataKhanaval> mTopList;
    Context context;
    private DatabaseHelper helper;
    //BestCusineFragment bestCusineFragment = new BestCusineFragment();

    public AdapterCouponScreen(List<DataKhanaval> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
        this.mTopList = menuList;
        helper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public AdapterCouponScreen.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_coupon, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCouponScreen.ViewHolder viewHolder, final int i) {

        final DataKhanaval data = menuList.get(i);

        viewHolder.coupName.setText("COUPON " + data.getCouponName());
        viewHolder.coupValue.setText(String.valueOf(data.getCouponValue()));
        viewHolder.coupValid.setText("Valid Until: " + data.getCouponValid());
        viewHolder.img.setImageResource(data.getImg());
        //final Bundle bundle = new Bundle();

        viewHolder.couponCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(context, OrderScreenActivity.class);
                b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SaveSharedPreference.setIsCouponExist(context, true);
                context.startActivity(b);

            }
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()){

                    menuList = mTopList;

                }else {

                    ArrayList<DataKhanaval> filteredList = new ArrayList<>();
                    for (DataKhanaval data : mTopList){

                        if (data.getCouponName().toLowerCase().contains(charString) || data.getCouponName().contains(charString)
                                || data.getCouponName().toUpperCase().contains(charString)){

                            filteredList.add(data);

                        }

                    }

                    menuList = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = menuList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                menuList = (ArrayList<DataKhanaval>) filterResults.values;
                notifyDataSetChanged();

            }
        };

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView coupName, coupValue, coupValid;
        public RelativeLayout couponCard;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            coupName = itemView.findViewById(R.id.coupName);
            coupValue = itemView.findViewById(R.id.coupValue);
            coupValid = itemView.findViewById(R.id.coupValid);
            couponCard = itemView.findViewById(R.id.couponCard);
            img = itemView.findViewById(R.id.img);

        }
    }
}
