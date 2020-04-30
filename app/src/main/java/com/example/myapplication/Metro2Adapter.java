package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Metro2Adapter
        extends ArrayAdapter<Metro>
//        implements SectionIndexer
{

    private List<Metro> stations;

    private HashSet<Integer> selection = new HashSet<>();

    public Metro2Adapter(@NonNull Context context, int resource, List<Metro> stations) {
        super(context, resource);

        this.stations = stations;
    }

    @Override
    public int getCount() {
        Log.d("ilnurgi", "getCount: ");
        return stations.size();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isSelected(position)){
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isSelected(int position){
        return selection.contains(position);
    }

    public void toggleSelection(int position){
        if (isSelected(position)){
            selection.remove(position);
        } else {
            selection.add(position);
        }
        notifyDataSetChanged();
    }

    public void deleteSelected(){
        List<Integer> items = new ArrayList<>(selection);
        Collections.sort(items);
        for (int i: items){
            selection.remove(i);
            stations.remove(i);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("ilnurgi", "getView: " + position);
        Metro station = stations.get(position);
        Holder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            if (isSelected(position)) {
                convertView = inflater.inflate(R.layout.metro2_item, parent, false);
//                convertView = inflater.inflate(R.layout.metro2_item_selected, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.metro2_item, parent, false);
            }
            holder = new Holder();

            holder.text = convertView.findViewById(R.id.text);
            holder.dob = convertView.findViewById(R.id.dob);
            holder.dod = convertView.findViewById(R.id.dod);
            holder.bar = convertView.findViewById(R.id.rating);
            holder.image = convertView.findViewById(R.id.threedots);

            convertView.setTag(holder);
        }

        holder = (Holder) convertView.getTag();

        holder.text.setText(station.getName());
        holder.dob.setText(station.getDob());
        holder.dod.setText(station.getDod());
        holder.bar.setRating(station.getRating());

        return convertView;
    }

//    @Override
//    public Object[] getSections() {
//        return new Object[0];
//    }
//
//    @Override
//    public int getPositionForSection(int i) {
//        return 0;
//    }
//
//    @Override
//    public int getSectionForPosition(int i) {
//        return 0;
//    }

    private static class Holder {
        TextView text;
        TextView dob;
        TextView dod;
        RatingBar bar;
        ImageView image;
    }
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        Log.d("ilnurgi", "getView: " + position);
//        Metro station = stations.get(position);
//
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        convertView = inflater.inflate(R.layout.metro2_item, parent, false);
//
//        TextView text = convertView.findViewById(R.id.text);
//        text.setText(station.getName());
//
//        TextView dob = convertView.findViewById(R.id.dob);
//        dob.setText(station.getDob());
//
//        TextView dod = convertView.findViewById(R.id.dod);
//        dod.setText(station.getDod());
//
//        RatingBar bar = convertView.findViewById(R.id.rating);
//        bar.setRating(station.getRating());
//
//        ImageView image = convertView.findViewById(R.id.threedots);
//
//        return convertView;
//    }
}
