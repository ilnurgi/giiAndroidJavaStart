package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MetroAdapter extends ArrayAdapter<String> {

    private List<String> stations;

    public MetroAdapter(@NonNull Context context, int resource, List<String> stations) {
        super(context, resource);
        this.stations = stations;
    }

    @Override
    public int getCount() {
        Log.d("ilnurgi", "getCount: ");
        return stations.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("ilnurgi", "getView: " + position);
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(R.layout.metro_item, parent, false);

        String station = stations.get(position);

        TextView s = convertView.findViewById(R.id.station);
        s.setText(station);

        return convertView;
    }
}
