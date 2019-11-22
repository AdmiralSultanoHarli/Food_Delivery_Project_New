package com.example.fooddeliveryproject.Activities.HomeScreenItem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.fooddeliveryproject.Activities.HomeScreenItem.DataTopSlide;
import com.example.fooddeliveryproject.R;

import java.util.List;

public class AdapterTopSliderPager extends PagerAdapter {

    private Context mContext;
    private List<DataTopSlide> mList;

    public AdapterTopSliderPager(Context mContext, List<DataTopSlide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.view_top_categories, container,false);

        ImageView slideImage = slideLayout.findViewById(R.id.img);

        slideImage.setImageResource(mList.get(position).getImage());

        container.addView(slideLayout);

        slideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, "Not Yet", Toast.LENGTH_SHORT).show();

            }
        });

        return  slideLayout;



    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == o;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
