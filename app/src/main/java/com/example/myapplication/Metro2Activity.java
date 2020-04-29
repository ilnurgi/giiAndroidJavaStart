package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Metro2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Metro> stations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro2);

        stations.add(new Metro("Пролетарская", "dob10", "dod20", 0f));
        stations.add(new Metro("Кузнецкий", "dob11", "dod21", 1f));
        stations.add(new Metro("Смоленская", "dob12", "dod22", 2f));
        stations.add(new Metro("Киевская", "dob13", "dod23", 3f));
        stations.add(new Metro("Таганская", "dob14", "dod24", 4f));
        stations.add(new Metro("Студенческая", "dob15", "dod25", 5f));
        stations.add(new Metro("Римская", "dob16", "dod26", 10f));
        stations.add(new Metro("Комсомольская", "dob17", "dod27", 11f));
        stations.add(new Metro("Рижская", "dob18", "dod28", 12f));
        stations.add(new Metro("Китай-город", "dob19", "dod29", 13f));

        ListView list = findViewById(R.id.list);
        list.setAdapter(new Metro2Adapter(this, R.layout.metro_item, stations));
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Metro m = stations.get(position);
        Toast.makeText(this, m.getName(), Toast.LENGTH_SHORT).show();
    }
}
