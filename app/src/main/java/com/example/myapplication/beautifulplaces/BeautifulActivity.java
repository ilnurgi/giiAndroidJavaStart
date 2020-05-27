package com.example.myapplication.beautifulplaces;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BeautifulActivity extends AppCompatActivity {

    private ViewPager mPager;

    private List<Place> places = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // прозрачный статусбар

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_beautiful);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            toolbar.setTitle("Beautiful Places");
        }
        setSupportActionBar(toolbar);
//        mPager = (ViewPager) findViewById(R.id.pager);

        places.add(new Place("Монако1", "Описание1", "$1", "$2"));
        places.add(new Place("Монако2", "Описание2", "$2", "$3"));
        places.add(new Place("Монако3", "Описание3", "$3", "$4"));
        places.add(new Place("Монако4", "Описание5", "$4", "$5"));

        MyPagerAdapter mPagerAdapter = new MyPagerAdapter(places);
        mPager.setAdapter(mPagerAdapter);
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }
}
