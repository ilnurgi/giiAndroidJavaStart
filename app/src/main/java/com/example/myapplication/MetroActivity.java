package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MetroActivity extends AppCompatActivity {

    private ListView list;
    private List<String> stations = new ArrayList<>();
    private List<Metro> stations2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        list = findViewById(R.id.list);

        stations.add("Пролетарская");
        stations.add("Китай-город");
        stations.add("Кузнецкий мост");
        stations.add("Смоленская");
        stations.add("Киевская");
        stations.add("Таганская");
        stations.add("Студенческая");
        stations.add("Римская");
        stations.add("Комсомольская");
        stations.add("Рижская");

        MetroAdapter adapter = new MetroAdapter(this, R.layout.metro_item, stations);
        list.setAdapter(adapter);

    }
}
