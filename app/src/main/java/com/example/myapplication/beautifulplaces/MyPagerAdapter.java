package com.example.myapplication.beautifulplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MyPagerAdapter extends PagerAdapter {

    private List<Place> places;

    public MyPagerAdapter(List<Place> places) {
        this.places = places;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        View layout = LayoutInflater.from(context)
                .inflate(R.layout.beautifull_page, container, false);

        Place p = places.get(position);
        String pictureUrl = p.getPicture();

        ImageView picture = layout.findViewById(R.id.picture);

//        Picasso
//                .with(context)
//                .load(pictureUrl)
//                .fit()
//                .centerCrop()
//                .into(picture);

        TextView place = layout.findViewById(R.id.place);
        place.setText(p.getPlace());

        TextView description = layout.findViewById(R.id.description);
        description.setText(p.getDescription());

        TextView oldPrice = layout.findViewById(R.id.old_price);
        oldPrice.setText(p.getOldPrice());

        TextView newPrice = layout.findViewById(R.id.new_price);
        newPrice.setText(p.getNewPrice());

        container.addView(layout);
        return layout;
    }
}
